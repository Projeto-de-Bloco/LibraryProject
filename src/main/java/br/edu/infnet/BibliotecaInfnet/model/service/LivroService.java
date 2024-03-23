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
    public List<Livro> obterLista() {
        return (List<Livro>) livroRepository.findAll();
    }
    public Livro findById(UUID id) {
        return livroRepository.findById(id).orElse(null);
    }
    public List<Livro> findByAutor(String autor) {
        return livroRepository.findByAutor(autor);
    }
    public List<Livro> findByTitulo(String titulo) {
        return livroRepository.findByTitulo(titulo);
    }
    public List<Livro> findByGenero(String genero) {
        return livroRepository.findByGenero(genero);
    }

    public void excluir(UUID id) {
        livroRepository.deleteById(id);
    }
}
