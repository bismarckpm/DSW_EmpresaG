package com.empresag;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "disponibilidad", schema = "empresag", catalog = "")
public class DisponibilidadEntity extends BaseEntity{

    @Basic
    @Column(name = "hora")
    @JsonbDateFormat(value = "HH:mm a")
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
