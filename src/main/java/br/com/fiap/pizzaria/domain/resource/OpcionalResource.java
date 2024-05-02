package br.com.fiap.pizzaria.domain.resource;

import br.com.fiap.pizzaria.domain.dto.request.PizzariaRequest;
import br.com.fiap.pizzaria.domain.dto.response.PizzariaResponse;
import br.com.fiap.pizzaria.domain.dto.response.ProdutoResponse;
import br.com.fiap.pizzaria.domain.dto.response.SaborResponse;
import br.com.fiap.pizzaria.domain.entity.Pizzaria;
import br.com.fiap.pizzaria.domain.entity.Produto;
import br.com.fiap.pizzaria.domain.entity.Sabor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.fiap.pizzaria.domain.service.OpcionalService;
import br.com.fiap.pizzaria.domain.repository.SaborRepository;
import br.com.fiap.pizzaria.domain.entity.Opcional;
import br.com.fiap.pizzaria.domain.dto.response.OpcionalResponse;
import br.com.fiap.pizzaria.domain.dto.request.OpcionalRequest;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;


@RestController
@RequestMapping("/opcionais")
public class OpcionalResource implements ResourceDTO<OpcionalRequest, OpcionalResponse> {

    @Autowired
    private OpcionalService repo;

    @Autowired
    private SaborRepository saborService;

    @GetMapping("/{id}")
    public ResponseEntity<OpcionalResponse> findById(@PathVariable Long id) {
        var entity = repo.findById(id);
        if (Objects.isNull(entity))
            return ResponseEntity.notFound().build();
        var response = repo.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<OpcionalResponse> save(@RequestBody OpcionalRequest opcional) {
        var entity = repo.toEntity(opcional);
        var saved = repo.save(entity);
        var response = repo.toResponse(saved);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @Override
    public ResponseEntity<PizzariaResponse> save(PizzariaRequest r) {
        return null;
    }

    @GetMapping
    public ResponseEntity<Collection<OpcionalResponse>> findAll(
            @RequestParam(name = "sabor", required = false) Long idSabor,
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "preco", required = false) BigDecimal preco
    ) {
        var sabor = Objects.nonNull(idSabor) ? saborService
                .findById(idSabor)
                .orElseThrow() : null;
        var opcional = Opcional.builder()
                .nome(nome)
                .sabor(sabor)
                .preco(preco)
                .build();
        Example<Opcional> example = Example.of(opcional);
        Collection<Opcional> opcionais = repo.findAll(example);
        var response = opcionais.stream().map(repo::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @Override
    public OpcionalResponse toResponse(Opcional e) {

        var sabores = saborService.toResponse(e.getSabor());
        return OpcionalResponse.builder().id(e.getId()).nome(e.getNome()).saborResponse((SaborResponse) sabores).build();
    }

    @Override
    public PizzariaResponse toResponse(Pizzaria e) {
        return null;
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
