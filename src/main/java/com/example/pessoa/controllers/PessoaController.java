package com.example.pessoa.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pessoa.dtos.PessoaDto;
import com.example.pessoa.models.Pessoa;
import com.example.pessoa.repositories.PessoaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@PostMapping
	public ResponseEntity<Pessoa> salvarPessoa(@RequestBody @Valid PessoaDto pessoaDto) {
		var pessoa = new Pessoa();
		BeanUtils.copyProperties(pessoaDto, pessoa);
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaRepository.save(pessoa));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> editarPessoa(@PathVariable(value = "id") Long id, 
			@RequestBody @Valid PessoaDto pessoaDto) {
		Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
		
		if(pessoaOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
		}
		
		var pessoa = pessoaOptional.get();
		BeanUtils.copyProperties(pessoaDto, pessoa);
		return ResponseEntity.status(HttpStatus.OK).body(pessoaRepository.save(pessoa));
	}
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> mostrarPessoas() {
		return ResponseEntity.status(HttpStatus.OK).body(pessoaRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> MostrarPessoa(@PathVariable(value = "id") Long id,
			@RequestBody @Valid PessoaDto pessoaDto) {
		Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
		
		if(pessoaOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
		}
		
		var pessoa = pessoaOptional.get();
		BeanUtils.copyProperties(pessoaDto, pessoa);
		return ResponseEntity.status(HttpStatus.OK).body(pessoa);
	}

}
