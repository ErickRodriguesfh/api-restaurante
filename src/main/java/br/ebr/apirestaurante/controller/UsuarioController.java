package br.ebr.apirestaurante.controller;

import br.ebr.apirestaurante.domain.dto.UsuarioPutDto;
import br.ebr.apirestaurante.domain.dto.UsuarioRequestDTO;
import br.ebr.apirestaurante.domain.dto.UsuarioResponseDTO;
import br.ebr.apirestaurante.domain.dto.UsuarioSenhaDTO;
import br.ebr.apirestaurante.domain.model.Usuario;
import br.ebr.apirestaurante.domain.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    private final ModelMapper mapper;

    @GetMapping
    public List<UsuarioResponseDTO> listar() {
        return service.listar().stream()
                .map(usuario -> mapper.map(usuario, UsuarioResponseDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO buscarPorId(@PathVariable Long id) {
        return mapper.map(service.buscarOuFalhar(id), UsuarioResponseDTO.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrar(@RequestBody @Valid UsuarioRequestDTO usuario) {
        service.cadastrar(mapper.map(usuario, Usuario.class));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void atualizar(@RequestBody @Valid UsuarioPutDto usuario, @PathVariable Long id) {
        service.atualizar(usuario, id);
    }

    @PutMapping("/{id}/alterar-senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@RequestBody @Valid UsuarioSenhaDTO usuario, @PathVariable Long id) {
        service.alterarSenha(usuario, id);
    }

}
