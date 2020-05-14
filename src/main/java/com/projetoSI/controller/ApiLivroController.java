package com.projetoSI.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoSI.model.Livro;
import com.projetoSI.repository.ApiLivroRepository;

@RestController
@RequestMapping("/livros")
public class ApiLivroController {
	
	/*
	 * Endpoints:
	 * 	1 - Listar - getLivros()
	 *  2 - Obter um livro pelo id - getLivroId(Int id)
	 *  3 - Cadastrar - addLivro(Livro livro)
	 *  4 - Editar - editLivro(Livro livro)
	 *  5 - Excluir livro - deleteLivro(Int id)
	 *  
	 */
		
	@GetMapping()
	public List<Livro> getLivros() {
		ApiLivroRepository livroRepository = new ApiLivroRepository();
		return livroRepository.getAll();
	}
	
	@GetMapping("/{id}")
	public Livro getLivroId(@PathVariable int id) {
		ApiLivroRepository livroRepository = new ApiLivroRepository();
		
		return livroRepository.getById(id);
	}
	
	@PostMapping()
	public void addLivro(@RequestBody Livro livro) {
		ApiLivroRepository livroRepository = new ApiLivroRepository();
		
		livroRepository.add(livro);
	}
	
	@PutMapping("/atualizar/{id}")
	public void editLivro(@PathVariable int id, @RequestBody Livro livro) {
		
	}

	@DeleteMapping("/{id}")
	public void deleteLivro(@PathVariable int id) {
		
	}
}
