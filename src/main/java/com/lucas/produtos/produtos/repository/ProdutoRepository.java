package com.lucas.produtos.produtos.repository;

import com.lucas.produtos.produtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Já herda métodos: save(), findAll(), findById(), deleteById()
    boolean existsByNome(String nome);
}
