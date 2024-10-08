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
public class ValidacaoTutorAtingiuMaximo implements ValidacaoSolicitacaoAdocao {
	@Autowired
	private TutorRepository tutorRepository;
	
	@Autowired
	private AdocaoRepository adocaoRepository;
	
	public void validar (AdocaoRequest adocaoDTO) {
		var tutor = this.tutorRepository.findById(adocaoDTO.idTutor())
				.orElseThrow();
		
		var adocoes = this.adocaoRepository.findAll();
		
		for (Adocao a : adocoes) {
			int contador = 0;
			if (a.getTutor() == tutor && a.getStatus() == StatusAdocao.APROVADO) {
				contador = contador + 1;
			}
			if (contador == 5) {
				throw new ValidacaoException("Tutor chegou ao limite máximo de 5 adoções!");
			}
		}
	}
}
