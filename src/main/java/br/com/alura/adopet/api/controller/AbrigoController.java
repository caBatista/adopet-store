package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.abrigo.AbrigoRequest;
import br.com.alura.adopet.api.dto.abrigo.AbrigoResponse;
import br.com.alura.adopet.api.dto.pet.PetRequest;
import br.com.alura.adopet.api.dto.pet.PetResponse;
import br.com.alura.adopet.api.service.AbrigoService;
import br.com.alura.adopet.api.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {
    @Autowired
    private AbrigoService abrigoService;
    
    @Autowired
    private PetService petService;
    
    @GetMapping
    public ResponseEntity<Page<AbrigoResponse>> listar(@PageableDefault Pageable pageable) {
        var page = this.abrigoService.findAll(pageable);
        
        if(page.getTotalElements() == 0) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AbrigoResponse> cadastrar(@RequestBody @Valid AbrigoRequest abrigoDTO) {
        var response = this.abrigoService.cadastrar(abrigoDTO);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/pets")
    public ResponseEntity<Page<PetResponse>> listarPets(@PageableDefault Pageable pageable, @PathVariable Long id) {
        var page = this.petService.findAllByAbrigo(pageable, id);
        
        if(page.getTotalElements() == 0) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(page);
    }

    @PostMapping("/{id}/pets")
    @Transactional
    public ResponseEntity<PetResponse> cadastrarPet(@PathVariable Long id, @RequestBody @Valid PetRequest petDTO) {
        var response = this.petService.cadastrar(id, petDTO);
        
        return ResponseEntity.ok(response);
    }

}
