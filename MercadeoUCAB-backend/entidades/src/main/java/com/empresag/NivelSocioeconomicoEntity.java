package com.empresag;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "nivel_socioeconomico", schema = "empresag", catalog = "")
public class NivelSocioeconomicoEntity extends BaseEntity{
    @Basic
    @Column(name = "nombre")
    private String nombre;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fkNivelSocioeconomico")
    private List<LugarEntity> lugares;

    public NivelSocioeconomicoEntity(long id) {
        super(id);
    }

    public NivelSocioeconomicoEntity() {
    }

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
