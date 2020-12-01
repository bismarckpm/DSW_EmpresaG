package com.empresag;

public class PersonaOcupacionDto extends BaseDto {
    private PersonaDto fkPersona;
    private OcupacionDto fkOcupacion;

    public PersonaDto getFkPersona() {
        return fkPersona;
    }

    public void setFkPersona(PersonaDto fkPersona) {
        this.fkPersona = fkPersona;
    }

    public OcupacionDto getFkOcupacion() {
        return fkOcupacion;
    }

    public void setFkOcupacion(OcupacionDto fkOcupacion) {
        this.fkOcupacion = fkOcupacion;
    }
}
