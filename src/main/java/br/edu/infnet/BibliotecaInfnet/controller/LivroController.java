package br.edu.infnet.BibliotecaInfnet.controller;

import br.edu.infnet.BibliotecaInfnet.model.domain.Livro;
import br.edu.infnet.BibliotecaInfnet.model.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
public class LivroController {
    @Autowired
    private LivroService livroService;

    @PostMapping
    public ResponseEntity<Object> criarLivro(@RequestBody Livro livro) {
        ResponseEntity<Object> retorno = ResponseEntity.badRequest().build();

        Livro gravado = livroService.criarLivro(livro);
        retorno = ResponseEntity.status(201).body(gravado);
        return retorno;
    }

    @GetMapping()
    public ResponseEntity<Object> listarLivros() {
        ResponseEntity<Object> retorno = ResponseEntity.notFound().build();

        try {
            List<Livro> lista = livroService.obterLista();
            if(!lista.isEmpty()) {
                retorno = ResponseEntity.ok().body(lista);
            }
        }catch(Exception e) {
        }
        return retorno;
    }

    @GetMapping(path = "{autor}")
    public ResponseEntity<Object> listarPorAutor(@PathVariable String autor) {
        ResponseEntity<Object> retorno = ResponseEntity.notFound().build();

        try {
            List<Livro> lista = livroService.findByAutor(autor);
            if(!lista.isEmpty()) {
                retorno = ResponseEntity.ok().body(lista);
            }
        }catch(Exception e) {
        }
        return retorno;
    }
    @GetMapping(path = "{genero}")
    public ResponseEntity<Object> listarPorGenero(@PathVariable String genero) {
        ResponseEntity<Object> retorno = ResponseEntity.notFound().build();

        try {
            List<Livro> lista = livroService.findByGenero(genero);
            if(!lista.isEmpty()) {
                retorno = ResponseEntity.ok().body(lista);
            }
        }catch(Exception e) {
        }
        return retorno;
    }
    @GetMapping(path = "{titulo}")
    public ResponseEntity<Object> listarPorTitulo(@PathVariable String titulo) {
        ResponseEntity<Object> retorno = ResponseEntity.notFound().build();

        try {
            List<Livro> lista = livroService.findByTitulo(titulo);
            if(!lista.isEmpty()) {
                retorno = ResponseEntity.ok().body(lista);
            }
        }catch(Exception e) {
        }
        return retorno;
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<Object> listarPorId(@PathVariable UUID id) {
        ResponseEntity<Object> retorno = ResponseEntity.notFound().build();

        try {
            Livro livro = livroService.findById(id);
            retorno = ResponseEntity.ok().body(livro);
        }catch(Exception e) {
        }
        return retorno;
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Object> atualizarLivro(@PathVariable UUID id, @RequestBody Livro livro) {
        ResponseEntity<Object> retorno = ResponseEntity.badRequest().build();

        if(livro != null && id != null ) {
            Livro livroGravado = livroService.findById(id);
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

    @DeleteMapping(path = "/delete/{id}")
    public void deletarLivroById(@PathVariable UUID id) {
        Livro livroDeletado = livroService.findById(id);
        if(livroDeletado != null) {
            try {
                livroService.excluir(id);
            }catch(Exception e) {
            }
        }
    }

}
