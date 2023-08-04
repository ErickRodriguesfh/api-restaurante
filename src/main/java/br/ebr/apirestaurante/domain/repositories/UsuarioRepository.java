package br.ebr.apirestaurante.domain.repositories;

import br.ebr.apirestaurante.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
