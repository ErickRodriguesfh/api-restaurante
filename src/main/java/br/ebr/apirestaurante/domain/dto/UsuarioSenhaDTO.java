package br.ebr.apirestaurante.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioSenhaDTO {

    @NotBlank
    private String senhaAtual;

    @NotBlank
    private String novaSenha;

}
