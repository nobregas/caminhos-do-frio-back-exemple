package com.caminhosdofrio.models.dtos.cidades;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CreateCidadeDTO(

     @NotBlank(message = "O campo nome é obrigatório")
     @Schema(description = "Nome da cidade", example = "Campina Grande")
     String nome,

     @NotBlank(message = "O campo descrição é obrigatório")
     @Schema(description = "Descrição da cidade", example = "A cidade x é conhecida por...")
     String descricao,

     @Schema(description = "Url de alguma imagem da cidade", example = "https:xxxxxxx.com.br")
     String urlDaImagem
) {
}
