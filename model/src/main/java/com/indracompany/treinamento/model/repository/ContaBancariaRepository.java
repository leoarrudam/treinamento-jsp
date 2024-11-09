package com.indracompany.treinamento.model.repository;

import com.indracompany.treinamento.model.entity.ContaBancaria;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaBancariaRepository extends GenericCrudRepository<ContaBancaria, Long> {

    public ContaBancaria findByAgenciaAndNumero(String agencia, String numero);
}
