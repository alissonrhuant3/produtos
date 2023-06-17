package com.cadastro.produtos.repository;

import org.springframework.data.repository.CrudRepository;

import com.cadastro.produtos.models.Produtos;

public interface ProdutosRepository extends CrudRepository<Produtos, String> {
	Produtos findByCodigoProduto(long codigoProduto);
	Produtos deleteByCodigoProduto(long codigoProduto);
	
}
