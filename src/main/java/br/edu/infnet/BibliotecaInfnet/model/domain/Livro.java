package br.edu.infnet.BibliotecaInfnet.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tb_livro")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String titulo;
    private String autor;
    private String genero;
    private String isbn;
    private String sinopse;

    private boolean disponivel;
    @Getter
    @Setter
    private int numeroDeLeituras;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dono_id")
    private Usuario dono;

    @JsonIgnore
    @OneToOne( mappedBy = "livro", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Emprestimo emprestimo;

    public Livro(String titulo, String autor, int numeroDeLeituras) {
        this.titulo = titulo;
        this.autor = autor;
        this.numeroDeLeituras = numeroDeLeituras;
}

