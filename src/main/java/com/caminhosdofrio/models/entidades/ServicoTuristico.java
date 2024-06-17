package com.caminhosdofrio.models.entidades;

import com.caminhosdofrio.models.dtos.servicos_turisticos.CreateServicoTuristicoDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "servicosTuristicos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicoTuristico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Id do serviço turistico", example = "1")
	private Long id;

	@Schema(description = "Nome do serviço turistico", example = "Hotelaria...")
	private String nome;

	@Schema(description = "Descrição do serviço turistico", example = "Somos uma hotelaria ...")
	private String descricao;


	@Column(unique = true, nullable = false)
	@Schema(description = "Cnpj do serviço turistico", example = "44.853.360/0001-01")
	private String cnpj;

	@Schema(description = "Url de alguma imagem do serviço", example = "https:xxxxxxx.com.br")
	private String imagem;

	public ServicoTuristico(CreateServicoTuristicoDTO dto) {
		this.nome = dto.nome();
		this.descricao = dto.descricao();
		this.cnpj = dto.cnpj();
		this.imagem = dto.imagem();
	}
}
