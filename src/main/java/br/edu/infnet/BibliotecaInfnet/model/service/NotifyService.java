package br.edu.infnet.BibliotecaInfnet.model.service;

import br.edu.infnet.BibliotecaInfnet.notification.Notify;
import br.edu.infnet.BibliotecaInfnet.notification.NotifyAzureServiceBus;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class NotifyService {
    public void notificar( String message) throws JsonProcessingException {
        Notify notify = new Notify();
        notify.setId(UUID.randomUUID());
        notify.setMessage(message);

        NotifyAzureServiceBus serviceBus = new NotifyAzureServiceBus();
        serviceBus.sendMessage(notify);
    }

    public void enviarComandoNotificacao(String acao) throws JsonProcessingException {
        NotifyCommandService commandService = new NotifyCommandService();
        commandService.enviarComando(acao);
    }

    public void enviarConsultaNotificacao(String criterio) throws JsonProcessingException, com.azure.json.implementation.jackson.core.JsonProcessingException {
        NotifyQueryService queryService = new NotifyQueryService();
        queryService.enviarConsulta(criterio);
    }
}