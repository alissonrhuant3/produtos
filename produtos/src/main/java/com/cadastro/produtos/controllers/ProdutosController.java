package com.cadastro.produtos.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.xml.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.cadastro.produtos.repository.ProdutosRepository;
import com.cadastro.produtos.models.Produtos;


@SuppressWarnings("unused")
@Controller
public class ProdutosController {
	
	private static String caminhoImagens = "C:\\Users\\ead\\Documents\\imagens\\";
	
	@Autowired
	private ProdutosRepository pr;

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	
	@RequestMapping(value="/cadastrodeprodutos", method=RequestMethod.GET)
	public String cadastrarProduto() {
		return "paginas/cadastrodeprodutos";
	}
	
	@RequestMapping(value="/cadastrodeprodutos", method=RequestMethod.POST)
	public String cadastrarProduto(Produtos produto) {
		pr.save(produto);
		return "redirect:/produtosvendedor";
	} 
	
	@RequestMapping("/produtos")
	public ModelAndView produtos() {
		ModelAndView mv = new ModelAndView("paginas/produtos");
		
		Iterable<Produtos> produtos = pr.findAll();
		mv.addObject("produtos", produtos);
		
		return mv;
	}
	
	@RequestMapping("/produtosvendedor")
	public ModelAndView produtosvendedor() {
		ModelAndView mv = new ModelAndView("paginas/produtos-vendedor");
		
		Iterable<Produtos> produtos = pr.findAll();
		mv.addObject("produtos", produtos);
		
		return mv;
	}
	
	@RequestMapping(value="/alterarProduto/{codigoProduto}", method=RequestMethod.GET)
	public ModelAndView formAlterarProduto(@PathVariable("codigoProduto") long codigoProduto) {
		
		Produtos produto = pr.findByCodigoProduto(codigoProduto);
		
		ModelAndView mv = new ModelAndView("paginas/atualizar-produto");
		
		mv.addObject("produto", produto);
		
		return mv;
		
	}
	
	@RequestMapping(value="/alterarProduto/{codigoProduto}", method=RequestMethod.POST)
	public String alterarLivro(@Validated Produtos produto, BindingResult result, RedirectAttributes attributes, @RequestParam("file") MultipartFile arquivo) {
		
		pr.save(produto);
		
		try {
			if(!arquivo.isEmpty()) {
				byte[] bytes = arquivo.getBytes();
				Path caminho = Paths.get(caminhoImagens+ String.valueOf(produto.getCodigoProduto()) +arquivo.getOriginalFilename());
				Files.write(caminho, bytes);
				
				produto.setNomeImagem(String.valueOf(produto.getCodigoProduto())+arquivo.getOriginalFilename());
				pr.save(produto);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		return "redirect:/produtosvendedor";
		
	}
	
	@GetMapping("/paginas/mostrarImagem/{imagem}")
	@ResponseBody
	public byte[] retornarImagem(@PathVariable("imagem") String imagem) throws IOException {
//		System.out.println(imagem);
		File imagemArquivo = new File(caminhoImagens + imagem);
		if (imagem != null || imagem.trim().length() > 0) {
			System.out.println("No IF");
			return Files.readAllBytes(imagemArquivo.toPath());
		}
		return null;
	}
	
	@RequestMapping("/confirmarCompra/{codigoProduto}")
	public ModelAndView confirmarCompra(@PathVariable("codigoProduto") long codigoProduto) {
		
		Produtos produto = pr.findByCodigoProduto(codigoProduto);
		
		ModelAndView mv = new ModelAndView("paginas/comprar-produto");
		
		mv.addObject("produto", produto);
		
		return mv;
	}
	
	 @RequestMapping("/excluirProduto")
	public String excluirProduto(long codigoProduto) {
		
		Produtos produto = pr.findByCodigoProduto(codigoProduto);
		pr.delete(produto);
		
		return "redirect:/produtos";
	} 
	
}
