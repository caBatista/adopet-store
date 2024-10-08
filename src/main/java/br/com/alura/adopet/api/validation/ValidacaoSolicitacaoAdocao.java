package br.com.alura.adopet.api.validation;

import br.com.alura.adopet.api.dto.AdocaoRequest;

public interface ValidacaoSolicitacaoAdocao {
	void validar(AdocaoRequest adocaoDTO);
}
