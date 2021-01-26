package com.empresag;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_presentacion", schema = "empresag", catalog = "")
public class TipoPresentacionEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "fk_tipo")
    private TipoEntity fkTipo;

    public TipoEntity getFkTipo() {
        return fkTipo;
    }

    public void setFkTipo(TipoEntity fkTipo) {
        this.fkTipo = fkTipo;
    }

    @ManyToOne
    @JoinColumn(name = "fk_presentacion")
    private PresentacionEntity fkPresentacion;

    public PresentacionEntity getFkPresentacion() {
        return fkPresentacion;
    }

    public void setFkPresentacion(PresentacionEntity fkPresentacion) {
        this.fkPresentacion = fkPresentacion;
    }

    public TipoPresentacionEntity(long id) {
        super(id);
    }

    public TipoPresentacionEntity() {
    }
}
