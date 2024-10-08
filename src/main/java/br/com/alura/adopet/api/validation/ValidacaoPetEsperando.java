package br.com.alura.adopet.api.validation;

import br.com.alura.adopet.api.dto.AdocaoRequest;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPetEsperando implements ValidacaoSolicitacaoAdocao {
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private AdocaoRepository adocaoRepository;
	
	public void validar(AdocaoRequest adocaoDTO) {
		var pet = this.petRepository.findById(adocaoDTO.idPet())
				.orElseThrow();
		
		var adocoes = this.adocaoRepository.findAll();
		
		for (Adocao a : adocoes) {
			if (a.getPet() == pet && a.getStatus() == StatusAdocao.AGUARDANDO_AVALIACAO) {
				throw new ValidacaoException("Pet já possui outra adoção aguardando avaliação!");
			}
		}
	}
}
