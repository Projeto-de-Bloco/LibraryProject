package br.edu.infnet.BibliotecaInfnet.model.service;

import br.edu.infnet.BibliotecaInfnet.model.domain.Emprestimo;
import br.edu.infnet.BibliotecaInfnet.model.domain.Livro;
import br.edu.infnet.BibliotecaInfnet.model.domain.Usuario;
import br.edu.infnet.BibliotecaInfnet.model.dto.EmprestimoDto;
import br.edu.infnet.BibliotecaInfnet.model.repository.EmprestimoRepository;
import br.edu.infnet.BibliotecaInfnet.notification.Notify;
import br.edu.infnet.BibliotecaInfnet.notification.NotifyAzureServiceBus;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private  NotificacaoService notificacaoService;

    public Emprestimo criarEmprestimo(EmprestimoDto emprestimoDto) {
        Emprestimo emprestimo = new Emprestimo(UUID.randomUUID(), true, emprestimoDto.getLivro(), emprestimoDto.getUsuario(), LocalDateTime.now().plusDays(7));
        Emprestimo emprestimoSalvo = emprestimoRepository.save(emprestimo);

        Usuario usuario = emprestimoDto.getUsuario();
        Livro livro = emprestimoDto.getLivro();
        Usuario donoLivro = livro.getUsuario();

        String usuarioId = usuario.getId().toString();
        String livroId = livro.getId().toString();
        String donoLivroId = donoLivro.getId().toString();


        String mensagemDono = "Seu livro (ID: " + livroId + ") foi emprestado pelo usuário " + usuarioId;
        notificacaoService.enviarNotificacao(donoLivroId, "Empréstimo de Livro", mensagemDono);

        String mensagemUsuario = "Você emprestou o livro (ID: " + livroId + ")";
        notificacaoService.enviarNotificacao(usuarioId, "Empréstimo de Livro", mensagemUsuario);

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

        Livro livro = emprestimo.getLivro();
        Usuario usuario = emprestimo.getUsuario();
        Usuario donoLivro = livro.getUsuario();

        String usuarioId = usuario.getId().toString();
        String livroId = livro.getId().toString();
        String donoLivroId = donoLivro.getId().toString();

        String mensagemDono = "Seu livro (ID: " + livroId + ") foi devolvido pelo usuário " + usuarioId;
        notificacaoService.enviarNotificacao(donoLivroId, "Devolução de Livro", mensagemDono);

        String mensagemUsuario = "Você devolveu o livro (ID: " + livroId + ")";
        notificacaoService.enviarNotificacao(usuarioId, "Devolução de Livro", mensagemUsuario);

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
