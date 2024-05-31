package br.edu.infnet.BibliotecaInfnet.model.service;


import br.edu.infnet.BibliotecaInfnet.model.domain.Notificacao;
import br.edu.infnet.BibliotecaInfnet.model.domain.Usuario;
import org.springframework.stereotype.Service;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

@Service
public class NotificacaoService {

    public void enviarNotificacao(String destinatario, String assunto, String mensagem) {
        System.out.println("Enviando notificação para: " + destinatario);
        System.out.println("Assunto: " + assunto);
        System.out.println("Mensagem: " + mensagem);
    }
}