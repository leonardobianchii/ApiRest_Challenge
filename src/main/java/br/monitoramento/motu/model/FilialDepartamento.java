package br.monitoramento.motu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "T_CM_FILIAL_DEPARTAMENTO")
public class FilialDepartamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_filial_departamento")
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "nm_filial_departamento", nullable = false, length = 100)
    private String nome;

    // =====================
    // GETTERS e SETTERS
    // =====================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
