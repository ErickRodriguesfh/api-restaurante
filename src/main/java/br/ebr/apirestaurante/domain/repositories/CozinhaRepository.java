package br.ebr.apirestaurante.domain.repositories;

import br.ebr.apirestaurante.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

}
