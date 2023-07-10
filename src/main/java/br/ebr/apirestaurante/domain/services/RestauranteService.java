package br.ebr.apirestaurante.domain.services;

import br.ebr.apirestaurante.domain.exception.EntidadeNaoEncontradaException;
import br.ebr.apirestaurante.domain.model.Restaurante;
import br.ebr.apirestaurante.domain.repositories.RestauranteRepository;
import br.ebr.apirestaurante.domain.repositories.specification.RestauranteComFreteGratisSpec;
import br.ebr.apirestaurante.domain.repositories.specification.RestauranteComNomeSemelhanteSpec;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RestauranteService {

    private final RestauranteRepository repository;
    private final CozinhaService cozinhaService;

    public RestauranteService(
            RestauranteRepository repository, CozinhaService cozinhaService
    ) {
        this.repository = repository;
        this.cozinhaService = cozinhaService;
    }

    public List<Restaurante> listar() {
        return repository.findAll();
    }

    public Restaurante findById(Long id) {
        return repository.findById(id).get();
    }

    public Restaurante salvar(Restaurante restaurante) {
        final var idCozinha = restaurante.getCozinha().getId();

        if (!cozinhaService.cozinhaExiste(idCozinha)) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cadastro de cozinha com código %d", idCozinha));
        }

        return repository.save(restaurante);
    }

    public List<Restaurante> consultarPorTaxaDeFrete(BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return repository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
    }

    public List<Restaurante> restaurantesComFreteGratis(String nome) {
        var comFreteGratis = new RestauranteComFreteGratisSpec();
        var comNomeSemelhante = new RestauranteComNomeSemelhanteSpec(nome);

        return repository.findAll(comFreteGratis.and(comNomeSemelhante));
    }

}
