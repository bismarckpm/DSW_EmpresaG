package com.empresag;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "nivel_academico", schema = "empresag")
public class NivelAcademicoEntity extends BaseEntity{
    private String nombre;

    public NivelAcademicoEntity(long id) {
        super(id);
    }

    public NivelAcademicoEntity() {

    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
