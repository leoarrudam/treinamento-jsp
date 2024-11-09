package com.indracompany.treinamento.model.repository;

import com.indracompany.treinamento.model.entity.Operacao;

import java.util.List;

public interface OperacaoRepository extends GenericCrudRepository<Operacao, Long> {

    List<Operacao> findAllByContaBancaria_AgenciaAndContaBancaria_Numero(String agencia, String numero);
}
