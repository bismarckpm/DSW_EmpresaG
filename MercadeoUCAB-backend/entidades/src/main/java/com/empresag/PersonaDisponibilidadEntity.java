package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "persona_disponibilidad", schema = "empresag", catalog = "")
public class PersonaDisponibilidadEntity extends BaseEntity{
    private int id;
    private PersonaEntity fkPersona;
    private DisponibilidadEntity fkDisponibilidad;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "fk_persona")
    public PersonaEntity getFkPersona() {
        return fkPersona;
    }

    public void setFkPersona(PersonaEntity fkPersona) {
        this.fkPersona = fkPersona;
    }

    @ManyToOne
    @JoinColumn(name = "fk_disponibilidad")
    public DisponibilidadEntity getFkDisponibilidad() {
        return fkDisponibilidad;
    }

    public void setFkDisponibilidad(DisponibilidadEntity fkDisponibilidad) {
        this.fkDisponibilidad = fkDisponibilidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonaDisponibilidadEntity that = (PersonaDisponibilidadEntity) o;
        return id == that.id &&
                fkPersona == that.fkPersona &&
                fkDisponibilidad == that.fkDisponibilidad;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fkPersona, fkDisponibilidad);
    }
}
