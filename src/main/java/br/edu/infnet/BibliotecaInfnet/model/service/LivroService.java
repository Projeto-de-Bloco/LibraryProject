package br.edu.infnet.BibliotecaInfnet.model.service;

import br.edu.infnet.BibliotecaInfnet.model.domain.Livro;
import br.edu.infnet.BibliotecaInfnet.model.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;


    public Livro criarLivro(Livro livro) {
        return livroRepository.save(livro);
    }
    public List<Livro> listarLivros() {
        return (List<Livro>) livroRepository.findAll();
    }
    public Livro obterLivro(UUID id) {
        return livroRepository.findById(id).orElse(null);
    }
    public List<Livro> listarLivrosPorAutor(String autor) {
        return livroRepository.listarLivrosPorAutor(autor);
    }
    public List<Livro> listarLivrosPorTitulo(String titulo) {
        return livroRepository.listarLivrosPorTitulo(titulo);
    }
    public List<Livro> listarLivrosPorGenero(String genero) {
        return livroRepository.listarlivrosPorGenero(genero);
    }

    public void deletarLivro(UUID id) {
        livroRepository.deleteById(id);
    }
}
