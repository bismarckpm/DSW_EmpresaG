package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "posible_respuesta", schema = "empresag", catalog = "")
public class PosibleRespuestaEntity extends BaseEntity{
    private int id;
    private PreguntaEntity fkPregunta;
    private OpcionEntity fkOpcion;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PosibleRespuestaEntity that = (PosibleRespuestaEntity) o;
        return id == that.id &&
                fkPregunta == that.fkPregunta &&
                fkOpcion == that.fkOpcion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fkPregunta, fkOpcion);
    }
}
