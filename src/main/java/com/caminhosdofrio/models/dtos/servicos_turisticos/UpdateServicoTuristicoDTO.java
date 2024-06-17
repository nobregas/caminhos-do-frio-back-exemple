package com.caminhosdofrio.models.dtos.servicos_turisticos;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateServicoTuristicoDTO(

        @Schema(description = "Nome do serviço turistico", example = "Hotelaria...")
        String nome,

        @Schema(description = "Descrição do serviço turistico", example = "Somos uma hotelaria ...")
        String descricao,

        @Schema(description = "Cnpj do serviço turistico", example = "44.853.360/0001-01")
        String cnpj,

        @Schema(description = "Url de alguma imagem do serviço", example = "https:xxxxxxx.com.br")
        String imagem
) {
}
