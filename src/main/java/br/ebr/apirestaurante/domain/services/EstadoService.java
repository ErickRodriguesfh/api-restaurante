package br.ebr.apirestaurante.domain.services;

import br.ebr.apirestaurante.domain.model.Estado;
import br.ebr.apirestaurante.domain.repositories.EstadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    private final EstadoRepository repository;

    public EstadoService(EstadoRepository repository) {
        this.repository = repository;
    }

    public List<Estado> listarEstados() {
        return repository.findAll();
    }

}
