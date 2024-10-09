package br.com.alura.adopet.api.dto.adocao;

import br.com.alura.adopet.api.model.Adocao;

public record AdocaoResponse(
	long id,
	String nomePet,
	String nomeTutor,
	String status
) {
	public AdocaoResponse(Adocao adocao) {
		this(adocao.getId()
			,adocao.getPet().getNome()
			,adocao.getTutor().getNome()
			,String.valueOf(adocao.getStatus()));
	}
}
