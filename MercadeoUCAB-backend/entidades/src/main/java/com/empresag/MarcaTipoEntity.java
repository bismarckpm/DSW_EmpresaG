package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "marca_tipo", schema = "empresag")
public class MarcaTipoEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "fk_marca")
    private MarcaEntity fkMarca;

    public MarcaEntity getFkMarca() {
        return fkMarca;
    }

    public void setFkMarca(MarcaEntity fkMarca) {
        this.fkMarca = fkMarca;
    }

    @ManyToOne
    @JoinColumn(name = "fk_tipo")
    private TipoEntity fkTipo;

    public TipoEntity getFkTipo() {
        return fkTipo;
    }

    public void setFkTipo(TipoEntity fkTipo) {
        this.fkTipo = fkTipo;
    }

    public MarcaTipoEntity(long id) {
        super(id);
    }

    public MarcaTipoEntity() {
    }
}
