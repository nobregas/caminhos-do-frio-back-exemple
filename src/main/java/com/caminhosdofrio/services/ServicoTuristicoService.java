package com.caminhosdofrio.services;

import com.caminhosdofrio.dtos.servicos_turisticos.CreateServicoTuristicoDTO;
import com.caminhosdofrio.dtos.servicos_turisticos.UpdateServicoTuristicoDTO;
import com.caminhosdofrio.exceptions.entidades.ServicoTuristico;
import com.caminhosdofrio.exceptions.validation.InvalidCnpjException;
import com.caminhosdofrio.exceptions.servico_turistico.ServicoTuristicoNotFoundException;
import com.caminhosdofrio.repositories.ServicoTuristicoRepository;
import com.caminhosdofrio.utils.CnpjUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoTuristicoService {

	private final ServicoTuristicoRepository servicoTuristicoRepository;

	public List<ServicoTuristico> listar() {
		return servicoTuristicoRepository.findAll();
	}

	public ServicoTuristico obterPorId(Long id) {
		return servicoTuristicoRepository.findById(id).orElseThrow(
                ServicoTuristicoNotFoundException::new
		);
	}

	public ServicoTuristico salvar(CreateServicoTuristicoDTO dto) {
		var servicoTuristico = new ServicoTuristico(dto);
		return servicoTuristicoRepository.save(servicoTuristico);
	}

	public void deletarPorId(Long id) {
		var servicoTuristico = servicoTuristicoRepository.findById(id).orElseThrow(
				ServicoTuristicoNotFoundException::new
		);

		servicoTuristicoRepository.delete(servicoTuristico);
	}

	public ServicoTuristico atualizarPorId(Long id, UpdateServicoTuristicoDTO dto) {
		var servicoTuristico = servicoTuristicoRepository.findById(id).orElseThrow(
				ServicoTuristicoNotFoundException::new
		);

		if(!dto.nome().isBlank()) {
			servicoTuristico.setNome(dto.nome());
		}
		if(!dto.descricao().isBlank()) {
			servicoTuristico.setDescricao(dto.descricao());
		}
		if(!dto.cnpj().isBlank()) {
			if(CnpjUtil.isValidCnpj(dto.cnpj())) {
				servicoTuristico.setCnpj(dto.cnpj());
			} else throw new InvalidCnpjException();

		}
		if(!dto.imagem().isBlank()) {
			servicoTuristico.setImagem(dto.imagem());
		}
		return servicoTuristicoRepository.save(servicoTuristico);
	}



}