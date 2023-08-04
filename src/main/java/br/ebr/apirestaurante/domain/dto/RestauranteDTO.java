package br.ebr.apirestaurante.domain.dto;

import br.ebr.apirestaurante.domain.model.Cozinha;
import br.ebr.apirestaurante.domain.model.Endereco;
import br.ebr.apirestaurante.domain.model.FormaPagamento;
import br.ebr.apirestaurante.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RestauranteDTO {

    private Long id;

    @NotBlank
    private String nome;

    private BigDecimal taxaFrete;

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    @Valid
    @NotNull
    private Cozinha cozinha;

    private Endereco endereco;

    private Boolean ativo;

    @JsonIgnore
    private List<Produto> produto;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;

    @JsonIgnore
    private List<FormaPagamento> formaPagamentos = new ArrayList<>();

}
