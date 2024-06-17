package com.caminhosdofrio.models.dtos.servicos_turisticos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

public record CreateServicoTuristicoDTO(
       @NotBlank(message = "campo nome é obrigatório")
       @Schema(description = "Nome do serviço turistico", example = "Hotelaria...")
       String nome,

       @NotBlank (message = "campo descricao é obrigatório")
       @Schema(description = "Descrição do serviço turistico", example = "Somos uma hotelaria ...")
       String descricao,

       @CNPJ(message = "Deve inserir um cnpj válido")
       @NotBlank (message = "campo cnpj é obrigatório")
       @Schema(description = "Cnpj do serviço turistico", example = "44.853.360/0001-01")
       String cnpj,


       @NotBlank (message = "campo imagem é obrigatório")
       @Schema(description = "Url de alguma imagem do serviço", example = "https:xxxxxxx.com.br")
       String imagem
) {
}
