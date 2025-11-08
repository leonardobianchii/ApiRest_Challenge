package br.monitoramento.motu.controller;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import br.monitoramento.motu.dto.LocalizacaoMotoRfidDto;
import br.monitoramento.motu.service.LocalizacaoMotoRfidService;

@RestController
@RequestMapping("/api/motos/localizacoes")
public class LocalizacaoMotoRfidController {

    private final LocalizacaoMotoRfidService service;

    public LocalizacaoMotoRfidController(LocalizacaoMotoRfidService service) {
        this.service = service;
    }

    @GetMapping
    public List<LocalizacaoMotoRfidDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public LocalizacaoMotoRfidDto getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<LocalizacaoMotoRfidDto> create(@Valid @RequestBody LocalizacaoMotoRfidDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public LocalizacaoMotoRfidDto update(@PathVariable Integer id, @Valid @RequestBody LocalizacaoMotoRfidDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
