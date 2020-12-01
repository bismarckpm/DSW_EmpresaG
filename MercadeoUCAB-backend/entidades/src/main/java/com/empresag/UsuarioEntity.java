package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "usuario", schema = "empresag", catalog = "")
public class UsuarioEntity extends BaseEntity{
    private String email;
    private String password;
    private int estado;
    private PersonaEntity fkPersona;
    private RolEntity fkRol;

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "estado")
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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
    @JoinColumn(name = "fk_rol")
    public RolEntity getFkRol() {
        return fkRol;
    }

    public void setFkRol(RolEntity fkRol) {
        this.fkRol = fkRol;
    }

}
