package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "presentacion", schema = "empresag")
public class PresentacionEntity extends BaseEntity{
    @Basic
    @Column(name = "nombre")
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "descripcion")
    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public PresentacionEntity() {
    }

    public PresentacionEntity(long id) {
        super(id);
    }

    public PresentacionEntity(long id, String nombre) {
        super(id);
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "PresentacionEntity{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
