package com.caminhosdofrio.models.dtos.cidades;

import com.caminhosdofrio.models.entidades.Cidade;
import io.swagger.v3.oas.annotations.media.Schema;

public record CidadeResponse(

        @Schema(description = "Id da cidade", example = "1")
        Long id,

        @Schema(description = "Nome da cidade", example = "Campina Grande")
        String nome,

        @Schema(description = "Descrição da cidade", example = "A cidade x é conhecida por...")
        String descricao,

        @Schema(description = "Url de alguma imagem da cidade", example = "https:xxxxxxx.com.br")
        String urlDaImagem
) {
    public CidadeResponse(Cidade cidade) {
        this(
                cidade.getId(),
                cidade.getNome(),
                cidade.getDescricao(),
                cidade.getUrlDaImagem()
        );
    }
}
