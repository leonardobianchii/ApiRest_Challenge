package br.monitoramento.motu.controller;

import java.util.List;

import br.monitoramento.motu.dto.ModeloDto;
import br.monitoramento.motu.service.ModeloService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/modelos")
public class ModeloController {

    private final ModeloService service;

    public ModeloController(ModeloService service) {
        this.service = service;
    }

    @GetMapping
    public List<ModeloDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ModeloDto getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<ModeloDto> create(@Valid @RequestBody ModeloDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ModeloDto update(@PathVariable Integer id, @Valid @RequestBody ModeloDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
