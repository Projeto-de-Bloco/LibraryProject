package br.edu.infnet.BibliotecaInfnet.controller;

import br.edu.infnet.BibliotecaInfnet.model.domain.Emprestimo;
import br.edu.infnet.BibliotecaInfnet.model.service.EmprestimoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/emprestimos", produces = {"application/json"})
@Tag(name = "Biblioteca INFNET - EmprestimoController")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping
    public ResponseEntity<Emprestimo> criarEmprestimo(@RequestBody Emprestimo emprestimo) {
        Emprestimo emprestimoCriado = emprestimoService.criarEmprestimo(emprestimo);
        if (emprestimoCriado != null) {
            return new ResponseEntity(emprestimo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> listarEmprestimos() {
        List<Emprestimo> emprestimos = emprestimoService.listarEmprestimos();
        return new ResponseEntity(emprestimos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Emprestimo> atualizarEmprestimo(@PathVariable UUID id, @RequestBody Emprestimo emprestimo) {
        Emprestimo emprestimoModificado = emprestimoService.listarEmprestimosPorId(id);
        emprestimoService.atualizarEmprestimo(emprestimoModificado);
        return new ResponseEntity(emprestimoModificado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Emprestimo> deletarEmprestimo(@PathVariable UUID id) {
        emprestimoService.deletarEmprestimo(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> listarEmprestimosPorId(@PathVariable("id") UUID id) {
        Emprestimo emprestimo = emprestimoService.listarEmprestimosPorId(id);
        if (emprestimo != null) {
            return new ResponseEntity(emprestimo, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{usuario}")
    public ResponseEntity<Emprestimo> listarEmprestimosPorUsuario(@PathVariable("usuario") UUID id_usuario) {
        List<Emprestimo> emprestimos = emprestimoService.listarEmprestimosPorUsuario(id_usuario);
        if (emprestimos != null) {
            return new ResponseEntity(emprestimos, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{livro}")
    public ResponseEntity<Emprestimo> listarEmprestimosPorLivro(@PathVariable("livro") UUID id_livro) {
        List<Emprestimo> emprestimos = emprestimoService.listarEmprestimosPorLivro(id_livro);
        if (emprestimos != null) {
            return new ResponseEntity(emprestimos, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
