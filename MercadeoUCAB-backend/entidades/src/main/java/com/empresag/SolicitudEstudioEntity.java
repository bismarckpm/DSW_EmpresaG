package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "solicitud_estudio", schema = "empresag", catalog = "")
public class SolicitudEstudioEntity extends BaseEntity{
    private int estado;
    private int fkSolicitud;
    private int fkEstudio;


    @Basic
    @Column(name = "estado")
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Basic
    @Column(name = "fk_solicitud")
    public int getFkSolicitud() {
        return fkSolicitud;
    }

    public void setFkSolicitud(int fkSolicitud) {
        this.fkSolicitud = fkSolicitud;
    }

    @Basic
    @Column(name = "fk_estudio")
    public int getFkEstudio() {
        return fkEstudio;
    }

    public void setFkEstudio(int fkEstudio) {
        this.fkEstudio = fkEstudio;
    }

}
