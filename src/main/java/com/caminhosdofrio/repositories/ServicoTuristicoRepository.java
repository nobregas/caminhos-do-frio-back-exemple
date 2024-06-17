package com.caminhosdofrio.repositories;

import com.caminhosdofrio.models.entidades.ServicoTuristico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoTuristicoRepository extends JpaRepository<ServicoTuristico, Long> {
    boolean existsByCnpj(String cnpj);
}