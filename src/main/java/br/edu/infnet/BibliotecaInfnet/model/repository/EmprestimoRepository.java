package br.edu.infnet.BibliotecaInfnet.model.repository;

import br.edu.infnet.BibliotecaInfnet.model.domain.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, UUID> {

    List<Emprestimo> findByUsuarioId(UUID id);
    List<Emprestimo> findByLivroId(UUID id);
    List<Emprestimo> findByUsuarioIdAndDevolvidoFalse(Long usuarioId);


    public void devolverLivro(Long emprestimoId) {
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId).orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));
        emprestimo.setDevolvido(true);
        emprestimo.getLivro().setDisponivel(true);
        emprestimoRepository.save(emprestimo);
        livroRepository.save(emprestimo.getLivro());
    }
}
