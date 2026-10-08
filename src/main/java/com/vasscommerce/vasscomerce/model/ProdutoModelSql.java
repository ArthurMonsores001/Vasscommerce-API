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

@Service("produtoModelSql")
@Profile("sql")
public class ProdutoModelSql implements ProdutoModel {

    private static final long PRIMEIRO_ID = 1000L;

    private final List<Produto> tabelaProduto = new ArrayList<>();
    private final AtomicLong sequencia = new AtomicLong(PRIMEIRO_ID - 1);

    @Override
    public synchronized Produto criar(ProdutoCreateRequest req) {
        Long novoId = sequencia.incrementAndGet();
        Produto produto = ProdutoMapper.toEntity(req, novoId);
        tabelaProduto.add(produto);
        return produto;
    }

    @Override
    public synchronized Optional<Produto> obterPorId(Long id) {
        return tabelaProduto.stream().filter(p -> Objects.equals(p.getId(), id)).findFirst();
    }

    @Override
    public synchronized List<Produto> buscar(String nome, BigDecimal valorMinimo, BigDecimal valorMaximo) {
        return ProdutoFiltro.aplicar(tabelaProduto, nome, valorMinimo, valorMaximo);
    }
}