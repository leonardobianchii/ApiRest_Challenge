package br.monitoramento.motu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.monitoramento.motu.model.Moto;

public interface MotoRepository extends JpaRepository<Moto, Integer> {}
