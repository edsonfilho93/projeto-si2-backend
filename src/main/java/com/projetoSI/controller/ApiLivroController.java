package com.projetoSI.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoSI.model.Livro;
import com.projetoSI.model.Response;
import com.projetoSI.repository.ApiLivroRepository;

@RestController
@RequestMapping("/livros")
public class ApiLivroController {

	/*
	 * Endpoints: 1 - Listar - getLivros() 2 - Obter um livro pelo id -
	 * getLivroId(Int id) 3 - Cadastrar - addLivro(Livro livro) 4 - Editar -
	 * editLivro(Livro livro) 5 - Excluir livro - deleteLivro(Int id)
	 * 
	 */

	@GetMapping()
	public ResponseEntity<List<Livro>> getLivros() throws Exception {
		try {
			return new ResponseEntity<List<Livro>>(ApiLivroRepository.instance().getAll(), HttpStatus.OK);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response<Livro>> getLivroId(@PathVariable int id) {
		try {
			
			Response<Livro> response = new Response<Livro>();

			List<?> queryResult = ApiLivroRepository.instance().getById(id);
			if (queryResult.isEmpty()) {
				String key = String.valueOf(1);
				String value = "Não há um livro com esse id: " + id + ", na base de dados";
				response.getErros().put(key, value);
				response.setStatusHTTP(HttpStatus.NOT_FOUND.value());

				return ResponseEntity.badRequest().body(response);
			}

			response.setDados((Livro) queryResult.get(0));
			response.setStatusHTTP(HttpStatus.OK.value());

			return ResponseEntity.ok().body(response);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@PostMapping()
	public ResponseEntity<Response<Livro>> addLivro(@Valid @RequestBody Livro livro, BindingResult result) {
		try {
			
			Response<Livro> response = new Response<Livro>();
			
			if (result.hasErrors()) {
				for (ObjectError erro : result.getAllErrors()) {
					String key = String.valueOf(response.getErros().size() + 1);
					String value = erro.getDefaultMessage();
					response.getErros().put(key, value);
				}
				response.setStatusHTTP(HttpStatus.BAD_REQUEST.value());
				return ResponseEntity.badRequest().body(response);
			}

			ApiLivroRepository.instance().add(livro);

			response.setDados(livro);
			response.setStatusHTTP(HttpStatus.OK.value());

			return ResponseEntity.ok(response);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@PutMapping("/atualizar/{id}")
	public ResponseEntity<Response<Livro>> editLivro(@Valid @PathVariable int id, @RequestBody Livro livro) {
		try {
			
			Response<Livro> response = new Response<Livro>();

			List<?> queryResult = ApiLivroRepository.instance().getById(id);
			if (queryResult.isEmpty()) {
				String key = String.valueOf(1);
				String value = "Não há um livro com esse id: " + id + ", na base de dados";
				response.getErros().put(key, value);
				response.setStatusHTTP(HttpStatus.NOT_FOUND.value());

				return ResponseEntity.badRequest().body(response);
			}
			
			ApiLivroRepository.instance().edit(id);
			response.setDados((Livro) queryResult.get(0));
			response.setStatusHTTP(HttpStatus.OK.value());

			return ResponseEntity.ok(response);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response<Integer>> deleteLivro(@PathVariable int id) {
		try {

			Response<Integer> response = new Response<Integer>();

			List<?> queryResult = ApiLivroRepository.instance().getById(id);
			if (queryResult.isEmpty()) {
				String key = String.valueOf(1);
				String value = "Não há um livro com esse id: " + id + ", na base de dados";
				response.getErros().put(key, value);
				response.setStatusHTTP(HttpStatus.NOT_FOUND.value());

				return ResponseEntity.badRequest().body(response);
			}

			ApiLivroRepository.instance().delete(id);
			response.setDados(id);
			response.setStatusHTTP(HttpStatus.OK.value());

			return ResponseEntity.ok().body(response);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
