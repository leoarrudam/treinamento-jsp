package com.indracompany.treinamento.model.repository;

import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.entity.ContaBancaria;

import java.util.List;

public interface ContaBancariaRepository extends GenericCrudRepository<ContaBancaria, Long>{

    public List<ContaBancaria> findByClientCpf(String cpf);

    public ContaBancaria findByAgenciaAndNumero(String agencia, String numero);

    public List<ContaBancaria> findByClient(Cliente cliente);
}
