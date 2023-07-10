package br.ebr.apirestaurante.domain.repositories;

import br.ebr.apirestaurante.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}
