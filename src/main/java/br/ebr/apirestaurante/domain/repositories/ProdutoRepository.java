package br.ebr.apirestaurante.domain.repositories;

import br.ebr.apirestaurante.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Set<Produto> findByRestauranteId(Long idRestaurante);

    Optional<Produto> findByIdAndRestauranteId(Long id, Long restauranteId);

}
