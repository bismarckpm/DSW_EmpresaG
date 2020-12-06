package com.empresag;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "disponibilidad", schema = "empresag", catalog = "")
public class DisponibilidadEntity extends BaseEntity{

    @Basic
    @Column(name = "hora")
    private Time hora;


    @Basic
    @Column(name = "hora")
    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

}
