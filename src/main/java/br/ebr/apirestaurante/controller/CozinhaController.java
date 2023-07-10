package br.ebr.apirestaurante.controller;

import br.ebr.apirestaurante.domain.model.Cozinha;
import br.ebr.apirestaurante.domain.services.CozinhaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    private final CozinhaService service;

    public CozinhaController(CozinhaService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cozinha> listarCozinhas() {
        return service.listar();
    }

    @GetMapping("/por-nome")
    List<Cozinha> consultarPorNome(@RequestParam("nome") String nome) {
        return service.consultarPorNome(nome);
    }

    @GetMapping("/{id}")
    public Cozinha buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
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
