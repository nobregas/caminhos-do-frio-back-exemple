package com.caminhosdofrio.models.dtos.cidadeServico;

import com.caminhosdofrio.models.entidades.ServicoTuristico;
import io.swagger.v3.oas.annotations.media.Schema;

public record CidadeServicoResponse(
        @Schema(description = "Id do Serviço turistico", example = "1")
        Long servicoId,

        @Schema(description = "Nome do serviço turistico", example = "Hotelaria adasds")
        String nome,

        @Schema(description = "Descrição do serviço turistico", example = "Somos uma hotelaria ...")
        String descricao,

        @Schema(description = "Cnpj do serviço turistico", example = "44.853.360/0001-01")
        String cnpj,

        @Schema(description = "Url de alguma imagem do serviço", example = "https:xxxxxxx.com.br")
        String imagem
) {
    public CidadeServicoResponse(ServicoTuristico servicoTuristico) {
        this(
                servicoTuristico.getId(),
                servicoTuristico.getNome(),
                servicoTuristico.getDescricao(),
                servicoTuristico.getCnpj(),
                servicoTuristico.getImagem()
        );
    }
}
