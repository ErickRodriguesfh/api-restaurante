package br.ebr.apirestaurante.controller;

import br.ebr.apirestaurante.domain.dto.RestauranteDTO;
import br.ebr.apirestaurante.domain.model.Restaurante;
import br.ebr.apirestaurante.domain.services.RestauranteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurantes")
@RequiredArgsConstructor
public class RestauranteController {

    private final RestauranteService service;
    private final ModelMapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RestauranteDTO> listar() {
        return service.listar().stream()
                .map(restaurante -> mapper.map(restaurante, RestauranteDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestauranteDTO buscarPorId(@PathVariable Long id) {
        return mapper.map(service.buscarPorId(id), RestauranteDTO.class);
    }

    @GetMapping("/por-taxa-frete")
    @ResponseStatus(HttpStatus.OK)
    public List<RestauranteDTO> consultarPorTaxaDeFrete(BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return service.consultarPorTaxaDeFrete(taxaInicial, taxaFinal).stream()
                .map(restaurante -> mapper.map(restaurante, RestauranteDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/com-frete-gratis")
    @ResponseStatus(HttpStatus.OK)
    public List<RestauranteDTO> restaurantesComFreteGratis(String nome) {
        return service.restaurantesComFreteGratis(nome).stream()
                .map(restaurante -> mapper.map(restaurante, RestauranteDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteDTO adicionar(@RequestBody @Valid RestauranteDTO restauranteDTO) {
        final var restauranteSalvo = service.salvar(mapper.map(restauranteDTO, Restaurante.class));

        return mapper.map(restauranteSalvo, RestauranteDTO.class);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public RestauranteDTO atualizar(@RequestBody @Valid RestauranteDTO restauranteDTO) {
        final var restauranteAtualizado = service.salvar(mapper.map(restauranteDTO, Restaurante.class));

        return mapper.map(restauranteAtualizado, RestauranteDTO.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluirRestaurante(id);
    }

    @PutMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarRestaurante(@PathVariable Long id) {
        service.ativar(id);
    }

    @DeleteMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void inativarRestaurante(@PathVariable Long id) {
        service.inativar(id);
    }

}
