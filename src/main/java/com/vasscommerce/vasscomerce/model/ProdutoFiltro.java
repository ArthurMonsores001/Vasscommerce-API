package com.vasscommerce.vasscomerce.model;

import com.vasscommerce.vasscomerce.entity.Produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

final class ProdutoFiltro {

    private ProdutoFiltro() {
    }

    static List<Produto> aplicar(List<Produto> produtos, String nome, BigDecimal min, BigDecimal max) {
        String termo = (nome == null || nome.isBlank()) ? null : nome.trim().toLowerCase(Locale.ROOT);
        return produtos.stream()
                .filter(p -> termo == null || p.getNome().toLowerCase(Locale.ROOT).contains(termo))
                .filter(p -> min == null || p.getValor().compareTo(min) >= 0)
                .filter(p -> max == null || p.getValor().compareTo(max) <= 0)
                .toList();
    }
}