package com.example.pessoa.dtos;

import java.util.Date;
import java.util.List;

import com.example.pessoa.models.Endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PessoaDto(@NotBlank String nome, @NotNull Date dataNascimento, @NotNull List<Endereco> enderecos) {

}
