package br.edu.infnet.BibliotecaInfnet.notification;

import java.util.concurrent.ExecutionException;
import com.azure.messaging.servicebus.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class NotifyAzureServiceBus {
    private static final String connectionString = "connectionstring_trocarPelaVerdadeira";
    private static final String queueName = "library_project";

    public NotifyAzureServiceBus() { }

    public void sendMessage(Object messageObject) throws JsonProcessingException {
        ServiceBusSenderClient sender = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .sender()
                .queueName(queueName)
                .buildClient();

        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(messageObject);

        ServiceBusMessage message = new ServiceBusMessage(body);
        sender.sendMessage(message);
    }
}