package br.edu.infnet.BibliotecaInfnet.controller;

import br.edu.infnet.BibliotecaInfnet.model.domain.Livro;
import br.edu.infnet.BibliotecaInfnet.model.domain.Usuario;
import br.edu.infnet.BibliotecaInfnet.model.service.LivroService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Biblioteca INFNET - LivroController")
public class LivroController {
    @Autowired
    private LivroService livroService;
    // get livros por usuarioId
    @PostMapping("/livros/{id}")
    public ResponseEntity<Object> criarLivro(@RequestBody Livro livro) {
        ResponseEntity<Object> retorno = ResponseEntity.badRequest().build();

        Livro gravado = livroService.criarLivro(livro);
        retorno = ResponseEntity.status(201).body(gravado);
        return retorno;
    }

    @GetMapping("/livros")
    public ResponseEntity<Object> listarLivros() {
        ResponseEntity<Object> retorno = ResponseEntity.notFound().build();

        try {
            List<Livro> lista = livroService.listarLivros();
            if(!lista.isEmpty()) {
                retorno = ResponseEntity.ok().body(lista);
            }
        }catch(Exception e) {
        }

        return retorno;
    }

    @PutMapping("/livros/{id}")
    public ResponseEntity<Object> atualizarLivro(@PathVariable UUID id, @RequestBody Livro livro) {
        ResponseEntity<Object> retorno = ResponseEntity.badRequest().build();

        if(livro != null && id != null ) {
            Livro livroGravado = livroService.obterLivro(id);
            if(livroGravado != null) {
                try {
                    livro.setId(id);
                    livroGravado = livroService.criarLivro(livro);
                    retorno = ResponseEntity.ok().body(livroGravado);
                }catch(Exception e) {
                }
            }
        }
        return retorno;
    }

    @DeleteMapping("/livros/{id}")
    public void deletarLivro(@PathVariable UUID id) {
        Livro livroDeletado = livroService.obterLivro(id);
        if(livroDeletado != null) {
            try {
                livroService.deletarLivro(id);
            }catch(Exception e) {
            }
        }
    }

    @GetMapping("/livros/autor/{autor}")
    public ResponseEntity<Object> listarLivrosPorAutor(@PathVariable String autor) {
        ResponseEntity<Object> retorno = ResponseEntity.notFound().build();

        try {
            List<Livro> lista = livroService.listarLivrosPorAutor(autor);
            if(!lista.isEmpty()) {
                retorno = ResponseEntity.ok().body(lista);
            }
        }catch(Exception e) {
        }
        return retorno;
    }
    @GetMapping("/livros/genero/{genero}")
    public ResponseEntity<Object> listarLivrosPorGenero(@PathVariable String genero) {
        ResponseEntity<Object> retorno = ResponseEntity.notFound().build();

        try {
            List<Livro> lista = livroService.listarLivrosPorGenero(genero);
            if(!lista.isEmpty()) {
                retorno = ResponseEntity.ok().body(lista);
            }
        }catch(Exception e) {
        }
        return retorno;
    }

    @GetMapping("/livros/id/{id}")
    public ResponseEntity<Object> obterLivro(@PathVariable UUID id) {
        ResponseEntity<Object> retorno = ResponseEntity.notFound().build();

        try {
            Livro livro = livroService.obterLivro(id);
            retorno = ResponseEntity.ok().body(livro);
        }catch(Exception e) {
        }
        return retorno;
    }

    @GetMapping("/livros/titulo/{titulo}")
    public ResponseEntity<Object> listarLivrosPorTitulo(@PathVariable String titulo) {
        ResponseEntity<Object> retorno = ResponseEntity.notFound().build();

        try {
            List<Livro> lista = livroService.listarLivrosPorTitulo(titulo);
            if(!lista.isEmpty()) {
                retorno = ResponseEntity.ok().body(lista);
            }
        }catch(Exception e) {
        }
        return retorno;
    }

}
