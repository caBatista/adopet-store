package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.Tutor;

import java.util.List;

public record TutorResponse(
	long id,
	String nome,
	String telefone,
	String email,
	List<AdocaoResponse> adocoes
) {
	public TutorResponse(Tutor tutor) {
		this(tutor.getId()
				, tutor.getNome()
				, tutor.getTelefone()
				, tutor.getEmail()
				, tutor.getAdocoes().stream().map(AdocaoResponse::new).toList());
	}
}
