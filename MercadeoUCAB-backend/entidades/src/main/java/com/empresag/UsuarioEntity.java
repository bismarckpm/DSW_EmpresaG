package com.empresag;

import javax.persistence.*;

@Entity
@Table(name = "usuario", schema = "empresag")
public class UsuarioEntity extends BaseEntity{

    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "estado")
    private int estado;
    @Basic
    @Column(name = "token")
    private String token;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_persona")
    private PersonaEntity fk_Persona;

    @ManyToOne
    @JoinColumn(name = "fk_rol")
    private RolEntity fk_Rol;

    public UsuarioEntity() {
    }

    public UsuarioEntity(long id) {
        super(id);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public PersonaEntity getFk_Persona() {
        return fk_Persona;
    }

    public void setFk_Persona(PersonaEntity fkPersona) {
        this.fk_Persona = fkPersona;
    }

    public RolEntity getFk_Rol() {
        return fk_Rol;
    }

    public void setFk_Rol(RolEntity fkRol) {
        this.fk_Rol = fkRol;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
