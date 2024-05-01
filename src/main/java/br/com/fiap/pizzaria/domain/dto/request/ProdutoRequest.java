package br.com.fiap.pizzaria.domain.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.Valid;

import java.math.BigDecimal;



public record ProdutoRequest (

        @NotNull(message = "Por favor, forneça um ID. Este campo não pode ser nulo.")
        Long id,

        @NotNull(message = "Por favor, insira um nome. Este campo não pode ser nulo.")
        String nome,

        @Valid
        @NotNull(message = "Por favor, selecione um sabor. Este campo não pode ser nulo.")
        AbstractRequest sabor,


        @Positive(message = "Por favor, insira um preço válido. O preço deve ser um número positivo.")
        @NotNull(message = "Por favor, insira um preço. Este campo não pode ser nulo.")


        BigDecimal preco


){
}
