package com.vasscommerce.vasscomerce.model;

import com.vasscommerce.vasscomerce.dto.ProdutoCreateRequest;
import com.vasscommerce.vasscomerce.entity.Produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProdutoModel {

    Produto criar(ProdutoCreateRequest req);

    Optional<Produto> obterPorId(Long id);

    List<Produto> buscar(String nome, BigDecimal valorMinimo, BigDecimal valorMaximo);
}