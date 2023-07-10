package br.ebr.apirestaurante.domain.services;

import br.ebr.apirestaurante.domain.model.Cozinha;
import br.ebr.apirestaurante.domain.repositories.CozinhaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CozinhaService {

    private final CozinhaRepository repository;

    public CozinhaService(CozinhaRepository repository) {
        this.repository = repository;
    }

    public List<Cozinha> listar() {
        return repository.findAll();
    }

    public Cozinha buscarPorId(Long id) {
        return repository.findById(id).get();
    }

    public void adicionarOuAtualizar(Cozinha cozinha) {
        repository.save(cozinha);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public boolean cozinhaExiste(Long idCozinha) {
        return repository.existsById(idCozinha);
    }

}
