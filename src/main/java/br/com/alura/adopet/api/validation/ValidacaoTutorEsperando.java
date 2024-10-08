package br.com.alura.adopet.api.validation;

import br.com.alura.adopet.api.dto.AdocaoRequest;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoTutorEsperando implements ValidacaoSolicitacaoAdocao {
	
	@Autowired
	private AdocaoRepository adocaoRepository;
	
	public void validar(AdocaoRequest adocaoDTO) {
		var tutorEstaEsperando = this.adocaoRepository.existsByTutorIdAndStatus(adocaoDTO.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO);
		
		if(tutorEstaEsperando){
			throw new ValidacaoException("Tutor já possui outra adoção aguardando avaliação!");
		}
	}
}
