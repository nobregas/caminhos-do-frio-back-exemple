package com.caminhosdofrio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caminhosdofrio.exceptions.entidades.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{

}
