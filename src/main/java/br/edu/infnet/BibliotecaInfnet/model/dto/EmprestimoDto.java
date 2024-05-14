package br.edu.infnet.BibliotecaInfnet.model.dto;

import br.edu.infnet.BibliotecaInfnet.model.domain.Livro;
import br.edu.infnet.BibliotecaInfnet.model.domain.Usuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

public class EmprestimoDto {

    private Livro livro;
    private Usuario usuario;
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }
    public void setLivro(Livro livro) {
        this.livro = livro;
    }

}
