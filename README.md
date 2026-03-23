💸 PicPay - Desafio Backend (Java)

Projeto desenvolvido como solução para o desafio backend inspirado no PicPay, com foco em transferências financeiras entre usuários, validações de saldo e regras de negócio.

📌 Objetivo

Construir uma API backend que simule um sistema de pagamentos, permitindo transferências entre usuários comuns e lojistas, seguindo regras específicas.

🚀 Tecnologias Utilizadas
☕ Java (JDK 17+)
🌱 Spring Boot
🛢️ Spring Data JPA
🐘 Banco de Dados (H2 / PostgreSQL)
📦 Maven
🔍 Bean Validation
🔐 REST APIs
📂 Estrutura do Projeto
src/
 ├── main/
 │   ├── java/
 │   │   └── com.seuprojeto.picpay/
 │   │        ├── controller/
 │   │        ├── service/
 │   │        ├── repository/
 │   │        ├── model/
 │   │        └── dto/
 │   └── resources/
 │        ├── application.properties
 └── test/
⚙️ Funcionalidades
✅ Cadastro de usuários (Comum e Lojista)
✅ Transferência entre usuários
❌ Lojistas não podem enviar dinheiro
✅ Validação de saldo antes da transferência
✅ Simulação de autorização externa
✅ Notificação de pagamento (simulada)
📊 Regras de Negócio
Usuários comuns podem enviar e receber dinheiro
Lojistas apenas recebem
Transferências só são realizadas se:
Houver saldo suficiente
Autorização externa for aprovada
🔄 Endpoints Principais
👤 Criar usuário
POST /users
💰 Transferência
POST /transactions
▶️ Como Rodar o Projeto
🔧 Pré-requisitos
Java 17+
Maven instalado
🛠️ Passos
# Clone o repositório
git clone https://github.com/seu-usuario/picpay-desafio-backend.git

# Acesse a pasta
cd picpay-desafio-backend

# Execute o projeto
mvn spring-boot:run
🧪 Testes

Para rodar os testes:

mvn test
🗄️ Banco de Dados

Por padrão, o projeto pode usar:

H2 (em memória) → ideal para testes
PostgreSQL → ambiente real

Configure no application.properties:

spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=
📌 Melhorias Futuras
🔐 Autenticação com JWT
📈 Logs e monitoramento
🧾 Histórico detalhado de transações
🌐 Integração real com APIs externas
🐳 Dockerização do projeto
👨‍💻 Autor

Desenvolvido por [Seu Nome]

LinkedIn: https://www.linkedin.com/
GitHub: https://github.com/
📄 Licença

Este projeto é apenas para fins educacionais.
