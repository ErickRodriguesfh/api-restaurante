package br.ebr.apirestaurante.controller;

import br.ebr.apirestaurante.domain.dto.GrupoPermissaoDTO;
import br.ebr.apirestaurante.domain.services.GrupoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grupos/{idGrupo}/permissoes")
@RequiredArgsConstructor
public class GrupoController {

    private final GrupoService service;
    private final ModelMapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public GrupoPermissaoDTO buscarPermissaoGrupo(@PathVariable Long idGrupo) {
        return service.buscarPermissaoGrupo(idGrupo);
    }

    @DeleteMapping("/{idPermissao}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociarPermissao(@PathVariable Long idGrupo, @PathVariable Long idPermissao) {
        service.desassociarPermissaoGrupo(idGrupo, idPermissao);
    }

    @PutMapping("/{idPermissao}")
    @ResponseStatus(HttpStatus.OK)
    public void associarPermissao(@PathVariable Long idGrupo, @PathVariable Long idPermissao) {
        service.associarPermissaoGrupo(idGrupo, idPermissao);
    }

}
