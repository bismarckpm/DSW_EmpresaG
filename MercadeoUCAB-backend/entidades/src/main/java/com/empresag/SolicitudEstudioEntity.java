package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "solicitud_estudio", schema = "empresag", catalog = "")
public class SolicitudEstudioEntity extends BaseEntity{
    @Basic
    @Column(name = "fk_solicitud")
    private int fkSolicitud;

    public int getFkSolicitud() {
        return fkSolicitud;
    }

    public void setFkSolicitud(int fkSolicitud) {
        this.fkSolicitud = fkSolicitud;
    }

    @Basic
    @Column(name = "fk_estudio")
    private int fkEstudio;

    public int getFkEstudio() {
        return fkEstudio;
    }

    public void setFkEstudio(int fkEstudio) {
        this.fkEstudio = fkEstudio;
    }

}
