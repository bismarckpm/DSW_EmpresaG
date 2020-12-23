package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "nivel_socioeconomico", schema = "empresag", catalog = "")
public class NivelSocioeconomicoEntity extends BaseEntity{
    private String nombre;

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
        return "NivelSocioeconomicoEntity{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
