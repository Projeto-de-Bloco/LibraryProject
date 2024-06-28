package br.edu.infnet.BibliotecaInfnet.model.repository;

import br.edu.infnet.BibliotecaInfnet.model.domain.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;

public interface NotificacaoRepository extends JpaRepository<Notificacao, UUID> {
    List<Notificacao> findByUsuarioIdAndLidaFalse(UUID usuarioId);
}
