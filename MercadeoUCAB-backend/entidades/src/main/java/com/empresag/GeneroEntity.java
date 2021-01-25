package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "genero", schema = "empresag", catalog = "")
public class GeneroEntity extends BaseEntity{
    private String nombre;

    public GeneroEntity(long id) {
        super(id);
    }

    public GeneroEntity() {

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
        return "GeneroEntity{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
