package br.edu.infnet.BibliotecaInfnet.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


public class Notificacao {

        private String assunto;
        private String mensagem;
        private Usuario destinatario;
        private LocalDateTime dataMensagem;

        public Notificacao(String assunto, String mensagem, Usuario destinatario) {
            this.assunto = assunto;
            this.mensagem = mensagem;
            this.destinatario = destinatario;
            this.dataMensagem = LocalDateTime.now();
        }

        public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public LocalDateTime getDataMensagem() {
        return dataMensagem;
    }

    public void setDataMensagem(LocalDateTime dataMensagem) {
        this.dataMensagem = dataMensagem;
    }

    @Override
    public String toString() {
        return "Notificacao{" +
                "assunto='" + assunto + '\'' +
                ", mensagem='" + mensagem + '\'' +
                ", destinatario=" + destinatario +
                ", dataMensagem=" + dataMensagem +
                '}';
    }
}
