package br.edu.infnet.BibliotecaInfnet.notification;

import java.util.UUID;

public class NotifyCommand {
    private UUID id;
    private String action;
    private String data;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}