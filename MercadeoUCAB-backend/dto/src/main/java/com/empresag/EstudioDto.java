package com.empresag;

import java.sql.Date;

public class EstudioDto extends BaseDto {
    private Date fechaRealizacion;
    private Date fechaCulminacion;
    private int estado;
    private AnalisisDto fkAnalisis;

    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public Date getFechaCulminacion() {
        return fechaCulminacion;
    }

    public void setFechaCulminacion(Date fechaCulminacion) {
        this.fechaCulminacion = fechaCulminacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public AnalisisDto getFkAnalisis() {
        return fkAnalisis;
    }

    public void setFkAnalisis(AnalisisDto fkAnalisis) {
        this.fkAnalisis = fkAnalisis;
    }
}
