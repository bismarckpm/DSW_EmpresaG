package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "persona_nvlacademico", schema = "empresag", catalog = "")
public class PersonaNvlacademicoEntity extends BaseEntity{
    private int id;
    private PersonaEntity fkPersona;
    private NivelAcademicoEntity fkNivelAcademico;

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
    @JoinColumn(name = "fk_nivel_academico")
    public NivelAcademicoEntity getFkNivelAcademico() {
        return fkNivelAcademico;
    }

    public void setFkNivelAcademico(NivelAcademicoEntity fkNivelAcademico) {
        this.fkNivelAcademico = fkNivelAcademico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonaNvlacademicoEntity that = (PersonaNvlacademicoEntity) o;
        return id == that.id &&
                fkPersona == that.fkPersona &&
                fkNivelAcademico == that.fkNivelAcademico;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fkPersona, fkNivelAcademico);
    }
}
