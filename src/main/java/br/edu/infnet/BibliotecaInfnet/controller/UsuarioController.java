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
    private UsuarioService services;

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getAllUsers() {
        return ResponseEntity.ok(services.getUserList());
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable UUID id) {
        Usuario user = services.obterUsuario(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> addUser(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(this.services.createUser(usuario));
    }


    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> updateUser(@RequestBody Usuario user, @PathVariable int id) {
        return ResponseEntity.ok().body(this.services.updateUserById(user));
    }

    @DeleteMapping("/usuarios/{id}")
    public HttpStatus deleteUser(@PathVariable UUID id) {
        this.services.deleteUserById(id);
        return HttpStatus.OK;
    }

}