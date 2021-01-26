package com.empresag;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "persona_ocupacion", schema = "empresag", catalog = "")
public class PersonaOcupacionEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "fk_persona")
    private PersonaEntity fkPersona;
    @ManyToOne
    @JoinColumn(name = "fk_ocupacion")
    private OcupacionEntity fkOcupacion;

    public PersonaOcupacionEntity(long id) {
        super(id);
    }

    public PersonaOcupacionEntity() {
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
    @JoinColumn(name = "fk_ocupacion")
    public OcupacionEntity getFkOcupacion() {
        return fkOcupacion;
    }

    public void setFkOcupacion(OcupacionEntity fkOcupacion) {
        this.fkOcupacion = fkOcupacion;
    }

}
