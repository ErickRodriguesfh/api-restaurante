package br.ebr.apirestaurante.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaDTO {

    private Long id;

    @NotBlank
    private String nome;

}
