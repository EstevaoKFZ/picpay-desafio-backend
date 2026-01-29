package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.transaction.Transaction;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.dtos.TransactionDTO;
import com.picpaysimplificado.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private  NotificationService notificationService;





    public Transaction createTransaction(TransactionDTO transaction) throws  Exception{
        User sender = this.userService.findUserById((transaction.senderId()));
        User receiver = this.userService.findUserById((transaction.receiverId()));

        userService.validateTransaction(sender, transaction.value());

        boolean isAuthorized = this.authorizeTransaction(sender, transaction.value());
        if(!isAuthorized){
            throw new Exception("Transação não autorizada");
        }

        Transaction  newtransaction = new Transaction();
        newtransaction.setAmount(transaction.value());
        newtransaction.setSender(sender);
        newtransaction.setReceiver(receiver);
        newtransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract((transaction.value())));
        receiver.setBalance((receiver.getBalance().add(transaction.value())));

        this.repository.save(new Transaction());
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        this.notificationService.sendNotification(sender, "Transação realizado com sucesso");

        this.notificationService.sendNotification(receiver, "Transação recebida com sucesso");

        return newtransaction;



    }

    public boolean authorizeTransaction(User sender, BigDecimal value) {
        try {
            ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity
                    ("https://util.devi.tools/api/v2/authorize", Map.class);

            if(authorizationResponse.getStatusCode() == HttpStatus.OK){
                // A nova API envia { "data": { "authorization": true } }
                // Precisamos pegar o mapa "data" primeiro
                Map<String, Object> data = (Map<String, Object>) authorizationResponse.getBody().get("data");
                boolean isAuthorized = (boolean) data.get("authorization");

                return isAuthorized;
            } else {
                return false;
            }
        } catch (Exception e) {
            // Se a API retornar 403 (Forbidden), ela cai aqui.
            // Retornamos false para negar a transação sem travar o sistema.
            return false;
        }
    }


}