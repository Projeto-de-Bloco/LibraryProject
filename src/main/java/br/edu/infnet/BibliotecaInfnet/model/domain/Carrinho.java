package br.edu.infnet.BibliotecaInfnet.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_carrinho")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Livro> livros = new ArrayList<>();
}
