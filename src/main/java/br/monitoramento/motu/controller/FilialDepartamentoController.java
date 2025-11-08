package br.monitoramento.motu.controller;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import br.monitoramento.motu.service.FilialDepartamentoService;
import br.monitoramento.motu.dto.FilialDepartamentoDto;


@RestController
@RequestMapping("/api/filiais")
public class FilialDepartamentoController {

    private final FilialDepartamentoService service;

    public FilialDepartamentoController(FilialDepartamentoService service) {
        this.service = service;
    }

    @GetMapping
    public List<FilialDepartamentoDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public FilialDepartamentoDto getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<FilialDepartamentoDto> create(@Valid @RequestBody FilialDepartamentoDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public FilialDepartamentoDto update(@PathVariable Long id, @Valid @RequestBody FilialDepartamentoDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
