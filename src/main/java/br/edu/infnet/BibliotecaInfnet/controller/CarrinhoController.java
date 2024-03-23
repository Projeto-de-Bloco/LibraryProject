package br.edu.infnet.BibliotecaInfnet.controller;

import br.edu.infnet.BibliotecaInfnet.model.domain.Carrinho;
import br.edu.infnet.BibliotecaInfnet.model.domain.Usuario;
import br.edu.infnet.BibliotecaInfnet.model.service.CarrinhoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping(value = "/carrinhos", produces = {"application/json"})
@Tag(name = "Biblioteca INFNET - CarrinhoController")
public class CarrinhoController {
    @Autowired
    CarrinhoService service;

    @PostMapping(value = "/{id}")
    public ResponseEntity<Carrinho> criarCarrinho (@PathVariable("id") UUID usuario_id){
        try{
            Carrinho carrinho = service.criarCarrinho(usuario_id);
            return new ResponseEntity<Carrinho>(carrinho, HttpStatus.OK);
        }catch (NoSuchElementException ns){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Carrinho>> getAll(){

        List<Carrinho> carrinhos = service.getCarrinhos();

        if (carrinhos == null || carrinhos.isEmpty()) {
            return new ResponseEntity<List<Carrinho>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Carrinho>>(carrinhos, HttpStatus.OK);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Carrinho> getById(@PathVariable("id") UUID id){
        try{
            Carrinho carrinho = service.getById(id);
            return new ResponseEntity<Carrinho>(carrinho, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deletarCarrinho(@PathVariable("id") UUID id){
        try {
            service.deletarCarrinho(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


}
