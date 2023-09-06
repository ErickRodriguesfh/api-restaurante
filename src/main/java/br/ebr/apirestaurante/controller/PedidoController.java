package br.ebr.apirestaurante.controller;

import br.ebr.apirestaurante.domain.dto.PedidoDTO;
import br.ebr.apirestaurante.domain.services.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService service;

    @GetMapping("/{codigo}")
    public PedidoDTO buscarPorId(@PathVariable String codigo) {
        return service.buscarPorId(codigo);
    }

    @GetMapping
    public List<PedidoDTO> buscarPorId() {
        return service.listarPedidos();
    }

}
