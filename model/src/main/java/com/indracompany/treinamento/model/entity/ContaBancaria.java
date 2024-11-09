package com.indracompany.treinamento.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "contas_bancarias_time7")
@Data
@EqualsAndHashCode(callSuper = true)
public class ContaBancaria extends GenericEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 4)
    private String agencia;

    @Column(length = 6)
    private String numero;

    @Column
    private double saldo;

    @ManyToOne
    @JoinColumn(name = "fk_cliente_id")
    private Cliente cliente;
}
