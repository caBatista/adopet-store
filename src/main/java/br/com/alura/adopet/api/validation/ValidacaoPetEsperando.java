package br.com.alura.adopet.api.validation;

import br.com.alura.adopet.api.dto.adocao.AdocaoRequest;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPetEsperando implements ValidacaoSolicitacaoAdocao {

	@Autowired
	private AdocaoRepository adocaoRepository;
	
	public void validar(AdocaoRequest adocaoDTO) {
		var petEstaEsperando = adocaoRepository
				.existsByPetIdAndStatus(adocaoDTO.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO);
		
		if(petEstaEsperando){
			throw new ValidacaoException("Pet já possui outra adoção aguardando avaliação!");
		}
	}
}
