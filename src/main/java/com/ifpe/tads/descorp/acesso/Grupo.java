/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.acesso;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author eduardo.amaral
 */
@Entity
@Table(name = "TB_GRUPO")
@NamedQueries(
    {
        @NamedQuery(name = "Grupo.PorNome", query = "SELECT G FROM Grupo G WHERE g.nome = :grupo_nome")

    }
)
public class Grupo implements Serializable {

    @Id
    private Long id;

    @NotBlank
    @Column(name = "TXT_NOME", unique = true)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
