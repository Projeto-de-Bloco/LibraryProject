package br.edu.infnet.BibliotecaInfnet.model.service;

import br.edu.infnet.BibliotecaInfnet.notification.NotifyAzureServiceBus;
import br.edu.infnet.BibliotecaInfnet.notification.NotifyQuery;
import com.azure.json.implementation.jackson.core.JsonProcessingException;

public class NotifyQueryService {

    public void enviarConsulta(String criterio) throws JsonProcessingException, com.fasterxml.jackson.core.JsonProcessingException {
        NotifyQuery query = new NotifyQuery();
        query.setCriteria(criterio);

        NotifyAzureServiceBus serviceBus = new NotifyAzureServiceBus();
        serviceBus.sendMessage(query);
    }
}