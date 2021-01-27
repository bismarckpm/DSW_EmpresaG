package com.empresag;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "edo_civil", schema = "empresag", catalog = "")
public class EdoCivilEntity extends BaseEntity{
    @Basic
    @Column(name = "nombre")
    private String nombre;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fkEdoCivil")
    private List<PersonaEntity> personas;

    public EdoCivilEntity(long id) {
        super(id);
    }

    public EdoCivilEntity() {

    }
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
