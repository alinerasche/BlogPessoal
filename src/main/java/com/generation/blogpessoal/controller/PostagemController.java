package com.generation.blogpessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {

	@Autowired
	private PostagemRepository postagemRepository;
	
	@GetMapping
	public ResponseEntity<List<com.generation.blogpessoal.model.Postagem>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll());
		
		// select * from tb_postagens;
		
	}

	@GetMapping("/{id}") //estou dizendo que o que vir depois de postagens será uma variável
	public ResponseEntity <Postagem> getById(@PathVariable Long id){
		return (ResponseEntity<Postagem>) postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
		
	}
	
	@GetMapping("Titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String Titulo){
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(Titulo));
	
	}	
	
	@PostMapping
	public ResponseEntity <Postagem> postPostagem(@Valid @RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
				
	}
	
	@PutMapping
	public ResponseEntity <Postagem> putPostagem(@Valid @RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem));

	}
	
	@DeleteMapping("/{id}")
	public void deletePostagem(@PathVariable Long id){
		
		postagemRepository.deleteById(id);
		
	}
	
	}	
	
