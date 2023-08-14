package br.ebr.apirestaurante.domain.repositories;

import br.ebr.apirestaurante.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("from Pedido p join fetch p.cliente join fetch p.restaurante r join fetch r.cozinha " +
            "join fetch p.enderecoEntrega.cidade c join fetch c.estado")
    List<Pedido> findAll();

}
