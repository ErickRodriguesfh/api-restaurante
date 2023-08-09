package br.ebr.apirestaurante.domain.dto;

import br.ebr.apirestaurante.domain.model.Grupo;
import br.ebr.apirestaurante.domain.model.Permissao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class GrupoPermissaoDTO {

    private Long id;
    private Set<Permissao> permissoes = new HashSet<>();

    public GrupoPermissaoDTO(Grupo grupo) {
        this.id = grupo.getId();
        this.permissoes.addAll(grupo.getPermissoes());
    }

}
