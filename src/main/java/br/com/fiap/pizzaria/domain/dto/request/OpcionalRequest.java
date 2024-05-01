package br.com.fiap.pizzaria.domain.dto.request;


import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OpcionalRequest (

        Long id,

        String nome,

        AbstractRequest sabor,

        @Positive(message = "O preço é obrigatório ser um número positivo")
        BigDecimal preco

) {
}
