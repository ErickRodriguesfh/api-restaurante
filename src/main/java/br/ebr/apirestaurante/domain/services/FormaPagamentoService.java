package br.ebr.apirestaurante.domain.services;

import br.ebr.apirestaurante.domain.dto.FormaPagamentoDTO;
import br.ebr.apirestaurante.domain.exception.EntidadeNaoEncontradaException;
import br.ebr.apirestaurante.domain.model.FormaPagamento;
import br.ebr.apirestaurante.domain.repositories.FormaPagamentoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FormaPagamentoService {

    private final RestauranteService restauranteService;
    private final FormaPagamentoRepository repository;

    public List<FormaPagamentoDTO> buscarFormaPagamentoRestaurante(Long idRestaurante) {
        final var restaurante = restauranteService.buscarOuFalhar(idRestaurante);

        return restaurante.getFormaPagamentos()
                .stream()
                .map(item -> {
                    final var formaPagamento = new FormaPagamentoDTO();
                    formaPagamento.setId(item.getId());
                    formaPagamento.setDescricao(item.getDescricao());
                    return formaPagamento;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void removerFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        final var restaurante = restauranteService.buscarOuFalhar(restauranteId);
        final var formaPagamento = buscarOuFalhar(formaPagamentoId);

        restaurante.removerFormaPagamento(formaPagamento);
    }

    @Transactional
    public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        final var restaurante = restauranteService.buscarOuFalhar(restauranteId);
        final var formaPagamento = buscarOuFalhar(formaPagamentoId);

        restaurante.associarFormaPagamento(formaPagamento);
    }

    public FormaPagamento buscarOuFalhar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Forma de pagamento não encontrada com o id %d", id)));
    }

}
