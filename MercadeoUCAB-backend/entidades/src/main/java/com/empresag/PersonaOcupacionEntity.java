package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "persona_ocupacion", schema = "empresag", catalog = "")
public class PersonaOcupacionEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "fk_persona")
    private PersonaEntity fkPersona;
    @ManyToOne
    @JoinColumn(name = "fk_ocupacion")
    private OcupacionEntity fkOcupacion;

    @ManyToOne
    @JoinColumn(name = "fk_persona")
    public PersonaEntity getFkPersona() {
        return fkPersona;
    }

    public void setFkPersona(PersonaEntity fkPersona) {
        this.fkPersona = fkPersona;
    }

    @ManyToOne
    @JoinColumn(name = "fk_ocupacion")
    public OcupacionEntity getFkOcupacion() {
        return fkOcupacion;
    }

    public void setFkOcupacion(OcupacionEntity fkOcupacion) {
        this.fkOcupacion = fkOcupacion;
    }

}
