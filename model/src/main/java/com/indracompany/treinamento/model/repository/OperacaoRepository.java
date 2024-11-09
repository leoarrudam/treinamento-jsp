package com.indracompany.treinamento.model.repository;


import com.indracompany.treinamento.model.entity.Operacao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperacaoRepository extends GenericCrudRepository<Operacao, Long>{


    public List<Operacao> findByAgenciaAndNumero(String agencia, String numero);
}
