package br.edu.infnet.BibliotecaInfnet.model.repository;

import br.edu.infnet.BibliotecaInfnet.model.domain.Carrinho;
import br.edu.infnet.BibliotecaInfnet.model.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, UUID> {
}
