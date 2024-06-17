package com.caminhosdofrio.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.caminhosdofrio.models.dtos.ErrorDTO;
import com.caminhosdofrio.models.dtos.cidadeServico.AssociateCidadeServico;
import com.caminhosdofrio.models.dtos.cidadeServico.CidadeServicoResponse;
import com.caminhosdofrio.models.dtos.cidades.CidadeResponse;
import com.caminhosdofrio.models.dtos.cidades.CreateCidadeDTO;
import com.caminhosdofrio.models.dtos.cidades.UpdateCidadeDTO;
import com.caminhosdofrio.models.entidades.QrCode;
import com.caminhosdofrio.services.CidadeService;
import com.google.zxing.WriterException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cidades")
@RequiredArgsConstructor
public class CidadeController {

	private final CidadeService cidadeService;

	@Operation(summary = "Cria uma nova cidade")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "201",
					description = "Cidade criada com sucesso",
					content = {
							@Content(mediaType = "application/json",
							schema = @Schema(implementation = CidadeResponse.class))
					}
			),
			@ApiResponse(
					responseCode = "400",
					description = "Um ou mais campos inválidos",
					content = {
							@Content(mediaType = "application/json",
									schema = @Schema(implementation = Map.class))
					}
			)
	})
	@PostMapping
	public ResponseEntity<CidadeResponse> criarCidade(@RequestBody @Validated CreateCidadeDTO dto)
			throws IOException, WriterException
	{
		var cidade = cidadeService.criarCidade(dto);
		return new ResponseEntity<>(cidade, HttpStatus.CREATED);
	}

	@Operation(summary = "Lista todas as cidades no sistema")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Cidade listada com sucesso",
					content = {
							@Content(mediaType = "application/json",
									schema = @Schema(implementation = CidadeResponse.class))
					}
			)
	})
	@GetMapping
	public ResponseEntity<List<CidadeResponse>> listarCidades() {

		List<CidadeResponse> cidadesList = cidadeService.listarCidades();
		return ResponseEntity.ok().body(cidadesList);
	}

	@Operation(summary = "Cria uma nova cidade")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Cidade encontrada com sucesso",
					content = {
							@Content(mediaType = "application/json",
									schema = @Schema(implementation = CidadeResponse.class))
					}
			),
			@ApiResponse(
					responseCode = "400",
					description = "Tipo do id inválido",
					content = {
							@Content(mediaType = "application/json",
									schema = @Schema(implementation = ErrorDTO.class))
					}
			),
			@ApiResponse(
					responseCode = "404",
					description = "Cidade não encontrada",
					content = {
							@Content(mediaType = "application/json",
									schema = @Schema(implementation = ErrorDTO.class))
					}
			)
	})
	@GetMapping("/{id}")
	public ResponseEntity<CidadeResponse> pesquisarCidadePorId(
			@PathVariable @Parameter(description = "Id da cidade que deseja visualizar", example = "1")
			Long id)
	{
		var cidade = cidadeService.obterCidadePorID(id);

		return ResponseEntity.ok().body(cidade);
	}

	@Operation(summary = "Deleta uma cidade do sistema")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "204",
					description = "Cidade deletada com sucesso"
			),
			@ApiResponse(
					responseCode = "400",
					description = "Tipo do id inválido",
					content = {
							@Content(mediaType = "application/json",
									schema = @Schema(implementation = ErrorDTO.class))
					}
			),
			@ApiResponse(
					responseCode = "404",
					description = "Cidade não encontrada",
					content = {
							@Content(mediaType = "application/json",
									schema = @Schema(implementation = ErrorDTO.class))
					}
			)
	})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deletarUsuarioPorId(
			@PathVariable @Parameter(description = "Id da cidade que deseja deletar", example = "1")
			Long id)
	{
		boolean deleted = cidadeService.deletarCidadePorId(id);
		var responseEntity = ResponseEntity.notFound().build();

		if (deleted) {
			responseEntity = ResponseEntity.noContent().build();
		}
		return responseEntity;
	}

	@Operation(summary = "Atualiza os dados de uma cidade do sistema")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Cidade atualizada com sucesso",
					content = {
							@Content(mediaType = "application/json",
									schema = @Schema(implementation = CidadeResponse.class))
					}
			),
			@ApiResponse(
					responseCode = "400",
					description = "Tipo do id inválido",
					content = {
							@Content(mediaType = "application/json",
									schema = @Schema(implementation = ErrorDTO.class))
					}
			),
			@ApiResponse(
					responseCode = "404",
					description = "Cidade não encontrada",
					content = {
							@Content(mediaType = "application/json",
									schema = @Schema(implementation = ErrorDTO.class))
					}
			)
	})
	@PatchMapping("/{id}")
	public ResponseEntity<CidadeResponse> atualizarCidade(
			@RequestBody @Validated UpdateCidadeDTO dto,
			@PathVariable @Parameter(description = "Id da cidade que deseja atualizar", example = "1")
			Long id) {

		return ResponseEntity.ok(cidadeService.atualizarCidade(id, dto));
	}

	// QRCODE
	@Operation(summary = "Obtém o qrcode de uma cidade")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Qrcode encontrado com sucesso",
					content = {
							@Content(mediaType = "application/json",
									schema = @Schema(implementation = QrCode.class))
					}
			),
			@ApiResponse(
					responseCode = "400",
					description = "Tipo do id inválido",
					content = {
							@Content(mediaType = "application/json",
									schema = @Schema(implementation = ErrorDTO.class))
					}
			),
			@ApiResponse(
					responseCode = "404",
					description = "Cidade não encontrada",
					content = {
							@Content(mediaType = "application/json",
									schema = @Schema(implementation = ErrorDTO.class))
					}
			)
	})
	@GetMapping("/{id}/qrcode")
	public ResponseEntity<QrCode> obterQRCode(
			@PathVariable @Parameter(description = "Id da cidade que deseja obter o qrcode", example = "1")
			Long id) {
		var qrcode = cidadeService.obterQrcode(id);
		return ResponseEntity.ok(qrcode);
	}

	//RELACAO ENTRE CIDADE E SERVICO:

	@Operation(summary = "Associa um servico existente à uma cidade")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "204",
					description = "Servico associado com sucesso"
			),
			@ApiResponse(
					responseCode = "400",
					description = "Tipo do id inválido",
					content = {
							@Content(mediaType = "application/json",
									schema = @Schema(implementation = ErrorDTO.class))
					}
			),
			@ApiResponse(
					responseCode = "404",
					description = "Cidade não encontrada",
					content = {
							@Content(mediaType = "application/json",
									schema = @Schema(implementation = ErrorDTO.class))
					}
			),
			@ApiResponse(
					responseCode = "404",
					description = "Serviço não encontrado",
					content = {
							@Content(mediaType = "application/json",
									schema = @Schema(implementation = ErrorDTO.class))
					}
			)
	})
	@PostMapping("/{cidadeId}/servicos")
	public ResponseEntity<Void> associarServico(
			@PathVariable("cidadeId")
			@Parameter(description = "Id da cidade que deseja associar", example = "1")
			Long cidadeId,
			@RequestBody AssociateCidadeServico dto
	) {

		cidadeService.associateServico(cidadeId, dto);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Lista todos os serviços de uma cidade")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Serviços listados com sucesso",
					content = {
							@Content(mediaType = "application/json",
									schema = @Schema(implementation = CidadeServicoResponse.class))
					}
			),
			@ApiResponse(
					responseCode = "400",
					description = "Tipo do id inválido",
					content = {
							@Content(mediaType = "application/json",
									schema = @Schema(implementation = ErrorDTO.class))
					}
			),
			@ApiResponse(
					responseCode = "404",
					description = "Cidade não encontrada",
					content = {
							@Content(mediaType = "application/json",
									schema = @Schema(implementation = ErrorDTO.class))
					}
			)
	})
	@GetMapping("/{cidadeId}/servicos")
	public ResponseEntity<List<CidadeServicoResponse>> listarServicos(
			@PathVariable("cidadeId")
			@Parameter(description = "Id da cidade que deseja visualizar seus serviços")
			Long cidadeId
	) {

		var servicos = cidadeService.listarServicos(cidadeId);
		return ResponseEntity.ok(servicos);
	}
}
