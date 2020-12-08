package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "solicitud", schema = "empresag")
public class SolicitudEntity extends BaseEntity{
    @Basic
    @Column(name = "estado")
    private Integer estado;

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private UsuarioEntity fkUsuario;

    public UsuarioEntity getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(UsuarioEntity fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

}
