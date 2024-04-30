package br.com.fiap.pizzaria.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cp2_produto")
public class Produto {




    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sq_produto")
    @SequenceGenerator(name = "id_sq_produto", sequenceName = "id_sq_produto", allocationSize = 1)
    @Column(name = "id_produto")
    private Long id;


    @Column(name = "nm_produto")
    private String nome;


    @ManyToOne( fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST} )
    @JoinColumn(
            name = "sabor_produto",
            referencedColumnName = "id_sabor",
            foreignKey = @ForeignKey(name = "fk_sabor_produto")
    )
    private Sabor sabor;


    @Column(name = "preco_produto")
    private BigDecimal preco;


    @ManyToMany( fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "tb_opcional_produto",
            joinColumns = {
                    @JoinColumn(
                            name = "produto",
                            referencedColumnName = "id_produto",
                            foreignKey = @ForeignKey(name = "fk_opcional_produto")
                    )
            },

            inverseJoinColumns = {
                    @JoinColumn(
                            name = "opcional",
                            referencedColumnName = "id_opcional",
                            foreignKey = @ForeignKey(name = "fk_produto_opcional")

                    )
            }
    )
    private Set<Opcional> opcionais = new LinkedHashSet<>();



}
