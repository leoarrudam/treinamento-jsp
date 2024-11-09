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

import lombok.Data;
//import lombok.EqualsAndHashCode;

@Entity
@Table(name = "transacoes_conta")
@Data
//@EqualsAndHashCode(callSuper = true)
public class Transacoes {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	
	 @ManyToOne
	 @JoinColumn(name = "fk_conta_bancaria_id", nullable = false)
	 private ContaBancaria conta;
	    
	 @Column(nullable = false)
	 private String tipoOperacao;

	 @Column(nullable = false)
	 private double valor;
	    
	 @Column(nullable = false)
	 private LocalDateTime dataOperacao;
	    
	 private String contaDestino;

}
