package com.example.pessoa.dtos;

import com.example.pessoa.models.Pessoa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoDto(@NotNull Pessoa pessoa, @NotBlank String logradouro, @NotBlank String cep, @NotBlank String numero,
		@NotBlank String cidade) {

}
