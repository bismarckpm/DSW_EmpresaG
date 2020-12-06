package com.empresag;

import java.sql.Time;

public class DisponibilidadDto extends BaseDto {

    private Time hora;

    public DisponibilidadDto() {
    }

    public DisponibilidadDto(long id) throws IndexDatabaseException {
        super(id);
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }
}
