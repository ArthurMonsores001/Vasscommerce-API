package com.vasscommerce.vasscomerce.model;

import com.vasscommerce.vasscomerce.dto.ProdutoCreateRequest;
import com.vasscommerce.vasscomerce.entity.Produto;
import com.vasscommerce.vasscomerce.mapper.ProdutoMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service("produtoModelMemoria")
@Profile("mem")
public class ProdutoModelMemoria implements ProdutoModel {

    private final List<Produto> banco = new ArrayList<>();
    private final AtomicLong seq = new AtomicLong(0);

    @Override
    public synchronized Produto criar(ProdutoCreateRequest req) {
        Long novoId = seq.incrementAndGet();
        Produto produto = ProdutoMapper.toEntity(req, novoId);
        banco.add(produto);
        return produto;
    }

    @Override
    public synchronized Optional<Produto> obterPorId(Long id) {
        return banco.stream().filter(p -> Objects.equals(p.getId(), id)).findFirst();
    }

    @Override
    public synchronized List<Produto> buscar(String nome, BigDecimal valorMinimo, BigDecimal valorMaximo) {
        return ProdutoFiltro.aplicar(banco, nome, valorMinimo, valorMaximo);
    }
}