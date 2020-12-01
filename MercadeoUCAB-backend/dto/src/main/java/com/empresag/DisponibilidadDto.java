package com.empresag;

import java.sql.Time;

public class DisponibilidadDto extends BaseDto {

    private Time horaInicial;
    private Time horaFinal;

    public DisponibilidadDto() {
    }

    public DisponibilidadDto(long id) throws IndexDatabaseException {
        super(id);
    }

    public Time getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(Time horaInicial) {
        this.horaInicial = horaInicial;
    }

    public Time getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Time horaFinal) {
        this.horaFinal = horaFinal;
    }
}
