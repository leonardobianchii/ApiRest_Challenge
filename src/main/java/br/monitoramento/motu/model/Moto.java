package br.monitoramento.motu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "T_CM_MOTO")
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_moto")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_modelo", nullable = false)
    private Modelo modelo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_filial_departamento", nullable = false)
    private FilialDepartamento filial;

    @NotBlank
    @Size(max = 10)
    @Column(name = "nm_placa", nullable = false, unique = true, length = 10)
    private String placa;

    @Size(max = 30)
    @Column(name = "st_moto", length = 30)
    private String status;

    @Column(name = "km_rodado")
    private Integer kmRodado;

    // =====================
    // GETTERS e SETTERS
    // =====================

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public FilialDepartamento getFilial() {
        return filial;
    }

    public void setFilial(FilialDepartamento filial) {
        this.filial = filial;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getKmRodado() {
        return kmRodado;
    }

    public void setKmRodado(Integer kmRodado) {
        this.kmRodado = kmRodado;
    }
}
