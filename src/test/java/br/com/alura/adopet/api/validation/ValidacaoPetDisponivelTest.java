package br.com.alura.adopet.api.validation;

import br.com.alura.adopet.api.dto.adocao.AdocaoRequest;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ValidacaoPetDisponivelTest {
	@Mock
	PetRepository petRepository;
	
	@InjectMocks
	ValidacaoPetDisponivel validacaoPetDisponivel;
	
	@Mock
	AdocaoRequest adocaoDTO;
	
	@Mock
	Pet pet;
	
	@Test
	void deveriaValidarAdocaoComPetDisponivel() {
		
		BDDMockito.given(petRepository.findById(adocaoDTO.idPet()))
				.willReturn(Optional.of(pet));
		
		BDDMockito.given(pet.getAdotado())
				.willReturn(false);
		
		Assertions.assertDoesNotThrow(() -> validacaoPetDisponivel.validar(adocaoDTO));
	}
	
	@Test
	void deveriaRejeitarAdocaoComPetIndisponivel() {
		
		BDDMockito.given(petRepository.findById(adocaoDTO.idPet()))
				.willReturn(Optional.of(pet));
		
		BDDMockito.given(pet.getAdotado())
				.willReturn(true);
		
		Assertions.assertThrows(ValidacaoException.class, () -> validacaoPetDisponivel.validar(adocaoDTO));
	}
}