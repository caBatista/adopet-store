package br.com.alura.adopet.api.model;

import br.com.alura.adopet.api.dto.TutorUpRequest;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tutores")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    
    private String telefone;
    
    private String email;

    @OneToMany(mappedBy = "tutor")
    private List<Adocao> adocoes;

    public void atualiza(TutorUpRequest tutorDTO) {
        if (tutorDTO.nome() != null) {
            this.nome = tutorDTO.nome();
        }
        
        if (tutorDTO.telefone() != null) {
            this.telefone = tutorDTO.telefone();
        }
        if (tutorDTO.email() != null) {
            this.email = tutorDTO.email();
        }
    }
}
