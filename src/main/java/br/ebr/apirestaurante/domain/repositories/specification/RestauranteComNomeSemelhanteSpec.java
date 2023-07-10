package br.ebr.apirestaurante.domain.repositories.specification;

import br.ebr.apirestaurante.domain.model.Restaurante;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestauranteComNomeSemelhanteSpec implements Specification<Restaurante> {

    private String nome;

    public RestauranteComNomeSemelhanteSpec(String nome) {
        this.nome = nome;
    }

    @Override
    public Predicate toPredicate(
            Root<Restaurante> root, CriteriaQuery<?> query, CriteriaBuilder builder
    ) {
        return builder.like(root.get("nome"), "%" + nome + "%");
    }

}
