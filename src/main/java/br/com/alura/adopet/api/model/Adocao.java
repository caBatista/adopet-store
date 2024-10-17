package br.com.alura.adopet.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "adocoes")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Adocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tutor tutor;

    @OneToOne(fetch = FetchType.LAZY)
    private Pet pet;

    private String motivo;

    @Enumerated(EnumType.STRING)
    private StatusAdocao status;

    private String justificativaStatus;
}
