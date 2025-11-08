package br.monitoramento.motu.controller;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import br.monitoramento.motu.dto.SensorRfidDto;
import br.monitoramento.motu.service.SensorRfidService;


@RestController
@RequestMapping("/api/sensores")
public class SensorRfidController {

    private final SensorRfidService service;

    public SensorRfidController(SensorRfidService service) {
        this.service = service;
    }

    @GetMapping
    public List<SensorRfidDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public SensorRfidDto getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<SensorRfidDto> create(@Valid @RequestBody SensorRfidDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public SensorRfidDto update(@PathVariable Integer id, @Valid @RequestBody SensorRfidDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
