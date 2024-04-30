package br.com.fiap.pizzaria.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "cp2_pizzaria" )
public class Pizzaria {


    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "id_seq_pizzaria" )
    @Column( name = "id_pizzaria" )
    private Long id;


    @Column( name = "nm_pizzaria", unique = true)
    private String nome;


    @ManyToMany( fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "cp2_produto_pizzaria",
            joinColumns = {
                    @JoinColumn (
                            name = "pizzaria",
                            referencedColumnName = "id_pizzaria",
                            foreignKey = @ForeignKey(name = "fk_produto_pizzaria")
                    )
            },

            inverseJoinColumns = {
                    @JoinColumn(
                            name = "produto",
                            referencedColumnName = "id_produto",
                            foreignKey = @ForeignKey( name = "fk_pizzaria_produto" )
                    )
            }
    )
    private Set<Produto> cardapio = new LinkedHashSet<>();

}
