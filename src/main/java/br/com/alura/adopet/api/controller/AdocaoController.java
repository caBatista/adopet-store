package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AdocaoResponse;
import br.com.alura.adopet.api.dto.AprovacaoAdocaoRequest;
import br.com.alura.adopet.api.dto.AdocaoRequest;
import br.com.alura.adopet.api.dto.ReprovacaoAdocaoRequest;

import br.com.alura.adopet.api.service.AdocaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adocoes")
public class AdocaoController {
    
    @Autowired
    private AdocaoService adocaoService;
    
    @GetMapping
    public ResponseEntity<Page<AdocaoResponse>> findAll(@PageableDefault() Pageable pageable){
        var adocoes = this.adocaoService.findAll(pageable);
        
        return ResponseEntity.ok().body(adocoes);
    }
    
    @PostMapping
    @Transactional
    public ResponseEntity<AdocaoResponse> solicitar(@RequestBody @Valid AdocaoRequest adocaoDTO) {
        var response = this.adocaoService.solicitar(adocaoDTO);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/aprovar")
    @Transactional
    public ResponseEntity<AdocaoResponse> aprovar(@RequestBody @Valid AprovacaoAdocaoRequest adocaoDTO) {
        var response = this.adocaoService.aprovar(adocaoDTO);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/reprovar")
    @Transactional
    public ResponseEntity<AdocaoResponse> reprovar(@RequestBody @Valid ReprovacaoAdocaoRequest adocaoDTO) {
        var response = this.adocaoService.reprovar(adocaoDTO);

        return ResponseEntity.ok().body(response);
    }

}
