package br.monitoramento.motu.dto;

import java.time.LocalDateTime;

public class LocalizacaoMotoRFIDDTO {

    private Long idMoto;
    private Long idSensor;
    private Double nrX;
    private Double nrY;
    private LocalDateTime dtLocalizacao;

    public Long getIdMoto() {
        return idMoto;
    }

    public void setIdMoto(Long idMoto) {
        this.idMoto = idMoto;
    }

    public Long getIdSensor() {
        return idSensor;
    }

    public void setIdSensor(Long idSensor) {
        this.idSensor = idSensor;
    }

    public Double getNrX() {
        return nrX;
    }

    public void setNrX(Double nrX) {
        this.nrX = nrX;
    }

    public Double getNrY() {
        return nrY;
    }

    public void setNrY(Double nrY) {
        this.nrY = nrY;
    }

    public LocalDateTime getDtLocalizacao() {
        return dtLocalizacao;
    }

    public void setDtLocalizacao(LocalDateTime dtLocalizacao) {
        this.dtLocalizacao = dtLocalizacao;
    }
}
