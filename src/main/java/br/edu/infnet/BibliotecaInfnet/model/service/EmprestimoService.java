package br.edu.infnet.BibliotecaInfnet.model.service;

import br.edu.infnet.BibliotecaInfnet.model.domain.Emprestimo;
import br.edu.infnet.BibliotecaInfnet.model.repository.EmprestimoRepository;
import br.edu.infnet.BibliotecaInfnet.notification.Notify;
import br.edu.infnet.BibliotecaInfnet.notification.NotifyAzureServiceBus;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private  NotificacaoService notificacaoService;

    public Emprestimo criarEmprestimo(Emprestimo emprestimo) {
        Emprestimo emprestimoSalvo = emprestimoRepository.save(emprestimo);

        notificacaoService.publicarNotificacao("Emprestimo realizado", "Seu emprestimo foi realizado com sucesso ", emprestimo.getUsuario());
        notificacaoService.publicarNotificacao("Livro emprestado", "Seu livro foi empresatado com sucesso ", emprestimo.getLivro().getUsuario());

        notificacaoService.processarFila();

        return emprestimoSalvo;

    }

    public Emprestimo listarEmprestimosPorId(UUID id) {
        return emprestimoRepository.findById(id).orElse(null);
    }

    public List<Emprestimo> listarEmprestimosPorUsuario(UUID id_usuario) {
        return emprestimoRepository.findByUsuarioId(id_usuario);
    }

    public List<Emprestimo> listarEmprestimosPorLivro(UUID id_livro) {
        return emprestimoRepository.findByLivroId(id_livro);
    }

    public List<Emprestimo> listarEmprestimos() {
        return emprestimoRepository.findAll();
    }

    public Emprestimo devolverEmprestimo(UUID id) {
        Emprestimo emprestimo = this.listarEmprestimosPorId(id);
        emprestimo.setAtivo(false);
        return this.atualizarEmprestimo(emprestimo);
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
