package com.empresag;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "edo_civil", schema = "empresag", catalog = "")
public class EdoCivilEntity extends BaseEntity{
    private String nombre;

    public EdoCivilEntity(long id) {
        super(id);
    }

    public EdoCivilEntity() {

    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "EdoCivilEntity{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
