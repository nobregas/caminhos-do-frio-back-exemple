package com.caminhosdofrio.controllers;

import java.io.IOException;
import java.util.List;

import com.caminhosdofrio.dtos.cidades.CreateCidadeDTO;
import com.caminhosdofrio.dtos.cidades.UpdateCidadeDTO;
import com.caminhosdofrio.services.CidadeService;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.caminhosdofrio.exceptions.entidades.Cidade;

@RestController
@RequestMapping("/cidades")
@RequiredArgsConstructor
public class CidadeController {

	private final CidadeService cidadeService;

	@PostMapping
	public ResponseEntity<Cidade> criarCidade(@RequestBody @Validated CreateCidadeDTO dto)
			throws IOException, WriterException
	{
		var cidade = cidadeService.criarCidade(dto);
		return new ResponseEntity<>(cidade, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Cidade>> listarCidades() {

		List<Cidade> cidadesList = cidadeService.listarCidades();
		return ResponseEntity.ok().body(cidadesList);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cidade> pesquisarCidadePorId(@PathVariable Long id) {
		var cidade = cidadeService.obterCidadePorID(id);
		ResponseEntity<Cidade> cidadeResponseEntity = null;

		if (cidade.isPresent()) {
			cidadeResponseEntity = ResponseEntity.ok(cidade.get());
		} else {
			cidadeResponseEntity = ResponseEntity.notFound().build();
		}
		return cidadeResponseEntity;
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deletarUsuarioPorId(@PathVariable Long id) {
		boolean deleted = cidadeService.deletarCidadePorId(id);
		var responseEntity = ResponseEntity.notFound().build();

		if (deleted) {
			responseEntity = ResponseEntity.noContent().build();
		}
		return responseEntity;
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> atualizarCidade(
			@RequestBody @Validated UpdateCidadeDTO dto,
			@PathVariable Long id) {

		return ResponseEntity.ok(cidadeService.atualizarCidade(id, dto));
	}
}
