package com.empresag;

public class PersonaNvlacademicoDto extends BaseDto {
    private PersonaDto fkPersona;
    private NivelAcademicoDto fkNivelAcademico;

    public PersonaDto getFkPersona() {
        return fkPersona;
    }

    public void setFkPersona(PersonaDto fkPersona) {
        this.fkPersona = fkPersona;
    }

    public NivelAcademicoDto getFkNivelAcademico() {
        return fkNivelAcademico;
    }

    public void setFkNivelAcademico(NivelAcademicoDto fkNivelAcademico) {
        this.fkNivelAcademico = fkNivelAcademico;
    }
}
