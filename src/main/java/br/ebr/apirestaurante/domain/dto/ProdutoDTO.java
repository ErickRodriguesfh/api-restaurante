package br.ebr.apirestaurante.domain.dto;


import br.ebr.apirestaurante.domain.model.Restaurante;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private boolean ativo;

}
