package com.indracompany.treinamento.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.Transacoes;


@Repository
public interface TransacoesRepository extends JpaRepository<Transacoes, Long>{
	
	List<Transacoes> findByConta(ContaBancaria Conta);
	
}
