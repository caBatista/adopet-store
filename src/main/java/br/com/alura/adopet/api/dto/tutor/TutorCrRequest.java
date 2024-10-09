package br.com.alura.adopet.api.dto.tutor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record TutorCrRequest(
	@NotBlank
	String nome,
	
	@NotBlank
	@Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}")
	String telefone,
	
	@NotBlank
	@Email
	String email
) {}
