package com.empresag;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rol", schema = "empresag", catalog = "")
public class RolEntity extends BaseEntity{
    private String nombre;

    public RolEntity(long id) {
        super(id);
    }

    public RolEntity() {

    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fk_Rol")
    private List<UsuarioEntity> usuarios;

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
        return "RolEntity{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
