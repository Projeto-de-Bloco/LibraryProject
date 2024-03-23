package br.edu.infnet.BibliotecaInfnet.model.service;

import br.edu.infnet.BibliotecaInfnet.model.domain.Usuario;
import br.edu.infnet.BibliotecaInfnet.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getUserList() {
        return usuarioRepository.findAll();
    }

    public Usuario obterUsuario(UUID id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario createUser(Usuario user) {
        return usuarioRepository.save(user);
    }

    public Usuario updateUserById(Usuario user) {
        if (user.getId() != null && usuarioRepository.existsById(user.getId())) {
            return usuarioRepository.save(user);
        } else {
            return null;
        }
    }

    public void deleteUserById(UUID id) {
        usuarioRepository.deleteById(id);
    }
}
