package br.com.fiap.pizzaria.domain.resource;

import br.com.fiap.pizzaria.domain.dto.request.ProdutoRequest;
import org.springframework.http.ResponseEntity;

public interface ResourceDTO<Request, Response> {



    ResponseEntity<Response> findById(Long id);

    ResponseEntity<Response> save(ProdutoRequest request);


}