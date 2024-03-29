package br.edu.infnet.BibliotecaInfnet.model.service;

import br.edu.infnet.BibliotecaInfnet.model.domain.Carrinho;
import br.edu.infnet.BibliotecaInfnet.model.domain.Usuario;
import br.edu.infnet.BibliotecaInfnet.model.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository repository;

    @Transactional(readOnly = true)
    public Carrinho getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public List<Carrinho> getCarrinhos(){
        return repository.findAll();
    }

    public Carrinho criarCarrinho(UUID user_id){
        Carrinho carrinho = new Carrinho();
        Usuario usuario = new Usuario();
        usuario.setId(user_id);
        carrinho.setUsuario(usuario);
        return repository.save(carrinho);
    }

    public void deletarCarrinho(UUID id){
        repository.deleteById(id);
    }


}
