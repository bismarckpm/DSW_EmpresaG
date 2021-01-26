package com.empresag;

import javax.persistence.*;

@Entity
@Table(name = "usuario", schema = "empresag")
public class UsuarioEntity extends BaseEntity{
    private String email;
    private String password;
    @Basic
    @Column(name = "estado")
    private int estado;
    @ManyToOne
    @JoinColumn(name = "fk_persona")
    private PersonaEntity fk_Persona;
    @ManyToOne
    @JoinColumn(name = "fk_rol")
    private RolEntity fk_Rol;

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
    public PersonaEntity getFk_Persona() {
        return fk_Persona;
    }

    public void setFk_Persona(PersonaEntity fkPersona) {
        this.fk_Persona = fkPersona;
    }

    @ManyToOne
    @JoinColumn(name = "fk_rol")
    public RolEntity getFk_Rol() {
        return fk_Rol;
    }

    public void setFk_Rol(RolEntity fkRol) {
        this.fk_Rol = fkRol;
    }

    @Override
    public String toString() {
        return "UsuarioEntity{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", estado=" + estado +
                ", fk_Persona=" + fk_Persona.toString() +
                ", fk_Rol=" + fk_Rol.toString() +
                '}';
    }
}
