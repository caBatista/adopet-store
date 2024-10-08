package br.com.alura.adopet.api.validation;

import br.com.alura.adopet.api.dto.AdocaoRequest;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPetDisponivel implements ValidacaoSolicitacaoAdocao {
	
	@Autowired
	private PetRepository petRepository;
	
	public void validar(AdocaoRequest adocaoDTO){
		var pet = this.petRepository.findById(adocaoDTO.idPet())
				.orElseThrow();
		
		if(pet.getAdotado()){
			throw new ValidacaoException("Pet indisponível para adoção");
		}
	}
}
