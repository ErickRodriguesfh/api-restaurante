package br.ebr.apirestaurante.domain.services;

import br.ebr.apirestaurante.domain.dto.UsuarioReponsavelDTO;
import br.ebr.apirestaurante.domain.dto.UsuarioResponseDTO;
import br.ebr.apirestaurante.domain.exception.EntidadeNaoEncontradaException;
import br.ebr.apirestaurante.domain.model.Restaurante;
import br.ebr.apirestaurante.domain.repositories.RestauranteRepository;
import br.ebr.apirestaurante.domain.repositories.specification.RestauranteSpecs;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RestauranteService {

    private final RestauranteRepository repository;
    private final CozinhaService cozinhaService;

    public List<Restaurante> listar() {
        return repository.findAll();
    }

    public Restaurante buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Restaurante não encontrado com o id: " +id));
    }

    public Restaurante salvar(Restaurante restaurante) {
        final var idCozinha = restaurante.getCozinha().getId();

        if (!cozinhaService.cozinhaExiste(idCozinha)) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cadastro de cozinha com código %d", idCozinha));
        }

        if (Objects.nonNull(restaurante.getId()) && repository.existsById(restaurante.getId())) {
            final var restauranteSalvo = repository.findById(restaurante.getId());

            restauranteSalvo.ifPresent(valor -> restaurante.setDataCadastro(valor.getDataCadastro()));
        }

        return repository.save(restaurante);
    }

    public List<Restaurante> consultarPorTaxaDeFrete(BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return repository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
    }

    public List<Restaurante> restaurantesComFreteGratis(String nome) {
        return repository.findAll(RestauranteSpecs.comFreteGratis().and(RestauranteSpecs.comNomeSemelhante(nome)));
    }

    public void excluirRestaurante(Long id) {
        if (!repository.existsById(id)) {
            throw new EntidadeNaoEncontradaException(String.format("Restaurante com o id %d não encontrado", id));

        }

        repository.deleteById(id);
    }

    public UsuarioReponsavelDTO buscarUsuarioResponsavel(Long idRestaurante) {
        final var usuarios = buscarOuFalhar(idRestaurante).getUsuarios();
        final var usuarioResponsavel = new UsuarioReponsavelDTO();

        usuarios.forEach(usuario -> {
            usuarioResponsavel.setEmail(usuario.getEmail());
            usuarioResponsavel.setId(usuario.getId());
            usuarioResponsavel.setNome(usuario.getNome());
        });

        return usuarioResponsavel;
    }

    @Transactional
    public void ativar(Long id) {
        final var restauranteAtual = buscarOuFalhar(id);
        restauranteAtual.ativar();
    }

    @Transactional
    public void inativar(Long id) {
        final var restauranteAtual = buscarOuFalhar(id);
        restauranteAtual.inativar();
    }

    public Restaurante buscarOuFalhar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Restaurante não encontrado com o id " +id));
    }

}
