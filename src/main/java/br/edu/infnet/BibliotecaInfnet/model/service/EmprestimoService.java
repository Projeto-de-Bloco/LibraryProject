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

    public Emprestimo getEmprestimoById(UUID id) {
        return emprestimoRepository.findById(id).orElse(null);
    }

    public List<Emprestimo> getEmprestimoByUsuario(UUID id_usuario) {
        return emprestimoRepository.findByUsuarioId(id_usuario);
    }

    public List<Emprestimo> getEmprestimoByLivro(UUID id_livro) {
        return emprestimoRepository.findByLivroId(id_livro);
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimoRepository.findAll();
    }

    public Emprestimo updateEmprestimoById(Emprestimo emprestimo) {
        if (emprestimo.getId() != null && emprestimoRepository.existsById(emprestimo.getId())) {
            return emprestimoRepository.save(emprestimo);
        } else {
            return null;
        }
    }

    public void deleteEmprestimoById(UUID id) {
        emprestimoRepository.deleteById(id);
    }
}
