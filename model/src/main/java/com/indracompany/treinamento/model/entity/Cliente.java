package com.indracompany.treinamento.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "cliente_caua_pires")
@EqualsAndHashCode(callSuper = true)
public class Cliente extends GenericEntity<Long>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50, nullable = false)
	private String nome;
	
	@CPF
	@Column(length = 11, nullable = false, unique = true)
	private String cpf;
	
	@Email
	@Column(nullable = false, unique = true)
	private String email;
	
	private boolean ativo;
	
	private String observacoes;

	public Cliente() {}

	//construtor
	
}
