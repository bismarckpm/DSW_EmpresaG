package com.empresag;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ocupacion", schema = "empresag", catalog = "")
public class OcupacionEntity extends BaseEntity{
    private String nombre;

    public OcupacionEntity(long id) {
        super(id);
    }

    public OcupacionEntity() {
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
