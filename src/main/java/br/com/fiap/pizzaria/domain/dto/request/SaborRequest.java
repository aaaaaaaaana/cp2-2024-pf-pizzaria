package br.com.fiap.pizzaria.domain.dto.request;


import jakarta.validation.constraints.NotNull;


public record SaborRequest (



        @NotNull
                (message = "Por favor, forneça uma descrição. Este campo não pode ser nulo.")
        String descricao,



        @NotNull
                (message = "Por favor, insira um nome. Este campo não pode ser nulo.")
        String nome


){
}
