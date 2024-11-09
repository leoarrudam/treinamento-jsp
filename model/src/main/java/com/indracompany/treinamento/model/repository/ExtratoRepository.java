package com.indracompany.treinamento.model.repository;

import com.indracompany.treinamento.model.entity.Extrato;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExtratoRepository extends GenericCrudRepository<Extrato, Long>{
    List<Extrato> findAllByNumeroOrigemAndAgenciaOrigem (String numeroOrigem, String agenciaOrigem);
}
