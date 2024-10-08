package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Adocao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdocaoRepository extends JpaRepository<Adocao, Long> {
	Page<Adocao> findAll(Pageable pageable);
}
