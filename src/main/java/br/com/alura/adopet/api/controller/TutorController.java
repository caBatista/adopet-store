package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.tutor.TutorCrRequest;
import br.com.alura.adopet.api.dto.tutor.TutorResponse;
import br.com.alura.adopet.api.dto.tutor.TutorUpRequest;
import br.com.alura.adopet.api.service.TutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutores")
public class TutorController {
    
    @Autowired
    private TutorService tutorService;
    
    @PostMapping
    @Transactional
    public ResponseEntity<TutorResponse> cadastrar(@RequestBody @Valid TutorCrRequest tutorDTO) {
        var response = tutorService.cadastrar(tutorDTO);
        
        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<TutorResponse> atualizar(@RequestBody @Valid TutorUpRequest tutorDTO) {
        var response = tutorService.atualizar(tutorDTO);
        
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<Page<TutorResponse>> listar(@PageableDefault Pageable pageable) {
        var page = tutorService.findAll(pageable);
        
        if(page.getTotalElements() == 0) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok().body(page);
    }
}
