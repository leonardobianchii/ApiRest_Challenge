package br.monitoramento.motu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "T_CM_SENSOR_RFID")
public class SensorRfid {

    @Id
    @Column(name = "id_sensor")
    private Integer id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "nm_sensor", nullable = false, length = 100)
    private String nome;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_filial_departamento", nullable = false)
    private FilialDepartamento filial;

    @Size(max = 100)
    @Column(name = "nm_localizacao", length = 100)
    private String localizacao;

    // =====================
    // GETTERS e SETTERS
    // =====================

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public FilialDepartamento getFilial() {
        return filial;
    }

    public void setFilial(FilialDepartamento filial) {
        this.filial = filial;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}
