package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.tutor.TutorCrRequest;
import br.com.alura.adopet.api.dto.tutor.TutorResponse;
import br.com.alura.adopet.api.dto.tutor.TutorUpRequest;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TutorService {
	@Autowired
	private TutorRepository tutorRepository;
	
	public TutorResponse cadastrar(TutorCrRequest tutorDTO) {
		var telefoneJaCadastrado = this.tutorRepository.existsByTelefone(tutorDTO.telefone());
		var emailJaCadastrado = this.tutorRepository.existsByEmail(tutorDTO.email());
		
		if (telefoneJaCadastrado || emailJaCadastrado) {
			throw new ValidacaoException("Dados já cadastrados para outro tutor!");
		}
		var tutor = Tutor.builder()
						.nome(tutorDTO.nome())
						.telefone(tutorDTO.telefone())
						.email(tutorDTO.email())
						.build();
		
		this.tutorRepository.save(tutor);
		return new TutorResponse(tutor);
	}
	
	public TutorResponse atualizar(TutorUpRequest tutorDTO) {
		var tutor = tutorRepository.findById(tutorDTO.id())
				.orElseThrow();
		
		tutor.atualiza(tutorDTO);
		
		this.tutorRepository.save(tutor);
		
		return new TutorResponse(tutor);
	}
	
	public Page<TutorResponse> findAll(Pageable pageable) {
		var page = this.tutorRepository.findAll(pageable);
		
		return page.map(TutorResponse::new);
	}
}
