package com.caminhosdofrio.models.entidades;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "qrcodes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Id do QRCode", example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Url do QRCode", example = "http://localhost:8080/cidade/1")
    private String url;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Schema(description = "QRCode em formato de texto")
    private String qrcode;
}
