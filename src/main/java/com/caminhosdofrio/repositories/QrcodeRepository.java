package com.caminhosdofrio.repositories;

import com.caminhosdofrio.exceptions.entidades.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QrcodeRepository extends JpaRepository<QrCode, Long> {
}
