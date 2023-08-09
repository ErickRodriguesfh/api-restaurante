package br.ebr.apirestaurante.domain.services;

import br.ebr.apirestaurante.domain.exception.EntidadeNaoEncontradaException;
import br.ebr.apirestaurante.domain.model.Produto;
import br.ebr.apirestaurante.domain.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;
    private final RestauranteService restauranteService;

    public Produto adicionarProdutoAoRestaurante(Produto produto, Long idRestaurante) {
        final var restaurante = restauranteService.buscarOuFalhar(idRestaurante);
        produto.setRestaurante(restaurante);

        return repository.save(produto);
    }

    public Set<Produto> buscarProdutosRestaurante(Long idRestaurante) {
        restauranteService.buscarOuFalhar(idRestaurante);

        return repository.findByRestauranteId(idRestaurante);
    }

    @Transactional
    public Produto atualizar(Produto produto, Long idRestaurante, Long idProduto) {
        final var produtoSalvo = repository.findByIdAndRestauranteId(idProduto, idRestaurante)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Produto não encontrado, verifique se os ids informados estão corretos")));

        produtoSalvo.setNome(produto.getNome());
        produtoSalvo.setDescricao(produto.getDescricao());
        produtoSalvo.setPreco(produto.getPreco());
        produtoSalvo.setAtivo(produto.isAtivo());

        return produtoSalvo;
    }

}
