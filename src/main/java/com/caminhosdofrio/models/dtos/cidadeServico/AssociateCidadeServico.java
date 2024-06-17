package com.caminhosdofrio.models.dtos.cidadeServico;

import io.swagger.v3.oas.annotations.media.Schema;

public record AssociateCidadeServico(
        @Schema(description = "Id do Serviço turistico", example = "1")
        Long servicoId
) {
}
