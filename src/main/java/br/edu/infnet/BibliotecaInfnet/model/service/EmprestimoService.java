package br.edu.infnet.BibliotecaInfnet.model.service;

import br.edu.infnet.BibliotecaInfnet.model.domain.Emprestimo;
import br.edu.infnet.BibliotecaInfnet.model.domain.Livro;
import br.edu.infnet.BibliotecaInfnet.model.domain.Notificacao;
import br.edu.infnet.BibliotecaInfnet.model.dto.EmprestimoDto;
import br.edu.infnet.BibliotecaInfnet.model.repository.EmprestimoRepository;
import br.edu.infnet.BibliotecaInfnet.model.repository.LivroRepository;
import br.edu.infnet.BibliotecaInfnet.model.repository.NotificacaoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private NotificacaoRepository notificacaoRepository;
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private LivroService livroService;
    @Autowired
    private NotifyService notifyService;


    public Emprestimo criarEmprestimo(EmprestimoDto emprestimoDto) throws JsonProcessingException {

        Emprestimo emprestimo = new Emprestimo(UUID.randomUUID(), true, emprestimoDto.getLivro(), emprestimoDto.getUsuario(),
                LocalDateTime.now().plusDays(7));
        Emprestimo emprestimoSalvo = emprestimoRepository.save(emprestimo);
        Livro livro = livroService.obterLivro(emprestimoDto.getLivro().getId());
        UUID donoLivro = livro.getDono().getId();
        UUID locatarioLivro = emprestimoDto.getLivro().getId();
        String tituloLivro = livro.getTitulo();


        Notificacao notificacao = new Notificacao(locatarioLivro, donoLivro,
                "Empréstimo do livro " + tituloLivro + " realizado!" ,
                tituloLivro, LocalDateTime.now(), emprestimo.getDataVencimento());

        Notificacao notificacaoFinalEmprestimo = new Notificacao(locatarioLivro, donoLivro,
                "O limite do empréstimo do livro " +tituloLivro+ " acaba amanhã! Fique ligado!", tituloLivro, LocalDateTime.now(), emprestimo.getDataVencimento().minusDays(1) );

        notificacaoRepository.save(notificacaoFinalEmprestimo);
        notificacaoRepository.save(notificacao);
        notifyService.enviarComandoNotificacao("Criar emprestimo");



        return emprestimoSalvo;

    }

    public Emprestimo devolverEmprestimo(UUID id) {
        Emprestimo emprestimo = this.buscarEmprestimoPorId(id);
        emprestimo.setAtivo(false);
        emprestimo.setDataVencimento(LocalDateTime.now());

        UUID donoLivro = emprestimo.getLivro().getDono().getId();
        UUID locatarioLivro = emprestimo.getUsuario().getId();
        String tituloLivro = emprestimo.getLivro().getTitulo();

        Notificacao notificacao = new Notificacao(locatarioLivro, donoLivro,
                "Devolução do livro " + tituloLivro + " realizada!" ,
                tituloLivro, LocalDateTime.now(), null );


        notificacaoRepository.save(notificacao);


        return this.atualizarEmprestimo(emprestimo);
    }

    public Emprestimo buscarEmprestimoPorId(UUID id) {

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


    public Emprestimo atualizarEmprestimo(Emprestimo emprestimo) {
        if (emprestimo.getId() != null && emprestimoRepository.existsById(emprestimo.getId())) {
            return emprestimoRepository.save(emprestimo);
        } else {
            return null;
        }
    }

    public Emprestimo renovarEmprestimo(UUID id) {

        Emprestimo emprestimo = buscarEmprestimoPorId(id);
        if (emprestimo != null && emprestimo.isAtivo()) {

        emprestimo.setDataVencimento(LocalDateTime.now().plusDays(7));
        emprestimo = atualizarEmprestimo(emprestimo);

        UUID donoLivro = emprestimo.getLivro().getDono().getId();
        UUID locatarioLivro = emprestimo.getUsuario().getId();
        String tituloLivro = emprestimo.getLivro().getTitulo();

        Notificacao notificacao = new Notificacao(locatarioLivro, donoLivro,
            "Empréstimo do livro " + tituloLivro + " renovado!",
            tituloLivro, LocalDateTime.now(), emprestimo.getDataVencimento());

        notificacaoRepository.save(notificacao);

        return emprestimo;
        } else {
        return null;
        }
    }

    public void deletarEmprestimo(UUID id) {

        emprestimoRepository.deleteById(id);
    }
}
