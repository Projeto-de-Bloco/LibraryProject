package br.edu.infnet.BibliotecaInfnet.model.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_carrinho")
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    public Usuario usuario;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<Livro> livros = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
}
