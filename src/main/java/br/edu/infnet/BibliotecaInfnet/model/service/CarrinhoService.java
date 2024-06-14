package br.edu.infnet.BibliotecaInfnet.model.service;

import br.edu.infnet.BibliotecaInfnet.model.domain.Carrinho;
import br.edu.infnet.BibliotecaInfnet.model.domain.Usuario;
import br.edu.infnet.BibliotecaInfnet.model.repository.CarrinhoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;
    @Autowired
    private NotifyService notifyService;

    public Carrinho criarCarrinho(UUID user_id) throws JsonProcessingException {
        Carrinho carrinho = new Carrinho();
        Usuario usuario = new Usuario();
        usuario.setId(user_id);
        carrinho.setUsuario(usuario);
        notifyService.enviarComandoNotificacao("Criar carrinho");

        return carrinhoRepository.save(carrinho);
    }

    public List<Carrinho> listarCarrinhos(){
        return carrinhoRepository.findAll();
    }

    public void deletarCarrinho(UUID id) throws JsonProcessingException {
        notifyService.enviarComandoNotificacao("Apagar carrinho");

        carrinhoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Carrinho listarCarrinhosPorId(UUID id) {
        return carrinhoRepository.findById(id).orElse(null);
    }

}
