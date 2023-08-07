package br.ebr.apirestaurante.controller;

import br.ebr.apirestaurante.domain.dto.FormaPagamentoDTO;
import br.ebr.apirestaurante.domain.services.FormaPagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
@RequiredArgsConstructor
public class RestauranteFormaPagamentoController {

    private final FormaPagamentoService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FormaPagamentoDTO> buscarFormasPagamentoRestaurante(@PathVariable Long restauranteId) {
        return service.buscarFormaPagamentoRestaurante(restauranteId);
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        service.removerFormaPagamento(restauranteId, formaPagamentoId);
    }

    @PutMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.OK)
    public void associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        service.associarFormaPagamento(restauranteId, formaPagamentoId);
    }

}
