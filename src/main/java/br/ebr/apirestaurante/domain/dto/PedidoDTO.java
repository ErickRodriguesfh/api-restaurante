package br.ebr.apirestaurante.domain.dto;

import br.ebr.apirestaurante.domain.model.StatusPedido;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PedidoDTO {

    private Long id;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private StatusPedido status;
    private LocalDateTime dataCriacao;
    private RestauranteResumoDTO restaurante;
    private UsuarioPedidoDTO cliente;

}
