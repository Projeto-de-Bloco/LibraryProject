package br.edu.infnet.BibliotecaInfnet.model.service;

import br.edu.infnet.BibliotecaInfnet.notification.NotifyAzureServiceBus;
import br.edu.infnet.BibliotecaInfnet.notification.NotifyCommand;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.UUID;

public class NotifyCommandService {

    public void enviarComando(String acao, String dados) throws JsonProcessingException {
        NotifyCommand comando = new NotifyCommand();
        comando.setId(UUID.randomUUID());
        comando.setAction(acao);
        comando.setData(dados);

        NotifyAzureServiceBus serviceBus = new NotifyAzureServiceBus();
        serviceBus.sendMessage(comando);
    }
}