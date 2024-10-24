package br.com.alura.adopet.api.dto.tutor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record TutorUpRequest(
	@NotNull
	Long id,
	
	String nome,
	
	@Email
	String email,
	
	@Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}")
	String telefone
) {}
