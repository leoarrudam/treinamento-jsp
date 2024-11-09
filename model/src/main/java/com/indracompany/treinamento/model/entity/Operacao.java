package com.indracompany.treinamento.model.entity;

import com.indracompany.treinamento.model.entity.enums.OperacaoTipo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "operacoes_jd_lw_3")
@Data
@EqualsAndHashCode(callSuper = true)
public class Operacao extends GenericEntity<Long> {

    @Id
    @GeneratedValue
    private Long id;
    private Double valor;
    private String descricao;
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private OperacaoTipo tipo;

    @ManyToOne
    @JoinColumn(name = "conta_bancaria_fk")
    private ContaBancaria contaBancaria;
}
