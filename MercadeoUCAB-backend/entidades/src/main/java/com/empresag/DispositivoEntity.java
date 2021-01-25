package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dispositivo", schema = "empresag", catalog = "")
public class DispositivoEntity extends BaseEntity{

    private String nombre;

    public DispositivoEntity(long id) {
        super(id);
    }

    public DispositivoEntity() {

    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
