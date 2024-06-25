package br.edu.infnet.BibliotecaInfnet.notification;

import java.util.UUID;

public class Notify {
    public UUID id;
    public String message;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }
}