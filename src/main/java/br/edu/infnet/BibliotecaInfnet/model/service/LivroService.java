package br.edu.infnet.BibliotecaInfnet.model.service;

import br.edu.infnet.BibliotecaInfnet.exception.LivroNaoDisponivelException;
import br.edu.infnet.BibliotecaInfnet.model.domain.Emprestimo;
import br.edu.infnet.BibliotecaInfnet.model.domain.Livro;
import br.edu.infnet.BibliotecaInfnet.model.domain.Usuario;
import br.edu.infnet.BibliotecaInfnet.model.repository.EmprestimoRepository;
import br.edu.infnet.BibliotecaInfnet.model.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;




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
        return livroRepository.findByAutor(autor);
    }
    public List<Livro> listarLivrosPorTitulo(String titulo) {
        return livroRepository.findByTitulo(titulo);
    }
    public List<Livro> listarLivrosPorGenero(String genero) {
        return livroRepository.findByGenero(genero);
    }

    public void deletarLivro(UUID id) {
        livroRepository.deleteById(id);
    }


}




