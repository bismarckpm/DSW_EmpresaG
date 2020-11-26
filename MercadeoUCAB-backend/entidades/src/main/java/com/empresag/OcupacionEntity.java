package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ocupacion", schema = "empresag", catalog = "")
public class OcupacionEntity extends BaseEntity{
    private String nombre;

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
