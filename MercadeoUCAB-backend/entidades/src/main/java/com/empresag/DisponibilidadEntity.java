package com.empresag;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "disponibilidad", schema = "empresag", catalog = "")
public class DisponibilidadEntity extends BaseEntity{
    private int id;
    private Time horaInicial;
    private Time horaFinal;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DisponibilidadEntity that = (DisponibilidadEntity) o;
        return id == that.id &&
                Objects.equals(horaInicial, that.horaInicial) &&
                Objects.equals(horaFinal, that.horaFinal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, horaInicial, horaFinal);
    }
}
