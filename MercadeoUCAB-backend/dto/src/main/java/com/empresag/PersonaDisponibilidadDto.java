package com.empresag;

public class PersonaDisponibilidadDto extends BaseDto {
    private PersonaDto fkPersona;
    private DisponibilidadDto fkDisponibilidad;

    public PersonaDto getFkPersona() {
        return fkPersona;
    }

    public void setFkPersona(PersonaDto fkPersona) {
        this.fkPersona = fkPersona;
    }

    public DisponibilidadDto getFkDisponibilidad() {
        return fkDisponibilidad;
    }

    public void setFkDisponibilidad(DisponibilidadDto fkDisponibilidad) {
        this.fkDisponibilidad = fkDisponibilidad;
    }
}
