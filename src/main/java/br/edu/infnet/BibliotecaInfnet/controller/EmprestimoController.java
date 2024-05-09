package br.edu.infnet.BibliotecaInfnet.controller;

import br.edu.infnet.BibliotecaInfnet.model.domain.Emprestimo;
import br.edu.infnet.BibliotecaInfnet.model.service.EmprestimoService;
import br.edu.infnet.BibliotecaInfnet.model.service.NotifyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Biblioteca INFNET - EmprestimoController")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;
    @Autowired
    private NotifyService notifyService;

    @PostMapping("/emprestimo")
    public ResponseEntity<Emprestimo> criarEmprestimo(@RequestBody Emprestimo emprestimo) throws JsonProcessingException {
        Emprestimo emprestimoCriado = emprestimoService.criarEmprestimo(emprestimo);
        if (emprestimoCriado != null) {

            notifyService.notificar("EmprestimoController.criarEmprestimo", emprestimo.toString());


            return new ResponseEntity(emprestimo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/emprestimo")
    public ResponseEntity<List<Emprestimo>> listarEmpestimos() {
        List<Emprestimo> emprestimos = emprestimoService.listarEmprestimos();
        return new ResponseEntity(emprestimos, HttpStatus.OK);
    }

    @PutMapping("/emprestimo/{id}")
    public  ResponseEntity<Emprestimo> atualizarEmprestimo(@PathVariable UUID id, @RequestBody Emprestimo emprestimo) throws JsonProcessingException {
        Emprestimo emprestimoModificado = emprestimoService.listarEmprestimosPorId(id);
        emprestimoService.atualizarEmprestimo(emprestimoModificado);
        notifyService.notificar("EmprestimoController.atualizarEmprestimo", emprestimo.toString());

        return new ResponseEntity(emprestimoModificado, HttpStatus.OK);
    }

    @DeleteMapping("/emprestimo/{id}")
    public ResponseEntity<Emprestimo> deletarEmprestimo(@PathVariable UUID id) {
        emprestimoService.deletarEmprestimo(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/emprestimo/{id}")
    public ResponseEntity<Emprestimo> listarEmprestimoPorId(@PathVariable("id") UUID id) {
        Emprestimo emprestimo = emprestimoService.listarEmprestimosPorId(id);
        if (emprestimo != null) {
            return new ResponseEntity(emprestimo, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/emprestimo/{usuario}")
    public ResponseEntity<Emprestimo> listarEmprestimoPorUsuario(@PathVariable("usuario") UUID id_usuario) {
        List<Emprestimo> emprestimos = emprestimoService.listarEmprestimosPorUsuario(id_usuario);
        if (emprestimos != null) {
            return new ResponseEntity(emprestimos, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/emprestimo/{livro}")
    public ResponseEntity<Emprestimo> getEmprestimoByLivro(@PathVariable("livro") UUID id_livro) {
        List<Emprestimo> emprestimos = emprestimoService.listarEmprestimosPorLivro(id_livro);
        if (emprestimos != null) {
            return new ResponseEntity(emprestimos, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
