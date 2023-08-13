package br.ebr.apirestaurante.controller;

import br.ebr.apirestaurante.domain.dto.UsuarioReponsavelDTO;
import br.ebr.apirestaurante.domain.services.RestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurantes/{idRestaurante}/responsaveis")
@RequiredArgsConstructor
public class RestauranteUsuarioController {

    private final RestauranteService restauranteService;

    @GetMapping
    public UsuarioReponsavelDTO buscarUsuarioResponsavel(@PathVariable Long idRestaurante) {
        return restauranteService.buscarUsuarioResponsavel(idRestaurante);
    }

}
