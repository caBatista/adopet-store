package br.com.alura.adopet.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "pets")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoPet tipo;

    private String nome;

    private String raca;

    private Integer idade;

    private String cor;

    private Float peso;

    private Boolean adotado;

    @ManyToOne(fetch=FetchType.LAZY)
    private Abrigo abrigo;

    @OneToOne(mappedBy = "pet", fetch=FetchType.LAZY)
    private Adocao adocao;
}
