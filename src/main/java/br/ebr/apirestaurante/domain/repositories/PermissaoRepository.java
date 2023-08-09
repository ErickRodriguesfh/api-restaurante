package br.ebr.apirestaurante.domain.repositories;

import br.ebr.apirestaurante.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
}
