package br.ebr.apirestaurante.controller;

import br.ebr.apirestaurante.domain.dto.ProdutoDTO;
import br.ebr.apirestaurante.domain.model.Produto;
import br.ebr.apirestaurante.domain.services.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
@RequiredArgsConstructor
public class ProdutoRestauranteController {

    private final ProdutoService service;
    private final ModelMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDTO adicionarProdutoAoRestaurante(@RequestBody ProdutoDTO produto, @PathVariable Long restauranteId) {
        final var produtoAssociado = service
                .adicionarProdutoAoRestaurante(mapper.map(produto, Produto.class), restauranteId);

        return mapper.map(produtoAssociado, ProdutoDTO.class);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<ProdutoDTO> buscarProdutosRestaurante(@PathVariable Long restauranteId) {
        return service.buscarProdutosRestaurante(restauranteId)
                .stream()
                .map(produto -> mapper.map(produto, ProdutoDTO.class))
                .collect(Collectors.toSet());
    }

    @PutMapping("/{idProduto}")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoDTO atualizar(@RequestBody ProdutoDTO produtoDTO, @PathVariable Long restauranteId,
            @PathVariable Long idProduto) {
        final var produtoAtualizado =
                service.atualizar(mapper.map(produtoDTO, Produto.class), restauranteId, idProduto);
        return mapper.map(produtoAtualizado, ProdutoDTO.class);
    }

}
