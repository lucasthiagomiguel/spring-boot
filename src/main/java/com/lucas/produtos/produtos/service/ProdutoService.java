package com.lucas.produtos.produtos.service;

import com.lucas.produtos.produtos.dto.ProdutoDTO;
import com.lucas.produtos.produtos.exception.ExistenteException;
import com.lucas.produtos.produtos.exception.NotFoundException;
import com.lucas.produtos.produtos.model.Produto;
import com.lucas.produtos.produtos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository repo;

    public ProdutoService(ProdutoRepository repo) {
        this.repo = repo;
    }

    public ProdutoDTO salvar(ProdutoDTO dto) {
        if (repo.existsByNome(dto.nome())) {
            throw new ExistenteException("Produto com esse nome já existe!");
        }
        Produto p = new Produto(dto.nome(), dto.preco(), dto.categoria());
        Produto salvo = repo.save(p);
        return new ProdutoDTO(salvo.getId(), salvo.getNome(), salvo.getPreco(), salvo.getCategoria());
    }

    public List<ProdutoDTO> listarTodos() {
        return repo.findAll()
                .stream()
                .map(p -> new ProdutoDTO(p.getId(), p.getNome(), p.getPreco(), p.getCategoria()))
                .collect(Collectors.toList());
    }

    public ProdutoDTO buscarPorId(Long id) {
        Produto p = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado!"));
        return new ProdutoDTO(p.getId(), p.getNome(), p.getPreco(), p.getCategoria());
    }

    public ProdutoDTO atualizar(Long id, ProdutoDTO dto) {
        Produto existente = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado!"));
        existente.setNome(dto.nome());
        existente.setPreco(dto.preco());
        existente.setCategoria(dto.categoria());
        Produto atualizado = repo.save(existente);
        return new ProdutoDTO(atualizado.getId(), atualizado.getNome(), atualizado.getPreco(), atualizado.getCategoria());
    }

    public void deletar(Long id) {
        Produto produto = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        produto.setDeleted(true);
        produto.setDeletedAt(LocalDateTime.now());
        repo.save(produto);
    }
}
