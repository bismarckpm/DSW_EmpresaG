package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "solicitud", schema = "empresag", catalog = "")
public class SolicitudEntity extends BaseEntity{
    private String tipo;
    private UsuarioEntity fkUsuario;

    @Basic
    @Column(name = "tipo")
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    public UsuarioEntity getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(UsuarioEntity fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

}
