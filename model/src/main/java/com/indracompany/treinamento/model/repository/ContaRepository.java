package com.indracompany.treinamento.model.repository;

import com.indracompany.treinamento.model.entity.Conta;

import antlr.collections.List;

public interface ContaRepository extends GenericCrudRepository<Conta, Long> {
    
    /**
     * @param cpf 
     * @return 
     */
    List<Conta> findByClienteCpf(String cpf);
    
    /**
     * @param agencia 
     * @param numero 
     * @return 
     */
    Conta findByAgenciaAndNumero(String agencia, String numero);
}