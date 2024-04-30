package br.edu.infnet.BibliotecaInfnet.model.service;

import br.edu.infnet.BibliotecaInfnet.notification.Notify;
import br.edu.infnet.BibliotecaInfnet.notification.NotifyAzureServiceBus;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.UUID;

public class NotificacaoService {



    public void notificar(String subject, String message) throws JsonProcessingException {

        Notify notify = new Notify();
        notify.setId(UUID.randomUUID());
        notify.setSubject(subject);
        notify.setMessage(message);


        NotifyAzureServiceBus serviceBus = new NotifyAzureServiceBus();
        serviceBus.sendMessage(notify);
    }

}
