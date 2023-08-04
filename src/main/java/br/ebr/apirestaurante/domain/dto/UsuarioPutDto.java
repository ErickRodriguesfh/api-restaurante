package br.ebr.apirestaurante.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioPutDto {

    @NotBlank
    private String nome;

    @Email
    @NotBlank
    private String email;

}
