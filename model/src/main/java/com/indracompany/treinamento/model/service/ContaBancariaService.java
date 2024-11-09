package com.indracompany.treinamento.model.service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.DepositoDTO;
import com.indracompany.treinamento.model.dto.SaqueDTO;
import com.indracompany.treinamento.model.dto.TransferenciaBancariaDTO;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContaBancariaService extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository> {

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    public void depositar(DepositoDTO dto) {
        ContaBancaria conta = contaBancariaRepository
                .findByAgenciaAndNumero(dto.getAgencia(), dto.getNumeroConta());

        conta.setSaldo(conta.getSaldo() + dto.getValor());

        contaBancariaRepository.save(conta);
    }

    public void sacar(SaqueDTO dto) {
        ContaBancaria conta = contaBancariaRepository
                .findByAgenciaAndNumero(dto.getAgencia(), dto.getNumeroConta());

        if (conta.getSaldo() < dto.getValor()) {
            throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INEXISTENTE);
        }

        conta.setSaldo(conta.getSaldo() - dto.getValor());

        contaBancariaRepository.save(conta);
    }

    @Transactional(rollbackFor = Exception.class)
    public void transferir(TransferenciaBancariaDTO transferenciaDto) {
        SaqueDTO saqueDto = new SaqueDTO();
        saqueDto.setAgencia(transferenciaDto.getAgenciaOrigem());
        saqueDto.setNumeroConta(transferenciaDto.getNumeroContaOrigem());
        saqueDto.setValor(transferenciaDto.getValor());

        this.sacar(saqueDto);

        DepositoDTO depositoDto = new DepositoDTO();
        depositoDto.setAgencia(transferenciaDto.getAgenciaDestino());
        depositoDto.setNumeroConta(transferenciaDto.getNumeroContaDestino());
        depositoDto.setValor(transferenciaDto.getValor());

        this.depositar(depositoDto);
    }
}
