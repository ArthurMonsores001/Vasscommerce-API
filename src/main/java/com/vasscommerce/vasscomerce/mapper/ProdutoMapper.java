package com.vasscommerce.vasscomerce.mapper;

import com.vasscommerce.vasscomerce.dto.ProdutoCreateRequest;
import com.vasscommerce.vasscomerce.dto.ProdutoResponse;
import com.vasscommerce.vasscomerce.entity.Produto;

public final class ProdutoMapper {

    private ProdutoMapper() {
    }

    public static Produto toEntity(ProdutoCreateRequest req, Long id) {
        return new Produto(id, req.nome(), req.descricao(), req.valor(), req.categoriaId());
    }

    public static ProdutoResponse toResponse(Produto produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getValor(),
                produto.getCategoriaId()
        );
    }
}