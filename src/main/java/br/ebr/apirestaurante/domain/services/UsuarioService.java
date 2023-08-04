package br.ebr.apirestaurante.domain.services;

import br.ebr.apirestaurante.domain.dto.UsuarioPutDto;
import br.ebr.apirestaurante.domain.dto.UsuarioSenhaDTO;
import br.ebr.apirestaurante.domain.exception.EntidadeNaoEncontradaException;
import br.ebr.apirestaurante.domain.exception.NegocioException;
import br.ebr.apirestaurante.domain.model.Usuario;
import br.ebr.apirestaurante.domain.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public void cadastrar(Usuario usuario) {
        repository.save(usuario);
    }

    public void atualizar(UsuarioPutDto usuario, Long id) {
        final var usuarioSalvo = buscarOuFalhar(id);

        usuarioSalvo.setEmail(usuario.getEmail());
        usuarioSalvo.setNome(usuario.getNome());

        repository.save(usuarioSalvo);
    }

    public void alterarSenha(UsuarioSenhaDTO usuarioSenhaDTO, Long id) {
        final var usuarioSalvo = buscarOuFalhar(id);

        if (!usuarioSalvo.getSenha().equals(usuarioSenhaDTO.getSenhaAtual())) {
            throw new NegocioException("Senha atual inválida");
        }

        if (Objects.equals(usuarioSalvo.getSenha(), usuarioSenhaDTO.getNovaSenha())) {
            throw new NegocioException("A nova senha deve ser diferente da senha antiga");
        }

        usuarioSalvo.setSenha(usuarioSenhaDTO.getNovaSenha());
        repository.save(usuarioSalvo);
    }

    public Usuario buscarOuFalhar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Usuário não encontrado com o id %d", id)));
    }

}
