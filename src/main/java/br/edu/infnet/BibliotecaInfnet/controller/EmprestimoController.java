package br.edu.infnet.BibliotecaInfnet.controller;

import br.edu.infnet.BibliotecaInfnet.model.domain.Emprestimo;
import br.edu.infnet.BibliotecaInfnet.model.dto.EmprestimoDto;
import br.edu.infnet.BibliotecaInfnet.model.service.EmprestimoService;
import br.edu.infnet.BibliotecaInfnet.model.service.LivroService;
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
    private LivroService livroService;
    @Autowired
    private NotifyService notifyService;

    @PostMapping("/emprestimo")
    public ResponseEntity<Emprestimo> criarEmprestimo(@RequestBody EmprestimoDto emprestimoDto) throws JsonProcessingException {
        List<Emprestimo> emprestimos = emprestimoService.listarEmprestimosPorLivro(emprestimoDto.getLivro().getId());
        if (!emprestimos.isEmpty()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else {
            Emprestimo emprestimoCriado = emprestimoService.criarEmprestimo(emprestimoDto);
            notifyService.notificar("EmprestimoController.criarEmprestimo", emprestimoCriado.toString());
            return new ResponseEntity(emprestimoCriado, HttpStatus.CREATED);
        }

    }

    @GetMapping("/emprestimo")
    public ResponseEntity<List<Emprestimo>> listarEmprestimos() {
        List<Emprestimo> emprestimos = emprestimoService.listarEmprestimos();
        return new ResponseEntity(emprestimos, HttpStatus.OK);
    }

    @PutMapping("/emprestimo/devolver/{id}")
    public  ResponseEntity<Emprestimo> devolverEmprestimo(@PathVariable UUID id) throws JsonProcessingException {
        Emprestimo emprestimo = emprestimoService.devolverEmprestimo(id);
        notifyService.notificar("EmprestimoController.atualizarEmprestimo", emprestimo.toString());

        return new ResponseEntity(emprestimo, HttpStatus.OK);
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

    @GetMapping("/emprestimo/usuario/{usuario}")
    public ResponseEntity<Emprestimo> listarEmprestimoPorUsuario(@PathVariable("usuario") UUID id_usuario) {
        List<Emprestimo> emprestimos = emprestimoService.listarEmprestimosPorUsuario(id_usuario);
        if (emprestimos != null) {
            return new ResponseEntity(emprestimos, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/emprestimo/livro/{livro}")
    public ResponseEntity<Emprestimo> getEmprestimoByLivro(@PathVariable("livro") UUID id_livro) {
        List<Emprestimo> emprestimos = emprestimoService.listarEmprestimosPorLivro(id_livro);
        if (emprestimos != null) {
            return new ResponseEntity(emprestimos, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
