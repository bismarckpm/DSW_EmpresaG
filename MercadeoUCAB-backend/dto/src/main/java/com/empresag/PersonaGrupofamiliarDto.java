package com.empresag;

public class PersonaGrupofamiliarDto extends BaseDto {
    private PersonaDto fkPersona;
    private GrupoFamiliarDto fkGrupoFamiliar;

    public PersonaDto getFkPersona() {
        return fkPersona;
    }

    public void setFkPersona(PersonaDto fkPersona) {
        this.fkPersona = fkPersona;
    }

    public GrupoFamiliarDto getFkGrupoFamiliar() {
        return fkGrupoFamiliar;
    }

    public void setFkGrupoFamiliar(GrupoFamiliarDto fkGrupoFamiliar) {
        this.fkGrupoFamiliar = fkGrupoFamiliar;
    }
}
