package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tipo_pregunta", schema = "empresag", catalog = "")
public class TipoPreguntaEntity extends BaseEntity{
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

}
