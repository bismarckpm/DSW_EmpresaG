package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "persona_disponibilidad", schema = "empresag", catalog = "")
public class PersonaDisponibilidadEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "fk_persona")
    private PersonaEntity fkPersona;
    @ManyToOne
    @JoinColumn(name = "fk_disponibilidad")
    private DisponibilidadEntity fkDisponibilidad;

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

}
