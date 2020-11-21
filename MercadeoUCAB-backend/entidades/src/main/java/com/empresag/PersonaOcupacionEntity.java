package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "persona_ocupacion", schema = "empresag", catalog = "")
public class PersonaOcupacionEntity extends BaseEntity{
    private int id;
    private PersonaEntity fkPersona;
    private OcupacionEntity fkOcupacion;

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
    @JoinColumn(name = "fk_ocupacion")
    public OcupacionEntity getFkOcupacion() {
        return fkOcupacion;
    }

    public void setFkOcupacion(OcupacionEntity fkOcupacion) {
        this.fkOcupacion = fkOcupacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonaOcupacionEntity that = (PersonaOcupacionEntity) o;
        return id == that.id &&
                fkPersona == that.fkPersona &&
                fkOcupacion == that.fkOcupacion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fkPersona, fkOcupacion);
    }
}
