package br.edu.infnet.BibliotecaInfnet.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
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



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @JsonIgnore
    @OneToOne( mappedBy = "livro", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Emprestimo emprestimo;

}
