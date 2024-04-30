package br.com.fiap.pizzaria.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sabor {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sq_sabor")
    @SequenceGenerator(name = "id_sq_sabor", sequenceName = "id_sq_sabor", allocationSize = 1)
    @Column(name = "id_sabor")
    private Long id;


    @Column(name = "nm_sabor", unique = true)
    private String nome;


    @Column(name = "desc_sabor")
    private String descricao;


}