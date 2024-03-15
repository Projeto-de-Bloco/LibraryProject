package br.edu.infnet.BibliotecaInfnet.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;
    public Boolean emprestado;
    public String titulo;
    public String autor;
    public String genero;
    public String isbn;
    public String sinopse;
    public Usuario dono;
    public List<Usuario> listaEspera;
    public List<Emprestimo> historico;

}
