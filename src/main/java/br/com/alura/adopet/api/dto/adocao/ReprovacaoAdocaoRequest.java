package br.com.alura.adopet.api.dto.adocao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReprovacaoAdocaoRequest(
	@NotNull
	long idAdocao,
	
	@NotBlank
	String justificativa
) {}
