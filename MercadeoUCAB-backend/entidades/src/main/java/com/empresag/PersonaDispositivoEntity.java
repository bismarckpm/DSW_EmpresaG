package com.empresag;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "persona_dispositivo", schema = "empresag", catalog = "")
public class PersonaDispositivoEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "fk_persona")
    private PersonaEntity fkPersona;
    @ManyToOne
    @JoinColumn(name = "fk_dispositivo")
    private DispositivoEntity fkDispositivo;

    public PersonaDispositivoEntity(long id) {
        super(id);
    }

    public PersonaDispositivoEntity() {
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

}
