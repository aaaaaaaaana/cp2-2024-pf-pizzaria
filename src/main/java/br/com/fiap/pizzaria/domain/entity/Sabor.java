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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_sabor")
    @SequenceGenerator(name = "sq_sabor", sequenceName = "sq_sabor", allocationSize = 1)
    @Column(name = "id_sabor")
    private Long id;

    //    @Column(name = "nm_sabor", unique = true)
    @Column(name = "nm_sabor")
    private String nome;


    @Column(name = "desc_sabor")
    private String descricao;


}