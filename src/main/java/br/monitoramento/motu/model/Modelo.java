package br.monitoramento.motu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "T_CM_MODELO")
public class Modelo {

    @Id
    @Column(name = "id_modelo")
    private Integer id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "nm_modelo", nullable = false, length = 100)
    private String nome;

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
}
