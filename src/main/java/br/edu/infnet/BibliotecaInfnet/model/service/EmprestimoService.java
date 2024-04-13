package br.edu.infnet.BibliotecaInfnet.model.service;

import br.edu.infnet.BibliotecaInfnet.model.domain.Emprestimo;
import br.edu.infnet.BibliotecaInfnet.model.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    public Emprestimo criarEmprestimo(Emprestimo emprestimo) {
        return emprestimoRepository.save(emprestimo);
    }

    public Emprestimo listarEmprestimosPorId(UUID id) {
        return emprestimoRepository.findById(id).orElse(null);
    }

    public List<Emprestimo> listarEmprestimosPorUsuario(UUID id_usuario) {
        return emprestimoRepository.listarUsuarioPorId(id_usuario);
    }

    public List<Emprestimo> listarEmprestimosPorLivro(UUID id_livro) {
        return emprestimoRepository.listarLivroPorId(id_livro);
    }

    public List<Emprestimo> listarEmprestimos() {
        return emprestimoRepository.findAll();
    }

    public Emprestimo atualizarEmprestimo(Emprestimo emprestimo) {
        if (emprestimo.getId() != null && emprestimoRepository.existsById(emprestimo.getId())) {
            return emprestimoRepository.save(emprestimo);
        } else {
            return null;
        }
    }

    public void deletarEmprestimo(UUID id) {
        emprestimoRepository.deleteById(id);
    }
}
