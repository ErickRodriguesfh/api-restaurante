package br.ebr.apirestaurante.controller;

import br.ebr.apirestaurante.domain.model.Restaurante;
import br.ebr.apirestaurante.domain.services.RestauranteService;
import org.springframework.http.HttpStatus;
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

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final RestauranteService service;

    public RestauranteController(RestauranteService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Restaurante> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Restaurante buscarPorId(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/por-taxa-frete")
    public List<Restaurante> consultarPorTaxaDeFrete(BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return service.consultarPorTaxaDeFrete(taxaInicial, taxaFinal);
    }

    @GetMapping("/com-frete-gratis")
    public List<Restaurante> restaurantesComFreteGratis(String nome) {
        return service.restaurantesComFreteGratis(nome);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante adicionar(@RequestBody Restaurante restaurante) {
        return service.salvar(restaurante);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Restaurante atualizar(@RequestBody Restaurante restaurante) {
        return service.salvar(restaurante);
    }

}
