package br.com.alura.adopet.api.dto.pet;

import br.com.alura.adopet.api.model.Pet;

public record PetResponse(
	long id,
	String nome,
	String raca,
	Integer idade,
	String cor,
	Float peso,
	String foiAdotado,
	long abrigoId,
	long tutorId
) {
	public PetResponse(Pet pet) {
		this(
				pet.getId(),
				pet.getNome(),
				pet.getRaca(),
				pet.getIdade(),
				pet.getCor(),
				pet.getPeso(),
				pet.getAdotado() ? "Sim" : "Não",
				pet.getAbrigo().getId(),
				pet.getAdocao() != null ? pet.getAdocao().getTutor().getId() : null
		);
	}
}
