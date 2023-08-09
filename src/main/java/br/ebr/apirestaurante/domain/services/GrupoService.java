package br.ebr.apirestaurante.domain.services;

import br.ebr.apirestaurante.domain.dto.GrupoPermissaoDTO;
import br.ebr.apirestaurante.domain.exception.EntidadeNaoEncontradaException;
import br.ebr.apirestaurante.domain.model.Grupo;
import br.ebr.apirestaurante.domain.repositories.GrupoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GrupoService {

    private final GrupoRepository repository;
    private final PermissaoService permissaoService;

    public GrupoPermissaoDTO buscarPermissaoGrupo(Long idGrupo) {
        final var grupo = buscarOuFalhar(idGrupo);
        return new GrupoPermissaoDTO(grupo);
    }

    @Transactional
    public void desassociarPermissaoGrupo(Long idGrupo, Long idPermissao) {
        final var grupo = buscarOuFalhar(idGrupo);
        final var permissao = permissaoService.buscarOuFalhar(idPermissao);
        grupo.removerPermissao(permissao);
    }

    @Transactional
    public void associarPermissaoGrupo(Long idGrupo, Long idPermissao) {
        final var grupo = buscarOuFalhar(idGrupo);
        final var permissao = permissaoService.buscarOuFalhar(idPermissao);
        grupo.adicionarPermissao(permissao);
    }

    public Grupo buscarOuFalhar(Long idGrupo) {
        return repository.findById(idGrupo)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Grupo não encontrado com o id %d", idGrupo)));
    }

}
