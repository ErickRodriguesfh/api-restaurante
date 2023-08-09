package br.ebr.apirestaurante.domain.dto;

import br.ebr.apirestaurante.domain.model.Grupo;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private Set<Grupo> grupos;

}
