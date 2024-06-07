package br.edu.infnet.BibliotecaInfnet.model.domain;

import jakarta.persistence.*;
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

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;
        private UUID userId;
        private UUID donoId;
        private String mensagem;
        private String nomeLivro;
        private LocalDateTime dataNotificacao;
        private LocalDateTime dataLimite;

        public Notificacao(UUID locatarioLivro, UUID donoLivro, String mensagem, String tituloLivro, LocalDateTime now, LocalDateTime dataVencimento) {
                this.userId = locatarioLivro;
                this.donoId = donoLivro;
                this.nomeLivro = tituloLivro;
                this.mensagem = mensagem;
                this.dataNotificacao = now;
                this.dataLimite = dataVencimento;
        }
}


