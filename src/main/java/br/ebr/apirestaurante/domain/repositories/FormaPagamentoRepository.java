package br.ebr.apirestaurante.domain.repositories;

import br.ebr.apirestaurante.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
}
