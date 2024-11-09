package com.indracompany.treinamento.model.entity;


import com.indracompany.treinamento.model.dto.OperacaoDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "operacoesWesley")
@Data
@NoArgsConstructor

public class Operacao extends  GenericEntity<Long>{
    public Operacao(OperacaoDTO dto){
        this.data_operacao = dto.getDataOperacao();
        this.tipo_operacao = dto.getTiposOperacao();
        this.numero = dto.getNumero();
        this.agencia = dto.getAgencia();
        this.valor = dto.getValor();
    }




    public enum TiposOperacao { SAQUE, TRANSFERENCIA, DEPOSITO }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 4)
    private String agencia;

    @Column(length = 6)
    private String numero;

    @Column
    private TiposOperacao tipo_operacao;

    @Column
    private double valor;

    @Column
    private LocalDateTime data_operacao;


}
