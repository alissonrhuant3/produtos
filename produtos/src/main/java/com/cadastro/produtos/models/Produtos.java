package com.cadastro.produtos.models;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produtos implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long codigoProduto;
	
	private String nomeProduto;
	private String categoriaProduto;
	private String especificacaoProduto;
	private String descricaoProduto;
	private String nomeVendedor;
	private String telefoneVendedor;
	private String precoProduto;
	private String emailVendedor;
	private String nomeImagem;
	
	public Produtos() {
	
	}

	public long getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(long codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getCategoriaProduto() {
		return categoriaProduto;
	}

	public void setCategoriaProduto(String categoriaProduto) {
		this.categoriaProduto = categoriaProduto;
	}

	public String getEspecificacaoProduto() {
		return especificacaoProduto;
	}

	public void setEspecificacaoProduto(String especificacaoProduto) {
		this.especificacaoProduto = especificacaoProduto;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}

	public String getTelefoneVendedor() {
		return telefoneVendedor;
	}

	public void setTelefoneVendedor(String telefoneVendedor) {
		this.telefoneVendedor = telefoneVendedor;
	}

	public String getPrecoProduto() {
		return precoProduto;
	}

	public void setPrecoProduto(String precoProduto) {
		this.precoProduto = precoProduto;
	}

	public String getEmailVendedor() {
		return emailVendedor;
	}

	public void setEmailVendedor(String emailVendedor) {
		this.emailVendedor = emailVendedor;
	}

	public String getNomeImagem() {
		return nomeImagem;
	}

	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}
	
}