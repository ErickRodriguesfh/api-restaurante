package br.ebr.apirestaurante.domain.services;

import br.ebr.apirestaurante.domain.exception.EntidadeNaoEncontradaException;
import br.ebr.apirestaurante.domain.model.Cozinha;
import br.ebr.apirestaurante.domain.repositories.CozinhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CozinhaService {

    private final CozinhaRepository repository;

    public List<Cozinha> listar() {
        return repository.findAll();
    }

    public Cozinha buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cozinha não encontrada com o id: " +id));
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

    public List<Cozinha> consultarPorNome(String nome) {
        return repository.findByNomeContaining(nome);
    }

}
