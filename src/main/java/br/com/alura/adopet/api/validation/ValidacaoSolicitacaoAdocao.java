package br.com.alura.adopet.api.validation;

import br.com.alura.adopet.api.dto.adocao.AdocaoRequest;

public interface ValidacaoSolicitacaoAdocao {
	void validar(AdocaoRequest adocaoDTO);
}
