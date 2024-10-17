package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.adocao.AdocaoRequest;
import br.com.alura.adopet.api.dto.adocao.AdocaoResponse;
import br.com.alura.adopet.api.dto.adocao.AprovacaoAdocaoRequest;
import br.com.alura.adopet.api.dto.adocao.ReprovacaoAdocaoRequest;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validation.ValidacaoSolicitacaoAdocao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AdocaoService {
	
	@Autowired
	private AdocaoRepository adocaoRepository;
	
	@Autowired
	private TutorRepository tutorRepository;
	
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private List<ValidacaoSolicitacaoAdocao> validacoes;
	
	public Page<AdocaoResponse> findAll(Pageable pageable) {
		var adocoes = adocaoRepository.findAll(pageable);
		
		return adocoes.map(AdocaoResponse::new);
	}
	
	public AdocaoResponse solicitar(AdocaoRequest adocaoDTO) {
		var tutor = tutorRepository.findById(adocaoDTO.idTutor())
				.orElseThrow();
		
		var pet = petRepository.findById(adocaoDTO.idPet())
				.orElseThrow();
		
		validacoes.forEach(v -> v.validar(adocaoDTO));
		
		var data = LocalDateTime.now();
		var status = StatusAdocao.AGUARDANDO_AVALIACAO;
		var adocao = Adocao.builder()
				.data(data)
				.tutor(tutor)
				.pet(pet)
				.motivo(adocaoDTO.motivo())
				.status(status)
				.build();
				
		adocaoRepository.save(adocao);
		
		var toEmail = adocao.getPet().getAbrigo().getEmail();
		var subject = "Solicitação de adoção";
		var text = "Olá " +adocao.getPet().getAbrigo().getNome() +"!\n\nUma solicitação de adoção foi registrada hoje para o pet: " +adocao.getPet().getNome() +". \nFavor avaliar para aprovação ou reprovação.";
		
		this.emailService.enviaEmail(toEmail, subject, text);
		
		return new AdocaoResponse(adocao);
	}
	
	public AdocaoResponse aprovar(AprovacaoAdocaoRequest adocaoDTO) {
		var adocao = adocaoRepository.findById(adocaoDTO.idAdocao())
				.orElseThrow();
		
		if(adocao.getStatus() != StatusAdocao.AGUARDANDO_AVALIACAO) {
			throw new ValidacaoException("Adoção está fechada!");
		}
		
		var pet = petRepository.findById(adocao.getPet().getId())
				.orElseThrow();
		
		adocao.setStatus(StatusAdocao.APROVADO);
		adocaoRepository.save(adocao);
		
		pet.setAdotado(true);
		petRepository.save(pet);
		
		var toEmail = adocao.getTutor().getEmail();
		var subject = "Adoção aprovada";
		var text = "Parabéns " +adocao.getTutor().getNome() +"!\n\nSua adoção do pet " +adocao.getPet().getNome() +", solicitada em " +adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +", foi aprovada.\nFavor entrar em contato com o abrigo " +adocao.getPet().getAbrigo().getNome() +" para agendar a busca do seu pet.";
		
		this.emailService.enviaEmail(toEmail, subject, text);
		
		return new AdocaoResponse(adocao);
	}
	
	public AdocaoResponse reprovar(ReprovacaoAdocaoRequest adocaoDTO) {
		var adocao = adocaoRepository.findById(adocaoDTO.idAdocao())
				.orElseThrow();
		
		if(adocao.getStatus() != StatusAdocao.AGUARDANDO_AVALIACAO) {
			throw new ValidacaoException("Adoção está fechada!");
		}
		
		adocao.setStatus(StatusAdocao.REPROVADO);
		adocaoRepository.save(adocao);
		
		var toEmail = adocao.getTutor().getEmail();
		var subject = "Adoção reprovada";
		var text = "Olá " +adocao.getTutor().getNome() +"!\n\nInfelizmente sua adoção do pet " +adocao.getPet().getNome() +", solicitada em " +adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +", foi reprovada pelo abrigo " +adocao.getPet().getAbrigo().getNome() +" com a seguinte justificativa: " +adocao.getJustificativaStatus();
		this.emailService.enviaEmail(toEmail, subject, text);
		
		return new AdocaoResponse(adocao);
	}
}
