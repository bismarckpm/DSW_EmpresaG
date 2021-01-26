package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "persona_nvlacademico", schema = "empresag", catalog = "")
public class PersonaNvlacademicoEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "fk_persona")
    private PersonaEntity fkPersona;
    @ManyToOne
    @JoinColumn(name = "fk_nivel_academico")
    private NivelAcademicoEntity fkNivelAcademico;

    @ManyToOne
    @JoinColumn(name = "fk_persona")
    public PersonaEntity getFkPersona() {
        return fkPersona;
    }

    public void setFkPersona(PersonaEntity fkPersona) {
        this.fkPersona = fkPersona;
    }

    @ManyToOne
    @JoinColumn(name = "fk_nivel_academico")
    public NivelAcademicoEntity getFkNivelAcademico() {
        return fkNivelAcademico;
    }

    public void setFkNivelAcademico(NivelAcademicoEntity fkNivelAcademico) {
        this.fkNivelAcademico = fkNivelAcademico;
    }

}
