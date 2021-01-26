package com.empresag;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Time;

@Entity
@Table(name = "disponibilidad", schema = "empresag", catalog = "")
public class DisponibilidadEntity extends BaseEntity{

    @Basic
    @Column(name = "hora")
    @JsonbDateFormat(value = "HH:mm a")
    private Time hora;

    public DisponibilidadEntity(long id) {
        super(id);
    }

    public DisponibilidadEntity() {
    }

    @Basic
    @Column(name = "hora")
    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

}
