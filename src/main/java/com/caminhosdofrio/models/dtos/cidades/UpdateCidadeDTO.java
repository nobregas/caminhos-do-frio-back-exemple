package com.caminhosdofrio.models.dtos.cidades;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateCidadeDTO(

        @Schema(description = "Nome da cidade", example = "Campina Grande")
        String nome,

        @Schema(description = "Descrição da cidade", example = "A cidade x é conhecida por...")
        String descricao,

        @Schema(description = "Url de alguma imagem da cidade", example = "https:xxxxxxx.com.br")
        String urlDaImagem
) {
}
