package br.edu.infnet.BibliotecaInfnet.controller;

import br.edu.infnet.BibliotecaInfnet.model.domain.Carrinho;
import br.edu.infnet.BibliotecaInfnet.model.service.CarrinhoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Biblioteca INFNET - CarrinhoController")
public class CarrinhoController {
    @Autowired
    private CarrinhoService carrinhoService;

    @PostMapping(value = "/carrinhos")
    public ResponseEntity<?> criarCarrinho(@PathVariable("id") UUID usuario_id){
        try{
            Carrinho carrinho = carrinhoService.criarCarrinho(usuario_id);
            return new ResponseEntity<>(carrinho, HttpStatus.OK);
        } catch (NoSuchElementException ns) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", HttpStatus.BAD_REQUEST.value());
            errorResponse.put("message", "ID de usuário não encontrado");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.put("message", "Erro interno do servidor");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/carrinhos")
    public ResponseEntity<?> listarCarrinhos() {
        try {
            List<Carrinho> carrinhos = carrinhoService.listarCarrinhos();
            if (carrinhos == null || carrinhos.isEmpty()) {
                Map<String, Object> response = new HashMap<>();
                response.put("code", HttpStatus.NO_CONTENT.value());
                response.put("message", "Nenhum carrinho encontrado");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(carrinhos, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Erro interno do servidor");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "carrinhos/{id}")
    public ResponseEntity<?> deletarCarrinho(@PathVariable("id") UUID id){
        try {
            carrinhoService.deletarCarrinho(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", HttpStatus.BAD_REQUEST.value());
            errorResponse.put("message", "ID de carrinho não encontrado");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.put("message", "Erro interno do servidor");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "carrinhos/{id}")
    public ResponseEntity<?> listarCarrinhosPorId(@PathVariable("id") UUID id){
        try{
            Carrinho carrinho = carrinhoService.listarCarrinhosPorId(id);
            return new ResponseEntity<Carrinho>(carrinho, HttpStatus.OK);
        }catch (IllegalArgumentException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", HttpStatus.BAD_REQUEST.value());
            errorResponse.put("message", "ID de usuário inválido");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.put("message", "Erro interno do servidor");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "carrinhos/{idCarrinho}/adicionar/{idLivro}")
    public ResponseEntity<?> adicionarLivroNoCarrinho(@PathVariable("idCarrinho") UUID idCarrinho, @PathVariable("idLivro") UUID idLivro){
        try{
            carrinhoService.adicionarLivroAoCarrinho(idCarrinho, idLivro);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException ns) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", HttpStatus.BAD_REQUEST.value());
            errorResponse.put("message", "ID de livro ou carrinho não encontrado");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.put("message", "Erro interno do servidor");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "carrinhos/{idCarrinho}/remover/{idLivro}")
    public ResponseEntity<?> removerLivroDoCarrinho(@PathVariable("idCarrinho") UUID idCarrinho, @PathVariable("idLivro") UUID idLivro){
        try{
            carrinhoService.removerLivroDoCarrinho(idCarrinho, idLivro);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException ns) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", HttpStatus.BAD_REQUEST.value());
            errorResponse.put("message", "ID de livro ou carrinho não encontrado");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.put("message", "Erro interno do servidor");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
