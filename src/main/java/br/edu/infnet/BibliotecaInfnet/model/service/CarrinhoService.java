package br.edu.infnet.BibliotecaInfnet.model.service;

import br.edu.infnet.BibliotecaInfnet.model.domain.Carrinho;
import br.edu.infnet.BibliotecaInfnet.model.domain.Livro;
import br.edu.infnet.BibliotecaInfnet.model.domain.Usuario;
import br.edu.infnet.BibliotecaInfnet.model.repository.CarrinhoRepository;
import br.edu.infnet.BibliotecaInfnet.model.repository.LivroRepository;
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
    private LivroRepository livroRepository;

    @Autowired
    private NotifyService notifyService;

    public Carrinho criarCarrinho(UUID user_id){
        Carrinho carrinho = new Carrinho();
        Usuario usuario = new Usuario();
        usuario.setId(user_id);
        carrinho.setUsuario(usuario);
        return carrinhoRepository.save(carrinho);
    }

    public List<Carrinho> listarCarrinhos(){
        return carrinhoRepository.findAll();
    }

    public void deletarCarrinho(UUID id)  {
        carrinhoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Carrinho listarCarrinhosPorId(UUID id) {
        return carrinhoRepository.findById(id).orElse(null);
    }

    public void adicionarLivroAoCarrinho(UUID idCarrinho, UUID idLivro){

        Carrinho carrinho = carrinhoRepository.findById(idCarrinho).orElse(null);
        Livro livro = livroRepository.findById(idLivro).orElse(null);

        if (carrinho != null && livro != null) {
            carrinho.getLivros().add(livro);
            carrinhoRepository.save(carrinho);
        }
    }

    public void removerLivroDoCarrinho(UUID idCarrinho, UUID idLivro){

        Carrinho carrinho = carrinhoRepository.findById(idCarrinho).orElse(null);
        Livro livro = livroRepository.findById(idLivro).orElse(null);

        if (carrinho != null && livro != null) {
            if (carrinho.getLivros().contains(livro)) {
                carrinho.getLivros().remove(livro);
                carrinhoRepository.save(carrinho);
            }
        }
    }

}
