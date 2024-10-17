package br.com.alura.adopet.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "abrigos")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Abrigo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String telefone;

    private String email;

    @OneToMany(mappedBy = "abrigo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pet> pets;
}
