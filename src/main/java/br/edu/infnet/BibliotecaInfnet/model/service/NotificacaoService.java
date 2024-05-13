package br.edu.infnet.BibliotecaInfnet.model.service;


import br.edu.infnet.BibliotecaInfnet.model.domain.Notificacao;
import br.edu.infnet.BibliotecaInfnet.model.domain.Usuario;
import org.springframework.stereotype.Service;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

@Service
public class NotificacaoService {

    private final Queue<String> filaNotificacoes = new ArrayBlockingQueue<>(100);

    public void publicarNotificacao(String assunto, String mensagem, Usuario usuario) {
        Notificacao notificacao =  new Notificacao(assunto, mensagem, usuario);
        filaNotificacoes.offer(notificacao.toString());
    }


    public void processarFila() {
        while (!filaNotificacoes.isEmpty()) {
            String mensagem = filaNotificacoes.poll();
            if (mensagem != null) {
                notificar(mensagem);
            }
        }
    }


     void notificar(String mensagem) {

        System.out.println("Nortificação: " + mensagem);

    }
}
