package br.monitoramento.motu.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "T_CM_MOTO")
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMoto;

    @Column(name = "nm_modelo", nullable = false)
    private String modelo;

    @ManyToOne
    @JoinColumn(name = "id_filial_departamento", nullable = false)
    private FilialDepartamento filialDepartamento;

    @NotBlank
    @Column(name = "nm_placa", nullable = false, unique = true)
    private String placa;

    @Column(name = "st_moto")
    private String statusMoto;

    @Column(name = "km_rodado")
    private Integer kmRodado;

    public Long getIdMoto() {
        return idMoto;
    }

    public void setIdMoto(Long idMoto) {
        this.idMoto = idMoto;
    }

    public FilialDepartamento getFilialDepartamento() {
        return filialDepartamento;
    }

    public void setFilialDepartamento(FilialDepartamento filialDepartamento) {
        this.filialDepartamento = filialDepartamento;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getStatusMoto() {
        return statusMoto;
    }

    public void setStatusMoto(String statusMoto) {
        this.statusMoto = statusMoto;
    }

    public Integer getKmRodado() {
        return kmRodado;
    }

    public void setKmRodado(Integer kmRodado) {
        this.kmRodado = kmRodado;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
