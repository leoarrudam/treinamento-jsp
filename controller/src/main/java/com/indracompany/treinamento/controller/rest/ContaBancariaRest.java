package com.indracompany.treinamento.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.indracompany.treinamento.model.dto.ContaBancariaDTO;
import com.indracompany.treinamento.model.dto.DepositoDTO;
import com.indracompany.treinamento.model.dto.SaqueDTO;
import com.indracompany.treinamento.model.dto.ExtratoDTO;
import com.indracompany.treinamento.model.dto.TransferenciaBancariaDTO;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.service.ContaBancariaService;
import com.indracompany.treinamento.model.service.ExtratoService;

@RestController
@RequestMapping("rest/contas")
public class ContaBancariaRest extends GenericCrudRest<ContaBancaria, Long, ContaBancariaService> {
  @Autowired
  private ContaBancariaService contaBancariaService;

  @Autowired
  private ExtratoService extratoService;

  @PutMapping(value = "/depositar", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody ResponseEntity<Void> depositar(@RequestBody DepositoDTO dto) {
    contaBancariaService.depositar(dto, true);
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  @PutMapping(value = "/sacar", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody ResponseEntity<Void> sacar(@RequestBody SaqueDTO dto) {
    contaBancariaService.sacar(dto, true);
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  @PutMapping(value = "/transferir", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody ResponseEntity<Void> transferir(@RequestBody TransferenciaBancariaDTO dto) {
    contaBancariaService.transferir(dto);
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  @PostMapping(value = "/historico", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody ResponseEntity<List<ExtratoDTO>> historico(@RequestBody ContaBancariaDTO conta) {
    List<ExtratoDTO> transacoes = extratoService.buscarHistoricoPorConta(conta);
    return new ResponseEntity<>(transacoes, HttpStatus.OK);
  }

}
