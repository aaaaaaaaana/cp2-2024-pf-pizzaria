package br.com.fiap.pizzaria.domain.dto.request;

import br.com.fiap.pizzaria.domain.entity.Produto;
import jakarta.validation.constraints.NotNull;


import java.util.Set;



public record PizzariaRequest (


        @NotNull
                (message = "Por favor, insira um nome para a pizzaria. Este campo é obrigatório.")
        String nome,



        @NotNull
                (message = "Por favor, forneça o cardápio da pizzaria. Este campo não pode ser nulo.")
        Set<Produto> cardapio



){
}