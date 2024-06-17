package com.caminhosdofrio.models.entidades;

import com.caminhosdofrio.models.dtos.cidades.CreateCidadeDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cidades")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Id da cidade", example = "1")
	private Long id;

	@Schema(description = "Nome da cidade", example = "Campina Grande")
	private String nome;

	@Schema(description = "Descrição da cidade", example = "A cidade x é conhecida por...")
	private String descricao;

	@Schema(description = "Url de alguma imagem da cidade", example = "https:xxxxxxx.com.br")
	private String urlDaImagem;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "qrcode_id", referencedColumnName = "id")
	@Schema(implementation = QrCode.class)
	private QrCode qrCode;

	@OneToMany(mappedBy = "cidade")
	@Schema(implementation = CidadeServico.class)
	private List<CidadeServico> cidadesServicos = new ArrayList<>();

	public Cidade(CreateCidadeDTO dto) {
		this.nome = dto.nome();
		this.descricao = dto.descricao();
		this.urlDaImagem = dto.urlDaImagem();
	}
}
