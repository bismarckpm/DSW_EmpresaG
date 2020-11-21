package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "persona_dispositivo", schema = "empresag", catalog = "")
public class PersonaDispositivoEntity extends BaseEntity{
    private int id;
    private PersonaEntity fkPersona;
    private DispositivoEntity fkDispositivo;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "fk_persona")
    public PersonaEntity getFkPersona() {
        return fkPersona;
    }

    public void setFkPersona(PersonaEntity fkPersona) {
        this.fkPersona = fkPersona;
    }

    @ManyToOne
    @JoinColumn(name = "fk_dispositivo")
    public DispositivoEntity getFkDispositivo() {
        return fkDispositivo;
    }

    public void setFkDispositivo(DispositivoEntity fkDispositivo) {
        this.fkDispositivo = fkDispositivo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonaDispositivoEntity that = (PersonaDispositivoEntity) o;
        return id == that.id &&
                fkPersona == that.fkPersona &&
                fkDispositivo == that.fkDispositivo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fkPersona, fkDispositivo);
    }
}
