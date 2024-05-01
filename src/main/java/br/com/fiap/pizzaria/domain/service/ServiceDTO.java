package br.com.fiap.pizzaria.domain.service;


import br.com.fiap.pizzaria.domain.dto.request.OpcionalRequest;
import br.com.fiap.pizzaria.domain.dto.request.PizzariaRequest;
import br.com.fiap.pizzaria.domain.dto.request.ProdutoRequest;
import br.com.fiap.pizzaria.domain.dto.request.SaborRequest;
import br.com.fiap.pizzaria.domain.entity.Opcional;
import br.com.fiap.pizzaria.domain.entity.Pizzaria;
import br.com.fiap.pizzaria.domain.entity.Produto;
import br.com.fiap.pizzaria.domain.entity.Sabor;
import org.springframework.data.domain.Example;

import java.util.Collection;

/**
 * @param <Entity>    Entity
 * @param <Request>   RequestDTO
 * @param <Response>> ResponseDTO
 */
public interface ServiceDTO<Entity, Request, Response> {
    /**
     * Busca todas as entidades
     *
     * @return
     */
    public Collection<Entity> findAll();

    /**
     * Consulta todas as entidades conforme o exemplo fornecido
     *
     * @param example
     * @return
     */
    public Collection<Entity> findAll(Example<Entity> example);

    /**
     * Busca uma entidade por Id
     *
     * @param id
     * @return
     */
    public Entity findById(Long id);

    /**
     * Persiste uma entidade no banco de dados
     *
     * @param e
     * @return
     */
    public Entity save(Entity e);

    /**
     * Transforma o RequestDTO em Entidade
     *
     * @param dto é um DTO de requisição ( RequestDTO )
     * @return uma Entidade
     */
    public Entity toEntity(Request dto);

    Pizzaria toEntity(PizzariaRequest dto);

    Produto toEntity(ProdutoRequest dto);

    Sabor toEntity(SaborRequest dto);

    /**
     * Transforma uma Entidade em um DTO de Resposta (ResponseDTO)
     *
     * @param e
     * @return
     */
    public Response toResponse(Entity e);

}
