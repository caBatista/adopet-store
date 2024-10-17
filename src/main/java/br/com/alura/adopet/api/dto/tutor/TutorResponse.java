package br.com.alura.adopet.api.dto.tutor;

import br.com.alura.adopet.api.dto.adocao.AdocaoResponse;
import br.com.alura.adopet.api.model.Tutor;

import java.util.List;

public record TutorResponse(
	Long id,
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
