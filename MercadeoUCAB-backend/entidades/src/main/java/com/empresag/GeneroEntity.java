package com.empresag;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genero", schema = "empresag", catalog = "")
public class GeneroEntity extends BaseEntity{

    @Basic
    @Column(name = "nombre")
    private String nombre;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fkGenero")
    private List<PersonaEntity> personas;

    public GeneroEntity(long id) {
        super(id);
    }

    public GeneroEntity() {

    }

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
