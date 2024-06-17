package com.caminhosdofrio.controllers;

import com.caminhosdofrio.models.dtos.ErrorDTO;
import com.caminhosdofrio.models.dtos.cidades.CidadeResponse;
import com.caminhosdofrio.models.dtos.servicos_turisticos.CreateServicoTuristicoDTO;
import com.caminhosdofrio.models.dtos.servicos_turisticos.UpdateServicoTuristicoDTO;
import com.caminhosdofrio.models.entidades.ServicoTuristico;
import com.caminhosdofrio.services.exceptions.servico_turistico.UniqueCnpjException;
import com.caminhosdofrio.services.ServicoTuristicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/servicos_turisticos")
@RequiredArgsConstructor
public class ServicoTuristicoController {

    private final ServicoTuristicoService servicoTuristicoService;

    @Operation(summary = "Lista todos os serviços turisticos no bando de dados")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Serviços listados com sucesso",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ServicoTuristico.class))
                    }
            )
    })
    @GetMapping
    public ResponseEntity<List<ServicoTuristico>> listarServicosTuristicos() {
        return ResponseEntity.ok(servicoTuristicoService.listar());
    }

    @Operation(summary = "Obtém um serviço turistico")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Serviço encontrado com sucesso",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ServicoTuristico.class))
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
                    description = "Serviço não encontrado",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDTO.class))
                    }
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ServicoTuristico> obterServicoTuristicoPorId(
            @PathVariable
            @Parameter(description = "Id do serviço turistico que deseja obter", example = "1")
            Long id) {
        return ResponseEntity.ok(servicoTuristicoService.obterPorId(id));
    }

    @Operation(summary = "Cria um novo serviço turistico")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Serviço criado com sucesso",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ServicoTuristico.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Um ou mais campos inválidos",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Map.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Cnpj é único",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDTO.class))
                    }
            ),
    })
    @PostMapping
    public ResponseEntity<ServicoTuristico> criarServicoTuristico(@RequestBody CreateServicoTuristicoDTO dto) {
        return new ResponseEntity<>(servicoTuristicoService.salvar(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Deleta um serviço turistico")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Serviço deletado com sucesso"
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirServicoTuristicoPorId(
            @PathVariable
            @Parameter(description = "Id do serviço turistico que deseja deletar", example = "1")
            Long id) {
        servicoTuristicoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza os dados de um serviço turistico")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Serviço atualizado com sucesso",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ServicoTuristico.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Cnpj é único",
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
    @PatchMapping("/{id}")
    public ResponseEntity<ServicoTuristico> atualizarServicoTuristicoPorId(
            @PathVariable
            @Parameter(description = "Id do serviço turistico que deseja atualizar", example = "1")
            Long id,
            @RequestBody UpdateServicoTuristicoDTO dto
    ) throws UniqueCnpjException {
        return ResponseEntity.ok(servicoTuristicoService.atualizarPorId(id, dto));
    }

}
