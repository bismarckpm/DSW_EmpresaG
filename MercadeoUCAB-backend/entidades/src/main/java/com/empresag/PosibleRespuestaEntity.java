package com.empresag;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "posible_respuesta", schema = "empresag")
public class PosibleRespuestaEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "fk_pregunta")
    private PreguntaEntity fkPregunta;

    public PreguntaEntity getFkPregunta() {
        return fkPregunta;
    }

    public void setFkPregunta(PreguntaEntity fkPregunta) {
        this.fkPregunta = fkPregunta;
    }

    @ManyToOne
    @JoinColumn(name = "fk_opcion")
    private OpcionEntity fkOpcion;

    public OpcionEntity getFkOpcion() {
        return fkOpcion;
    }

    public void setFkOpcion(OpcionEntity fkOpcion) {
        this.fkOpcion = fkOpcion;
    }

    public PosibleRespuestaEntity(long id) {
        super(id);
    }

    public PosibleRespuestaEntity() {
    }
}
