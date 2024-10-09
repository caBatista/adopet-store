package br.com.alura.adopet.api.dto.pet;

import br.com.alura.adopet.api.model.TipoPet;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PetRequest(
	@NotBlank
	String nome,
	
	@NotNull
	TipoPet tipo,
	
	@NotBlank
	String raca,
	
	@NotNull
	Integer idade,
	
	@NotBlank
	String cor,
	
	@NotNull
	Float peso
) {}
