package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdocaoRepository extends JpaRepository<Adocao, Long> {
	Page<Adocao> findAll(Pageable pageable);
	
	boolean existsByPetIdAndStatus(Long idPet, StatusAdocao status);
	
	Long countByTutorIdAndStatus(Long idTutor, StatusAdocao status);

	boolean existsByTutorIdAndStatus(Long idTutor, StatusAdocao status);
}
