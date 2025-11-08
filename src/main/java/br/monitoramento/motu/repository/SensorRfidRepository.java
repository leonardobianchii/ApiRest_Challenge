package br.monitoramento.motu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.monitoramento.motu.model.SensorRfid;

public interface SensorRfidRepository extends JpaRepository<SensorRfid, Integer> { }
