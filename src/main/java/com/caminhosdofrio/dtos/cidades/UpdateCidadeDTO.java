package com.caminhosdofrio.dtos.cidades;

public record UpdateCidadeDTO(
        String nome,
        String descricao,
        String urlDaImagem
) {
}
