package br.com.alura.adopet.api.dto.adocao;

import jakarta.validation.constraints.NotNull;

public record AprovacaoAdocaoRequest(
	@NotNull
	Long idAdocao
) {}
