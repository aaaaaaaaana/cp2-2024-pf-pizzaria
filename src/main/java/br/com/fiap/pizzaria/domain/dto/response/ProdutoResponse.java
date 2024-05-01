package br.com.fiap.pizzaria.domain.dto.response;


import lombok.Builder;

import java.math.BigDecimal;
import java.util.Collection;

@Builder
public record ProdutoResponse (

        Long id,

        String nome,

        SaborResponse sabor,

        BigDecimal preco,

        Collection<OpcionalResponse> opcional






){
}
