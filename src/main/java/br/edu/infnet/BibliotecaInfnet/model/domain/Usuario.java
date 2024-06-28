package br.edu.infnet.BibliotecaInfnet.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_usuario")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String email;
    private String senha;

    @JsonIgnore
    @OneToMany(mappedBy = "dono", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<Livro> meusLivros;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Emprestimo> emprestimos;


}
