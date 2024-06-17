package com.caminhosdofrio.repositories;

import com.caminhosdofrio.models.entidades.CidadeServico;
import com.caminhosdofrio.models.entidades.CidadeServicoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeServicoRepository
    extends JpaRepository<CidadeServico, CidadeServicoId> {
}
