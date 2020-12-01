package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "edo_civil", schema = "empresag", catalog = "")
public class EdoCivilEntity extends BaseEntity{
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
