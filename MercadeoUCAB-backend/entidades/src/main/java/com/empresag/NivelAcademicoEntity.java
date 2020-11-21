package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "nivel_academico", schema = "empresag", catalog = "")
public class NivelAcademicoEntity extends BaseEntity{
    private int id;
    private String nombre;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NivelAcademicoEntity that = (NivelAcademicoEntity) o;
        return id == that.id &&
                Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }
}
