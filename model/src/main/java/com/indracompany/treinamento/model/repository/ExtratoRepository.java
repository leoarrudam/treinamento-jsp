package com.indracompany.treinamento.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.indracompany.treinamento.model.entity.Extrato;

public interface ExtratoRepository extends GenericCrudRepository<Extrato,Long> {
  @Query("SELECT t FROM Extrato t WHERE t.conta.agencia = :agencia AND t.conta.numero = :numero")
  List<Extrato> findByAgenciaAndConta(String agencia, String numero);
}
