package br.ebr.apirestaurante.domain.services;

import br.ebr.apirestaurante.domain.exception.EntidadeNaoEncontradaException;
import br.ebr.apirestaurante.domain.model.Permissao;
import br.ebr.apirestaurante.domain.repositories.PermissaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PermissaoService {

    private final PermissaoRepository repository;

    public Permissao buscarOuFalhar(Long idPermissao) {
        return repository.findById(idPermissao)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Permissão não encontrada com o id " +idPermissao));
    }

}
