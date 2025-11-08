package br.monitoramento.motu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.monitoramento.motu.model.LocalizacaoMotoRfid;

public interface LocalizacaoMotoRfidRepository extends JpaRepository<LocalizacaoMotoRfid, Integer> { }
