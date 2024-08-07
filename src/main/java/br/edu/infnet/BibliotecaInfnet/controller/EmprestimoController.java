package br.edu.infnet.BibliotecaInfnet.controller;

import br.edu.infnet.BibliotecaInfnet.model.domain.Emprestimo;
import br.edu.infnet.BibliotecaInfnet.model.dto.EmprestimoDto;
import br.edu.infnet.BibliotecaInfnet.model.service.EmprestimoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Biblioteca INFNET - EmprestimoController")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping("/emprestimo")
    public ResponseEntity<?> criarEmprestimo(@RequestBody EmprestimoDto emprestimoDto) {
        try {
            Emprestimo emprestimoCriado = emprestimoService.criarEmprestimo(emprestimoDto);
            return new ResponseEntity(emprestimoCriado, HttpStatus.CREATED);
        } catch (NullPointerException np) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", HttpStatus.BAD_REQUEST.value());
            errorResponse.put("message", "O corpo do pedido de empréstimo está vazio.");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            e.printStackTrace();
            errorResponse.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.put("message", "Erro interno do servidor");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/emprestimo")
    public ResponseEntity<?> listarEmprestimos() {
        try {
            List<Emprestimo> emprestimos = emprestimoService.listarEmprestimos();
            return new ResponseEntity(emprestimos, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            e.printStackTrace();
            errorResponse.put("message", "Erro interno do servidor");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/emprestimo/devolver/{id}")
    public  ResponseEntity<?> devolverEmprestimo(@PathVariable UUID id) {
        try {
            Emprestimo emprestimo = emprestimoService.devolverEmprestimo(id);
            //notifyService.notificar("EmprestimoController.atualizarEmprestimo", emprestimo.toString());
            return new ResponseEntity(emprestimo, HttpStatus.OK);
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

    @DeleteMapping("/emprestimo/{id}")
    public ResponseEntity<?> deletarEmprestimo(@PathVariable UUID id) {
        try {
            emprestimoService.deletarEmprestimo(id);
            return new ResponseEntity(HttpStatus.OK);
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

    @GetMapping("/emprestimo/{id}")
    public ResponseEntity<?> buscarEmprestimoPorId(@PathVariable("id") UUID id) {
        try {
            Emprestimo emprestimo = emprestimoService.buscarEmprestimoPorId(id);
            return new ResponseEntity(emprestimo, HttpStatus.OK);
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

    @GetMapping("/emprestimo/usuario/{usuario}")
    public ResponseEntity<?> listarEmprestimoPorUsuario(@PathVariable("usuario") UUID id_usuario) {
        try {
            List<Emprestimo> emprestimos = emprestimoService.listarEmprestimosPorUsuario(id_usuario);
            return new ResponseEntity(emprestimos, HttpStatus.OK);
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

    @GetMapping("/emprestimo/livro/{livro}")
    public ResponseEntity<?> getEmprestimoByLivro(@PathVariable("livro") UUID id_livro) {
        try {
            List<Emprestimo> emprestimos = emprestimoService.listarEmprestimosPorLivro(id_livro);
            return new ResponseEntity(emprestimos, HttpStatus.OK);
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

    @PutMapping("/emprestimo/renovar/{id}")
    public ResponseEntity<?> renovarEmprestimo(@PathVariable UUID id) {
        try {
            Emprestimo emprestimoRenovado = emprestimoService.renovarEmprestimo(id);
            if (emprestimoRenovado != null) {
                return new ResponseEntity<>(emprestimoRenovado, HttpStatus.OK);
            } else {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("code", HttpStatus.BAD_REQUEST.value());
                errorResponse.put("message", "Empréstimo não encontrado");    
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.put("message", "Erro interno do servidor");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/alunos/{alunoId}/emprestimos")
    public List<Emprestimo> listarEmprestimosNaoDevolvidos(@PathVariable Long usuarioId) {
        return bibliotecaService.listarEmprestimosNaoDevolvidos(usuarioId);
    }

    @PostMapping("/emprestimos/{emprestimoId}/devolver")
    public void devolverLivro(@PathVariable Long emprestimoId) {
        bibliotecaService.devolverLivro(emprestimoId);
    }
}
