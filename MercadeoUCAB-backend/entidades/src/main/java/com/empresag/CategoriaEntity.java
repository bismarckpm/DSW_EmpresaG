package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categoria", schema = "empresag", catalog = "")
public class CategoriaEntity extends BaseEntity{
    private String nombre;
    private String descripcion;

    public CategoriaEntity(long id) {
        super(id);
    }

    public CategoriaEntity() {
    }

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

}
