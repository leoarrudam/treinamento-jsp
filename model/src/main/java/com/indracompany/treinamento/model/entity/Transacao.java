package com.indracompany.treinamento.model.entity;

import com.indracompany.treinamento.util.TipoDeTransacao;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.ws.rs.DefaultValue;
import java.time.LocalDateTime;

@Entity
@Table(name = "gama_transacoes")
@Data
@EqualsAndHashCode(callSuper = true)
public class Transacao extends GenericEntity<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoDeTransacao tipoDeTransacao;

    @ManyToOne
    private ContaBancaria contaBancaria;

    private double valor;

    private String descricao;

    private LocalDateTime horario;
}
