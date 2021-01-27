package com.empresag;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "disponibilidad", schema = "empresag", catalog = "")
public class DisponibilidadEntity extends BaseEntity{

    @Basic
    @Column(name = "hora")
    @JsonbDateFormat(value = "HH:mm a")
    private Time hora;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fkDisponibilidadInicial")
    private List<PersonaEntity> personasI;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fkDisponibilidadFinal")
    private List<PersonaEntity> personasF;

    public DisponibilidadEntity(long id) {
        super(id);
    }

    public DisponibilidadEntity() {
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

}
