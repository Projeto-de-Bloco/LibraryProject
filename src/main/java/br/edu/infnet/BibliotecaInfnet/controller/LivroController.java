package br.edu.infnet.BibliotecaInfnet.controller;

import br.edu.infnet.BibliotecaInfnet.model.domain.Livro;
import br.edu.infnet.BibliotecaInfnet.model.domain.Usuario;
import br.edu.infnet.BibliotecaInfnet.model.service.LivroService;
import br.edu.infnet.BibliotecaInfnet.model.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Biblioteca INFNET - LivroController")
public class LivroController {
    @Autowired
    private LivroService livroService;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/livros")
    public ResponseEntity<?> criarLivro(@RequestBody Livro livro) {
        try {
            Usuario dono = usuarioService.obterUsuario(livro.getDono().getId());
            livro.setDono(dono);
            Livro livroCriado = livroService.criarLivro(livro);
            return new ResponseEntity(livroCriado, HttpStatus.CREATED);
        } catch (NullPointerException np) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", HttpStatus.BAD_REQUEST.value());
            errorResponse.put("message", "O objeto livro está vazio.");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.put("message", "Erro interno do servidor");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/livros")
    public ResponseEntity<?> listarLivros() {
        try {
            List<Livro> livros = livroService.listarLivros();
            return new ResponseEntity(livros, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Erro interno do servidor");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/livros/{id}")
    public ResponseEntity<?> atualizarLivro(@PathVariable UUID id, @RequestBody Livro livro) {
        try {
            Livro livroModificado = livroService.obterLivro(id);
            livroService.atualizarLivroPorId(livro);
            return new ResponseEntity(livroModificado, HttpStatus.OK);
        } catch (NoSuchElementException ns) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", HttpStatus.BAD_REQUEST.value());
            errorResponse.put("message", "ID do livro não encontrado");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.put("message", "Erro interno do servidor");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/livros/{id}")
    public ResponseEntity<?> deletarLivro(@PathVariable UUID id) {
        try {
            livroService.deletarLivro(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NoSuchElementException ns) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", HttpStatus.BAD_REQUEST.value());
            errorResponse.put("message", "ID do livro não encontrado");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.put("message", "Erro interno do servidor");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/livros/autores/{autor}")
    public ResponseEntity<?> listarLivrosPorAutor(@PathVariable String autor) {
        try {
            List<Livro> livros = livroService.listarLivrosPorAutor(autor);
            return new ResponseEntity(livros, HttpStatus.OK);
        } catch (NoSuchElementException ns) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", HttpStatus.BAD_REQUEST.value());
            errorResponse.put("message", "ID do empréstimo não encontrado");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.put("message", "Erro interno do servidor");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/livros/generos/{genero}")
    public ResponseEntity<?> listarLivrosPorGenero(@PathVariable String genero) {
        try {
            List<Livro> livros = livroService.listarLivrosPorGenero(genero);
            return new ResponseEntity(livros, HttpStatus.OK);
        } catch (NoSuchElementException ns) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", HttpStatus.BAD_REQUEST.value());
            errorResponse.put("message", "ID do empréstimo não encontrado");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.put("message", "Erro interno do servidor");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/livros/{id}")
    public ResponseEntity<?> obterLivro(@PathVariable UUID id) {
        try {
            Livro livro = livroService.obterLivro(id);
            return new ResponseEntity(livro, HttpStatus.OK);
        } catch (NoSuchElementException ns) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", HttpStatus.BAD_REQUEST.value());
            errorResponse.put("message", "ID do empréstimo não encontrado");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.put("message", "Erro interno do servidor");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/livros/titulos/{titulo}")
    public ResponseEntity<?> listarLivrosPorTitulo(@PathVariable String titulo) {
        try {
            List<Livro> livros = livroService.listarLivrosPorTitulo(titulo);
            return new ResponseEntity(livros, HttpStatus.OK);
        } catch (NoSuchElementException ns) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", HttpStatus.BAD_REQUEST.value());
            errorResponse.put("message", "ID do empréstimo não encontrado");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.put("message", "Erro interno do servidor");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public String getLivrosPopulares() {
        List<Livro> livrosPopulares = livroService.getLivrosPopulares();
        modelo.addAttribute("livrosPopulares", livrosPopulares);
        return;

    }
}