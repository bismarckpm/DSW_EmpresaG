package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "posible_respuesta", schema = "empresag", catalog = "")
public class PosibleRespuestaEntity extends BaseEntity{
    private PreguntaEntity fkPregunta;
    private OpcionEntity fkOpcion;

    @ManyToOne
    @JoinColumn(name = "fk_pregunta")
    public PreguntaEntity getFkPregunta() {
        return fkPregunta;
    }

    public void setFkPregunta(PreguntaEntity fkPregunta) {
        this.fkPregunta = fkPregunta;
    }

    @ManyToOne
    @JoinColumn(name = "fk_opcion")
    public OpcionEntity getFkOpcion() {
        return fkOpcion;
    }

    public void setFkOpcion(OpcionEntity fkOpcion) {
        this.fkOpcion = fkOpcion;
    }

}
