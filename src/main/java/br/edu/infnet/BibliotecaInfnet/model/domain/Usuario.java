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
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;
    public String nome;
    public String email;
    public String senha;
    public List<Livro> favoritos;
    public List<Livro> meusLivros;
    public List<Livro> livrosEmMaos;
}
