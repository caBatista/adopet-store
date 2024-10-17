package br.com.alura.adopet.api.dto.abrigo;

import br.com.alura.adopet.api.dto.pet.PetResponse;
import br.com.alura.adopet.api.model.Abrigo;

import java.util.List;

public record AbrigoResponse(
	Long id,
	String nome,
	String telefone,
	String email,
	List<PetResponse> pets
) {
	public AbrigoResponse(Abrigo abrigo) {
		this(
				abrigo.getId(),
				abrigo.getNome(),
				abrigo.getTelefone(),
				abrigo.getEmail(),
				abrigo.getPets() == null ? null : abrigo.getPets().stream().map(PetResponse::new).toList()
		);
	}
}
