package com.indracompany.treinamento.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "time_sete")
@Data
@EqualsAndHashCode(callSuper = true)
public class Extrato extends GenericEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String operacao;

    // Saque
    @Column(length = 4)
    private String agenciaOrigem;
    @Column(length = 6)
    private String numeroOrigem;
    @Column
    private double valor;

    // Transferencia
    @Column(length = 4)
    private String agenciaDestino;
    @Column(length = 6)
    private String numeroDestino;
}
