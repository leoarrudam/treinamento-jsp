package com.indracompany.treinamento.model.repository;

import com.indracompany.treinamento.model.entity.ContaBancaria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaBancariaRepository extends GenericCrudRepository<ContaBancaria, Long> {
    public List<ContaBancaria> findByClienteCpf(String cof);
    public ContaBancaria findByAgenciaAndNumero(String agencia, String numero);
}
