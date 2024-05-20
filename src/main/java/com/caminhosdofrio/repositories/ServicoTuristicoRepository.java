package com.caminhosdofrio.repositories;

import com.caminhosdofrio.exceptions.entidades.ServicoTuristico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoTuristicoRepository extends JpaRepository<ServicoTuristico, Long> {
}