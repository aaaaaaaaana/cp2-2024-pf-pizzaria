package br.com.fiap.pizzaria.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cp2_opcional")
public class Opcional {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_opcional")
    @SequenceGenerator(name = "id_opcional", sequenceName = "id_sq_opcional", allocationSize = 1)
    private Long id;


    @Column(name = "nm_opcional")
    private String nome;


    @Column(name = "preco_opcional")
    private BigDecimal preco;


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "sabor_opcional",
            referencedColumnName = "id_sabor",
            foreignKey = @ForeignKey(name = "fk_sabor_opcional")
    )
    private Sabor sabor;

}
