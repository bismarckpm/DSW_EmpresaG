package com.empresag;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "disponibilidad", schema = "empresag", catalog = "")
public class DisponibilidadEntity extends BaseEntity{

    @Basic
    @Column(name = "hora_inicial")
    private Time horaInicial;
    @Basic
    @Column(name = "hora_final")
    private Time horaFinal;


    @Basic
    @Column(name = "hora_inicial")
    public Time getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(Time horaInicial) {
        this.horaInicial = horaInicial;
    }

    @Basic
    @Column(name = "hora_final")
    public Time getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Time horaFinal) {
        this.horaFinal = horaFinal;
    }

}
