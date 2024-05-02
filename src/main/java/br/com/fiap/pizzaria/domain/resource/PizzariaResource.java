package br.com.fiap.pizzaria.domain.resource;

import br.com.fiap.pizzaria.domain.dto.request.OpcionalRequest;
import br.com.fiap.pizzaria.domain.dto.request.PizzariaRequest;
import br.com.fiap.pizzaria.domain.dto.request.ProdutoRequest;
import br.com.fiap.pizzaria.domain.dto.response.OpcionalResponse;
import br.com.fiap.pizzaria.domain.dto.response.PizzariaResponse;
import br.com.fiap.pizzaria.domain.dto.response.ProdutoResponse;
import br.com.fiap.pizzaria.domain.entity.Opcional;
import br.com.fiap.pizzaria.domain.entity.Pizzaria;
import br.com.fiap.pizzaria.domain.entity.Produto;
import br.com.fiap.pizzaria.domain.entity.Sabor;
import br.com.fiap.pizzaria.domain.service.PizzariaService;
import br.com.fiap.pizzaria.domain.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/pizzarias")
public class PizzariaResource implements ResourceDTO<PizzariaRequest, PizzariaResponse> {

    @Autowired
    private PizzariaService repo;

    @Autowired
    private ProdutoService produtoService;


    @GetMapping
    public ResponseEntity<Collection<PizzariaResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "cardapio", required = false) Set<Produto> cardapio
    ) {

        var pizzaria = Pizzaria.builder()
                .nome(nome)
                .cardapio(cardapio)
                .build();


        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Pizzaria> example = Example.of(pizzaria, matcher);
        Collection<Pizzaria> pizzarias = repo.findAll(example);

        var response = pizzarias.stream().map(repo::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzariaResponse> findById(@PathVariable Long id) {
        var entity = repo.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = repo.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<PizzariaResponse> save(OpcionalRequest request) {
        return null;
    }

    @Override
    @Transactional
    @PostMapping
    public ResponseEntity<PizzariaResponse> save(@RequestBody @Valid PizzariaRequest r) {
        var entity = repo.toEntity(r);
        var saved = repo.save(entity);
        var resposta = repo.toResponse(saved);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(resposta);
    }

    @Override
    public OpcionalResponse toResponse(Opcional e) {
        return null;
    }

    @Transactional
    @PostMapping(value = "/{id}/cardapio")
    public ResponseEntity<ProdutoResponse> saveOpcional(@PathVariable Long id, @RequestBody ProdutoRequest r){
        Pizzaria pizzaria =  repo.findById(id);

        Set<Produto> cardapio = new LinkedHashSet<>();
        if(Objects.nonNull(pizzaria.getCardapio())){
            cardapio = pizzaria.getCardapio();
        }

        if(Objects.isNull(r)){
            return ResponseEntity.badRequest().build();
        }

        var entity = produtoService.toEntity(r);
        cardapio.add(entity);

        var saved = produtoService.save(entity);
        var response = produtoService.toResponse(saved);



        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/cardapio")
    public ResponseEntity<Collection<ProdutoResponse>> getMenu(@PathVariable Long id) {
        var pizzaria = repo.findById(id);
        if (Objects.isNull(pizzaria)) return ResponseEntity.notFound().build();

        var cardapio = pizzaria.getCardapio();
        var response = cardapio.stream().map(produtoService::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @Override
    public PizzariaResponse toResponse(Pizzaria e) {

        Collection<ProdutoResponse> cardapio = null;
        if (Objects.nonNull(e.getCardapio()) && !e.getCardapio().isEmpty())
            cardapio = e.getCardapio().stream().map(produtoService::toResponse).toList();

        return PizzariaResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .cardapio(cardapio)
                .build();

    }

    @Override
    public ProdutoResponse toResponse(Produto e) {
        return null;
    }

    @Override
    public Object toResponse(Sabor sabor) {
        return null;
    }


}