package br.ebr.apirestaurante.domain.services;

import br.ebr.apirestaurante.domain.dto.PedidoDTO;
import br.ebr.apirestaurante.domain.dto.RestauranteResumoDTO;
import br.ebr.apirestaurante.domain.dto.UsuarioPedidoDTO;
import br.ebr.apirestaurante.domain.exception.EntidadeNaoEncontradaException;
import br.ebr.apirestaurante.domain.model.Pedido;
import br.ebr.apirestaurante.domain.repositories.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;
    private final ModelMapper mapper;

    public PedidoDTO buscarPorId(Long idPedido) {
        final var pedido = buscarOuFalhar(idPedido);
        return pedidoToPedidoDto(pedido);
    }

    public List<PedidoDTO> listarPedidos() {
        final var pedidos = repository.findAll();
        return pedidos.stream()
                .map(this::pedidoToPedidoDto)
                .collect(Collectors.toList());
    }

    public PedidoDTO pedidoToPedidoDto(Pedido pedido) {
        final var pedidoDto = mapper.map(pedido, PedidoDTO.class);
        final var restauranteResumoDto = new RestauranteResumoDTO();
        restauranteResumoDto.setId(pedido.getRestaurante().getId());
        restauranteResumoDto.setNome(pedido.getRestaurante().getNome());
        pedidoDto.setRestaurante(restauranteResumoDto);

        final var usuarioPedido = new UsuarioPedidoDTO();
        usuarioPedido.setEmail(pedido.getCliente().getEmail());
        usuarioPedido.setId(pedido.getCliente().getId());
        usuarioPedido.setNome(pedido.getCliente().getNome());
        pedidoDto.setCliente(usuarioPedido);

        return pedidoDto;
    }

    public Pedido buscarOuFalhar(Long idPedido) {
        return repository.findById(idPedido)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pedido não encontrado com o id " +idPedido));
    }

}
