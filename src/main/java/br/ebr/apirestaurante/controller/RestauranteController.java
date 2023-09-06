package br.ebr.apirestaurante.controller;

import br.ebr.apirestaurante.domain.dto.RestauranteDTO;
import br.ebr.apirestaurante.domain.dto.UsuarioReponsavelDTO;
import br.ebr.apirestaurante.domain.model.Restaurante;
import br.ebr.apirestaurante.domain.services.RestauranteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurantes")
@RequiredArgsConstructor
public class RestauranteController {

    private final RestauranteService service;
    private final ModelMapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RestauranteDTO> listar() {
        return service.listar().stream()
                .map(restaurante -> mapper.map(restaurante, RestauranteDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestauranteDTO buscarPorId(@PathVariable Long id) {
        return mapper.map(service.buscarPorId(id), RestauranteDTO.class);
    }

    @GetMapping("/por-taxa-frete")
    @ResponseStatus(HttpStatus.OK)
    public List<RestauranteDTO> consultarPorTaxaDeFrete(BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return service.consultarPorTaxaDeFrete(taxaInicial, taxaFinal).stream()
                .map(restaurante -> mapper.map(restaurante, RestauranteDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/com-frete-gratis")
    @ResponseStatus(HttpStatus.OK)
    public List<RestauranteDTO> restaurantesComFreteGratis(String nome) {
        return service.restaurantesComFreteGratis(nome).stream()
                .map(restaurante -> mapper.map(restaurante, RestauranteDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteDTO adicionar(@RequestBody @Valid RestauranteDTO restauranteDTO) {
        final var restauranteSalvo = service.salvar(mapper.map(restauranteDTO, Restaurante.class));

        return mapper.map(restauranteSalvo, RestauranteDTO.class);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public RestauranteDTO atualizar(@RequestBody @Valid RestauranteDTO restauranteDTO) {
        final var restauranteAtualizado = service.salvar(mapper.map(restauranteDTO, Restaurante.class));

        return mapper.map(restauranteAtualizado, RestauranteDTO.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluirRestaurante(id);
    }

    @PutMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarRestaurante(@PathVariable Long id) {
        service.ativar(id);
    }

    @DeleteMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void inativarRestaurante(@PathVariable Long id) {
        service.inativar(id);
    }

    @PutMapping(value = "/{restauranteId}/produtos/{produtoId}/foto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void atualizarFoto(
            @PathVariable Long restauranteId, @PathVariable Long produtoId, @RequestParam MultipartFile arquivo
    ) {
        var nomeArquivo = arquivo.getOriginalFilename();

        var arquivoFoto = Path.of("C:\\Users\\Erick\\Desktop\\Casamento", nomeArquivo);

        System.out.println(arquivoFoto);
        System.out.println(arquivo.getContentType());

        try {
            arquivo.transferTo(arquivoFoto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
