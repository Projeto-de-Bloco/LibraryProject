package br.edu.infnet.BibliotecaInfnet.controller;

import br.edu.infnet.BibliotecaInfnet.model.domain.Carrinho;
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
@RequestMapping("/api/v1")
@Tag(name = "Biblioteca INFNET - CarrinhoController")
public class CarrinhoController {
    @Autowired
    private CarrinhoService carrinhoService;

    @PostMapping(value = "/carrinho/{id}")
    public ResponseEntity<Carrinho> criarCarrinho(@PathVariable("id") UUID usuario_id){
        try{
            Carrinho carrinho = carrinhoService.criarCarrinho(usuario_id);
            return new ResponseEntity<Carrinho>(carrinho, HttpStatus.OK);
        }catch (NoSuchElementException ns){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/carrinho")
    public ResponseEntity<List<Carrinho>> listarCarrinhos(){
        List<Carrinho> carrinhos = carrinhoService.listarCarrinhos();
        if (carrinhos == null || carrinhos.isEmpty()) {
            return new ResponseEntity<List<Carrinho>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Carrinho>>(carrinhos, HttpStatus.OK);
    }

    @DeleteMapping(value = "carrinho/{id}")
    public ResponseEntity deletarCarrinho(@PathVariable("id") UUID id){
        try {
            carrinhoService.deletarCarrinho(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "carrinho/{id}")
    public ResponseEntity<Carrinho> listarCarrinhosPorId(@PathVariable("id") UUID id){
        try{
            Carrinho carrinho = carrinhoService.listarCarrinhosPorId(id);
            return new ResponseEntity<Carrinho>(carrinho, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
