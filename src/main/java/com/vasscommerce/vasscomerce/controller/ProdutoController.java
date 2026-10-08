package com.vasscommerce.vasscomerce.controller;

import com.vasscommerce.vasscomerce.dto.ProdutoCreateRequest;
import com.vasscommerce.vasscomerce.dto.ProdutoResponse;
import com.vasscommerce.vasscomerce.entity.Produto;
import com.vasscommerce.vasscomerce.mapper.ProdutoMapper;
import com.vasscommerce.vasscomerce.model.ProdutoModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/produto", produces = "application/json")
public class ProdutoController {

    private final ProdutoModel model;

    public ProdutoController(ProdutoModel model) {
        this.model = model;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<ProdutoResponse> create(@Valid @RequestBody ProdutoCreateRequest body) {
        Produto created = model.criar(body);
        URI location = URI.create("/produto/" + created.getId());
        return ResponseEntity.created(location).body(ProdutoMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> get(@PathVariable Long id) {
        return model.obterPorId(id)
                .map(p -> ResponseEntity.ok(ProdutoMapper.toResponse(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> list(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) BigDecimal valorMinimo,
            @RequestParam(required = false) BigDecimal valorMaximo) {

        List<Produto> found = model.buscar(nome, valorMinimo, valorMaximo);
        return ResponseEntity.ok(found.stream().map(ProdutoMapper::toResponse).toList());
    }
}