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
class ValidacaoTutorEsperandoTest {
	@Mock
	AdocaoRepository adocaoRepository;
	
	@InjectMocks
	ValidacaoTutorEsperando validacaoTutorEsperando;
	
	@Mock
	AdocaoRequest adocaoDTO;
	
	@Test
	void deveriaValidarAdocaoComTutorDisponivel() {
		BDDMockito.given(adocaoRepository.existsByTutorIdAndStatus(adocaoDTO.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO))
				.willReturn(false);
		
		Assertions.assertDoesNotThrow(() -> validacaoTutorEsperando.validar(adocaoDTO));
	}
	
	@Test
	void deveriaRejeitarAdocaoComTutorEsperando() {
		BDDMockito.given(adocaoRepository.existsByTutorIdAndStatus(adocaoDTO.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO))
				.willReturn(true);
		
		Assertions.assertThrows(ValidacaoException.class, () -> validacaoTutorEsperando.validar(adocaoDTO));
	}
}