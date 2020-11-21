package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "solicitud_estudio", schema = "empresag", catalog = "")
public class SolicitudEstudioEntity extends BaseEntity{
    private int id;
    private int estado;
    private int fkSolicitud;
    private int fkEstudio;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolicitudEstudioEntity that = (SolicitudEstudioEntity) o;
        return id == that.id &&
                estado == that.estado &&
                fkSolicitud == that.fkSolicitud &&
                fkEstudio == that.fkEstudio;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, estado, fkSolicitud, fkEstudio);
    }
}
