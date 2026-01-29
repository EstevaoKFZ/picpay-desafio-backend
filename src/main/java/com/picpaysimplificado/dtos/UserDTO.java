package com.picpaysimplificado.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.picpaysimplificado.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName,String document , BigDecimal balance,String email, String password, @JsonProperty("userType")UserType UserType) {

}
