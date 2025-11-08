package br.monitoramento.motu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_CM_LOCALIZACAO_MOTO_RFID")
public class LocalizacaoMotoRfid {

    @Id
    @Column(name = "id_localizacao")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_moto", nullable = false)
    private Moto moto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_sensor", nullable = false)
    private SensorRfid sensor;

    @NotNull
    @Column(name = "dt_localizacao", nullable = false)
    private LocalDateTime data;

    // =====================
    // GETTERS e SETTERS
    // =====================

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Moto getMoto() { return moto; }
    public void setMoto(Moto moto) { this.moto = moto; }

    public SensorRfid getSensor() { return sensor; }
    public void setSensor(SensorRfid sensor) { this.sensor = sensor; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }
}
