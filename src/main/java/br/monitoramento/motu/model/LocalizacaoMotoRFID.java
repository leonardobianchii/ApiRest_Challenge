package br.monitoramento.motu.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_CM_LOCALIZACAO_MOTO_RFID")
public class LocalizacaoMotoRFID {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLocalizacao;

    @ManyToOne
    @JoinColumn(name = "id_moto", nullable = false)
    private Moto moto;

    @ManyToOne
    @JoinColumn(name = "id_sensor", nullable = false)
    private SensorRFID sensorRFID;

    @Column(name = "nr_x", nullable = false)
    private Double nrX;

    @Column(name = "nr_y", nullable = false)
    private Double nrY;

    @Column(name = "dt_localizacao", nullable = false)
    private LocalDateTime dtLocalizacao;

    public Long getIdLocalizacao() {
        return idLocalizacao;
    }

    public void setIdLocalizacao(Long idLocalizacao) {
        this.idLocalizacao = idLocalizacao;
    }

    public Moto getMoto() {
        return moto;
    }

    public void setMoto(Moto moto) {
        this.moto = moto;
    }

    public SensorRFID getSensorRFID() {
        return sensorRFID;
    }

    public void setSensorRFID(SensorRFID sensorRFID) {
        this.sensorRFID = sensorRFID;
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