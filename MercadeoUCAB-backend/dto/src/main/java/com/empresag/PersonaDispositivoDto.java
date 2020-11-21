package com.empresag;

public class PersonaDispositivoDto extends BaseDto {
    private PersonaDto fkPersona;
    private DispositivoDto fkDispositivo;

    public PersonaDto getFkPersona() {
        return fkPersona;
    }

    public void setFkPersona(PersonaDto fkPersona) {
        this.fkPersona = fkPersona;
    }

    public DispositivoDto getFkDispositivo() {
        return fkDispositivo;
    }

    public void setFkDispositivo(DispositivoDto fkDispositivo) {
        this.fkDispositivo = fkDispositivo;
    }
}
