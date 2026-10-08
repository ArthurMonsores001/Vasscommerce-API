package com.vasscommerce.vasscomerce.dto;

import java.math.BigDecimal;

public record ProdutoResponse(
        Long id,
        String nome,
        String descricao,
        BigDecimal valor,
        Long categoriaId
) {
}