package br.ebr.apirestaurante.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioReponsavelDTO {

    private Long id;
    private String nome;
    private String email;

}
