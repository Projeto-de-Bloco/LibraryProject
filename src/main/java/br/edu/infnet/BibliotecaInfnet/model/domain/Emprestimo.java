package br.edu.infnet.BibliotecaInfnet.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_emprestimo")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Boolean ativo;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private LocalDateTime dataVencimento;

    //public Emprestimo(UUID uuid, boolean b, Livro livro, Usuario usuario, LocalDateTime localDateTime) {
    //}


    /*public Emprestimo(UUID id, Boolean ativo, Livro livro, Usuario usuario, LocalDateTime dataVencimento) {
        this.id = id;
        this.ativo = ativo;
        this.livro = livro;
        this.usuario = usuario;
        this.dataVencimento = dataVencimento;
    }*/

    @Override
    public String toString() {
        return "Emprestimo{" +
                "id=" + id +
                ", ativo=" + ativo +
                ", livro=" + livro +
                ", usuario=" + usuario +
                ", dataVencimento=" + dataVencimento +
                '}';
    }

}
