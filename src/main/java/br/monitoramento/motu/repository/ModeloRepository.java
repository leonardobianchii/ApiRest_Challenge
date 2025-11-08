package br.monitoramento.motu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.monitoramento.motu.model.Modelo;

public interface ModeloRepository extends JpaRepository<Modelo, Integer> { }
