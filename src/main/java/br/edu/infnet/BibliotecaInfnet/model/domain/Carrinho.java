package br.edu.infnet.BibliotecaInfnet.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;
    public Usuario usuario;
    public List<Livro> listaLivros;
}
