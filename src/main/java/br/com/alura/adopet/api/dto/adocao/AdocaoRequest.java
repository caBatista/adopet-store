package br.com.alura.adopet.api.dto.adocao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdocaoRequest(
	@NotNull
	long idPet,
	
	@NotNull
	long idTutor,
	
	@NotBlank
	String motivo
) {}
