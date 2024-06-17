package com.caminhosdofrio.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ErrorDTO(
        @Schema(description = "Status do erro", example = "400")
        int status,

        @Schema(description = "Mensagem do erro", example = "Not Found")
        String message,

        @Schema(description = "Hora do erro", example = "2024-06-17T00:14:57.705+00:00")
        LocalDateTime timestamp
) {
}
