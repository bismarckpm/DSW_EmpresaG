package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "persona_grupofamiliar", schema = "empresag", catalog = "")
public class PersonaGrupofamiliarEntity extends BaseEntity{
    private int id;
    private PersonaEntity fkPersona;
    private GrupoFamiliarEntity fkGrupoFamiliar;

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
    @JoinColumn(name = "fk_grupo_familiar")
    public GrupoFamiliarEntity getFkGrupoFamiliar() {
        return fkGrupoFamiliar;
    }

    public void setFkGrupoFamiliar(GrupoFamiliarEntity fkGrupoFamiliar) {
        this.fkGrupoFamiliar = fkGrupoFamiliar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonaGrupofamiliarEntity that = (PersonaGrupofamiliarEntity) o;
        return id == that.id &&
                fkPersona == that.fkPersona &&
                fkGrupoFamiliar == that.fkGrupoFamiliar;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fkPersona, fkGrupoFamiliar);
    }
}
