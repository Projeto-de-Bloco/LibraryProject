package br.edu.infnet.BibliotecaInfnet.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_notificacao")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class Notificacao {

        private UUID userId;
        private UUID donoId;
        private String mensagem;
        private String nomeLivro;
        private LocalDateTime dataNotificacao;
        private LocalDateTime dataLimite;
}


