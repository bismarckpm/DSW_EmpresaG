package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tipo_presentacion", schema = "empresag", catalog = "")
public class TipoPresentacionEntity extends BaseEntity{
    private TipoEntity fkTipo;
    private PresentacionEntity fkPresentacion;

    @ManyToOne
    @JoinColumn(name = "fk_tipo")
    public TipoEntity getFkTipo() {
        return fkTipo;
    }

    public void setFkTipo(TipoEntity fkTipo) {
        this.fkTipo = fkTipo;
    }

    @ManyToOne
    @JoinColumn(name = "fk_presentacion")
    public PresentacionEntity getFkPresentacion() {
        return fkPresentacion;
    }

    public void setFkPresentacion(PresentacionEntity fkPresentacion) {
        this.fkPresentacion = fkPresentacion;
    }

}
