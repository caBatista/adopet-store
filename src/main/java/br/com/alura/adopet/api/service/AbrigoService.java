package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.abrigo.AbrigoRequest;
import br.com.alura.adopet.api.dto.abrigo.AbrigoResponse;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AbrigoService {
	@Autowired
	private AbrigoRepository abrigoRepository;
	
	public Page<AbrigoResponse> findAll(Pageable pageable) {
		var page = this.abrigoRepository.findAll(pageable);
		
		return page.map(AbrigoResponse::new);
	}
	
	public AbrigoResponse cadastrar(AbrigoRequest abrigoDTO) {
		var abrigo = Abrigo.builder()
				.nome(abrigoDTO.nome())
				.telefone(abrigoDTO.telefone())
				.email(abrigoDTO.email())
				.build();
		
		this.abrigoRepository.save(abrigo);
		
		return new AbrigoResponse(abrigo);
	}
}
