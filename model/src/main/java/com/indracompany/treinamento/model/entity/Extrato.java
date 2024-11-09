package com.indracompany.treinamento.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.indracompany.treinamento.util.TipoTransacao;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "transacoes_wesleyfreit")
@Data
@EqualsAndHashCode(callSuper = true)
public class Extrato extends GenericEntity<Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private TipoTransacao tipo; // "SAQUE", "DEPOSITO", "TRANSFERENCIA"

  @Column
  private LocalDateTime dtInicio;

  @Column
  private LocalDateTime dtFim;

  @Column
  private double saldo;

  @ManyToOne
  @JoinColumn(name = "fk_conta_id")
  private ContaBancaria conta;
}
