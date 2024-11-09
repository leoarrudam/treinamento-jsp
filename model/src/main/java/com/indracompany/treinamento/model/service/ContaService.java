package com.indracompany.treinamento.model.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.DepositoDTO;
import com.indracompany.treinamento.model.dto.SaqueDTO;
import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.entity.Conta;
import com.indracompany.treinamento.model.repository.ContaRepository;

import antlr.collections.List;

@Service
public class ContaService extends GenericCrudService<Conta, Long, ContaRepository> {

    @Autowired
    private ContaRepository contaRepository;

    /**
     * 
     * @param dto 
     */
    public void depositar(DepositoDTO dto) {
        Conta conta = contaRepository
                .findByAgenciaAndNumero(dto.getAgencia(), dto.getNumeroConta());
        if (conta == null) {
            throw new AplicacaoException("Conta bancária não encontrada.");
        }
        conta.setSaldo(conta.getSaldo() + dto.getValor());
        super.salvar(conta);
    }

    /**
     * 
     * @param dto 
     */
    public void sacar(SaqueDTO dto) {
        Conta conta = contaRepository
                .findByAgenciaAndNumero(dto.getAgencia(), dto.getNumeroConta());
        if (conta == null) {
            throw new AplicacaoException("Conta bancária não encontrada.");
        }
        if (conta.getSaldo() < dto.getValor()) {
            throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INEXISTENTE);
        }
        conta.setSaldo(conta.getSaldo() - dto.getValor());
        super.salvar(conta);
    }

    /**
     * @param transferenciaDto 
     */
    @Transactional(rollbackOn = Exception.class)
    public void transferir(TransferenciaDTO transferenciaDto) {
        SaqueDTO saqueDto = new SaqueDTO();
        saqueDto.setAgencia(transferenciaDto.getAgenciaOrigem());
        saqueDto.setNumeroConta(transferenciaDto.getAgenciaOrigem());
        saqueDto.setValor(transferenciaDto.getValor());
        this.sacar(saqueDto);
        
        DepositoDTO depositoDto = new DepositoDTO();
        depositoDto.setAgencia(transferenciaDto.getAgenciaDestino());
        depositoDto.setNumeroConta(transferenciaDto.getAgenciaDestino());
        depositoDto.setValor(transferenciaDto.getValor());
        this.depositar(depositoDto);
    }

    /**
     * 
     * @param cliente 
     * @return 
     */
    public List<Conta> listarContasPorCliente(Cliente cliente) {
        Object contaBancariaRepository;
                return contaBancariaRepository.findByClienteCpf(cliente.getCpf());
    }
}