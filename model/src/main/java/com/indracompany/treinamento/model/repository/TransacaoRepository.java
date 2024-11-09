package com.indracompany.treinamento.model.repository;

import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.Transacao;

import java.util.List;

public interface TransacaoRepository extends GenericCrudRepository<Transacao, Long>{
    List<Transacao> findAllByContaBancaria(ContaBancaria contaBancaria);
}
