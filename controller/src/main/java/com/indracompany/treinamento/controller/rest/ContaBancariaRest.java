package com.indracompany.treinamento.controller.rest;

import com.indracompany.treinamento.model.dto.*;
import com.indracompany.treinamento.model.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.service.ContaBancariaService;

import java.util.List;

@RestController
@RequestMapping("rest/contas")
public class ContaBancariaRest 
	extends GenericCrudRest<ContaBancaria, Long, ContaBancariaService>{

	
	@Autowired
	private ContaBancariaService contaBancariaService;
	@Autowired
	private TransacaoService transacaoService;
	
	@PutMapping(value = "/depositar", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Void> depositar(@RequestBody DepositoDTO dto){
		contaBancariaService.depositar(dto);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping(value = "/sacar", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Void> sacar(@RequestBody SaqueDTO dto){
		contaBancariaService.sacar(dto);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@PutMapping(value = "/transferir", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Void> transferir(
			@RequestBody TransferenciaBancariaDTO dto){
		contaBancariaService.transferir(dto);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping(value = "/extrato", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<ExtratoDTO>> buscarExtrato(@RequestBody BuscarExtratoDTO extratoDTO){
		List<ExtratoDTO> extratoDTOList = transacaoService.buscarExtrato(extratoDTO.getAgencia(), extratoDTO.getNumero());
		return new ResponseEntity<>(extratoDTOList, HttpStatus.OK);
	}
}
