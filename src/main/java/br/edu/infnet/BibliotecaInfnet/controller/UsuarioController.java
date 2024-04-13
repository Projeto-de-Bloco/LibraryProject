package br.edu.infnet.BibliotecaInfnet.controller;

import br.edu.infnet.BibliotecaInfnet.model.domain.Usuario;
import br.edu.infnet.BibliotecaInfnet.model.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Biblioteca INFNET - UsuarioController")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(this.usuarioService.criarUsuario(usuario));
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@RequestBody Usuario user, @PathVariable int id) {
        return ResponseEntity.ok().body(this.usuarioService.atualizarUsuarioPorId(user));
    }

    @DeleteMapping("/usuarios/{id}")
    public HttpStatus deletarUsuario(@PathVariable UUID id) {
        this.usuarioService.deletarUsuario(id);
        return HttpStatus.OK;
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> listarUsuariosPorId(@PathVariable UUID id) {
        Usuario user = usuarioService.obterUsuario(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}