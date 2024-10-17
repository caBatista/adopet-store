package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.pet.PetRequest;
import br.com.alura.adopet.api.dto.pet.PetResponse;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PetService {
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private AbrigoRepository abrigoRepository;
	
	public Page<PetResponse> findAll(Pageable pageable) {
		var pets = this.petRepository.findAll(pageable);
		
		return pets.map(PetResponse::new);
	}
	
	public Page<PetResponse> findAllByAbrigo(Pageable pageable, Long id) {
		var pets = this.petRepository.findAllByAbrigoId(pageable, id);
		
		return pets.map(PetResponse::new);
	}
	
	public PetResponse cadastrar(Long abrigoId, PetRequest petDTO) {
		var abrigo = this.abrigoRepository.findById(abrigoId)
				.orElseThrow(() -> new ValidacaoException("Abrigo nao encontrado."));
		
		var pet = Pet.builder()
				.tipo(petDTO.tipo())
				.nome(petDTO.nome())
				.raca(petDTO.raca())
				.idade(petDTO.idade())
				.cor(petDTO.cor())
				.peso(petDTO.peso())
				.adotado(false)
				.abrigo(abrigo)
				.build();
		
		
        this.petRepository.save(pet);
		
		return new PetResponse(pet);
	}
}
