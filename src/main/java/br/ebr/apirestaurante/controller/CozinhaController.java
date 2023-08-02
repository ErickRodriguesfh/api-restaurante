package br.ebr.apirestaurante.controller;

import br.ebr.apirestaurante.domain.dto.CozinhaDTO;
import br.ebr.apirestaurante.domain.model.Cozinha;
import br.ebr.apirestaurante.domain.services.CozinhaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cozinhas")
@RequiredArgsConstructor
public class CozinhaController {

    private final CozinhaService service;
    private final ModelMapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CozinhaDTO> listarCozinhas() {
        return service.listar().stream()
                .map(cozinha -> mapper.map(cozinha, CozinhaDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/por-nome")
    List<CozinhaDTO> consultarPorNome(@RequestParam("nome") String nome) {
        return service.consultarPorNome(nome).stream()
                .map(cozinha -> mapper.map(cozinha, CozinhaDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CozinhaDTO buscarPorId(@PathVariable Long id) {
        return mapper.map(service.buscarPorId(id), CozinhaDTO.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody Cozinha cozinha) {
        service.adicionarOuAtualizar(cozinha);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void atualizar(@RequestBody Cozinha cozinha) {
        service.adicionarOuAtualizar(cozinha);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

}
