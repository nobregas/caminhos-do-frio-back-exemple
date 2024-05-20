package com.caminhosdofrio.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.caminhosdofrio.dtos.cidades.CreateCidadeDTO;
import com.caminhosdofrio.dtos.cidades.UpdateCidadeDTO;
import com.caminhosdofrio.exceptions.cidades.CidadeNotFoundException;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.caminhosdofrio.exceptions.entidades.Cidade;
import com.caminhosdofrio.repositories.CidadeRepository;

@Service
@RequiredArgsConstructor
public class CidadeService {

	private final String uri = "http://localhost:8080/cidades/";

	private final CidadeRepository cidadeRepository;
	private final QrcodeService qrcodeService;

	public List<Cidade> listarCidades() {
		return cidadeRepository.findAll();
	}

	public Optional<Cidade> obterCidadePorID(Long id) {
		return cidadeRepository.findById(id);
	}

	public Cidade criarCidade(CreateCidadeDTO dto) throws IOException, WriterException {
		var cidadeCriada = cidadeRepository.save(new Cidade(dto));

		var url = gerarUrl(cidadeCriada);
		var qrCode = qrcodeService.criarQrCode(url);

		cidadeCriada.setQrCode(qrCode);
		return cidadeRepository.save(cidadeCriada);
	}

	public boolean deletarCidadePorId(Long id) {
		var exists = cidadeRepository.existsById(id);
		boolean deleted = false;

		if (exists) {
			deleted = true;
			cidadeRepository.deleteById(id);
		}
		return deleted;
	}

	public Cidade atualizarCidade(Long id, UpdateCidadeDTO dto) {
		var cidadeOptional = cidadeRepository.findById(id);

		if(cidadeOptional.isPresent()) {
			Cidade cidade = cidadeOptional.get();

			if (!dto.nome().isBlank()) {
				cidade.setNome(dto.nome());
			}
			if (!dto.descricao().isBlank()) {
				cidade.setDescricao(dto.descricao());
			}
			if (!dto.urlDaImagem().isBlank()) {
				cidade.setUrlDaImagem(dto.urlDaImagem());
			}
			return cidade;
		} else {
			throw new CidadeNotFoundException();
		}
	}

	public String gerarUrl(Cidade cidade) {
		return uri + cidade.getId();
	}

}
