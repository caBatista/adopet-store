package br.com.alura.adopet.api.validation;

import br.com.alura.adopet.api.dto.adocao.AdocaoRequest;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidacaoPetEsperandoTest {
	@Mock
	AdocaoRepository adocaoRepository;
	
	@InjectMocks
	ValidacaoPetEsperando validacaoPetEsperando;
	
	@Mock
	AdocaoRequest adocaoDTO;
	
	@Test
	void deveriaValidarAdocaoComPetDisponivel() {
		BDDMockito.given(adocaoRepository.existsByPetIdAndStatus(adocaoDTO.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO))
				.willReturn(false);
		
		Assertions.assertDoesNotThrow(() -> validacaoPetEsperando.validar(adocaoDTO));
	}
	
	@Test
	void deveriaRejeitarAdocaoComPetEsperando() {
		BDDMockito.given(adocaoRepository.existsByPetIdAndStatus(adocaoDTO.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO))
				.willReturn(true);
		
		Assertions.assertThrows(ValidacaoException.class, () -> validacaoPetEsperando.validar(adocaoDTO));
	}
}