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

    private boolean devolvido;

    private boolean aprovado;
    private String status;

}
