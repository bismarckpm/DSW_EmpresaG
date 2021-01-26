package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "telefono", schema = "empresag", catalog = "")
public class TelefonoEntity extends BaseEntity{
    private String numero;
    @ManyToOne
    @JoinColumn(name = "fk_persona")
    private PersonaEntity fkPersona;

    @Basic
    @Column(name = "numero")
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @ManyToOne
    @JoinColumn(name = "fk_persona")
    public PersonaEntity getFkPersona() {
        return fkPersona;
    }

    public void setFkPersona(PersonaEntity fkPersona) {
        this.fkPersona = fkPersona;
    }

}
