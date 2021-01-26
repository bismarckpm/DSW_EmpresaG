package com.empresag;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "marca", schema = "empresag", catalog = "")
public class MarcaEntity extends BaseEntity{
    private String nombre;
    private String descripcion;

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "MarcaEntity{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    public MarcaEntity(long id) {
        super(id);
    }

    public MarcaEntity() {
    }

    public MarcaEntity(long id, String nombre) {
        super(id);
        this.nombre = nombre;
    }
}
