package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "persona_grupofamiliar", schema = "empresag", catalog = "")
public class PersonaGrupofamiliarEntity extends BaseEntity{
    private PersonaEntity fkPersona;
    private GrupoFamiliarEntity fkGrupoFamiliar;

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

}
