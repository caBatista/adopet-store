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
class ValidacaoTutorAtingiuMaximoTest {
	@Mock
	AdocaoRepository adocaoRepository;
	
	@InjectMocks
	ValidacaoTutorAtingiuMaximo validacaoTutorAtingiuMaximo;
	
	@Mock
	AdocaoRequest adocaoDTO;
	
	@Test
	void deveriaValidarAdocaoComTutorAbaixoDoLimite() {
		BDDMockito.given(adocaoRepository.countByTutorIdAndStatus(adocaoDTO.idTutor(), StatusAdocao.APROVADO))
				.willReturn(4L);
		
		Assertions.assertDoesNotThrow(() -> validacaoTutorAtingiuMaximo.validar(adocaoDTO));
	}
	
	@Test
	void deveriaRejeitarAdocaoComTutorAcimaDoLimite() {
		BDDMockito.given(adocaoRepository.countByTutorIdAndStatus(adocaoDTO.idTutor(), StatusAdocao.APROVADO))
				.willReturn(6L);
		
		Assertions.assertThrows(ValidacaoException.class, () -> validacaoTutorAtingiuMaximo.validar(adocaoDTO));
	}
}