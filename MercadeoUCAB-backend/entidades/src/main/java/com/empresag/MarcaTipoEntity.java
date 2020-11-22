package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "marca_tipo", schema = "empresag", catalog = "")
public class MarcaTipoEntity extends BaseEntity{
    private int fkMarca;
    private int fkTipo;

    @Basic
    @Column(name = "fk_marca")
    public int getFkMarca() {
        return fkMarca;
    }

    public void setFkMarca(int fkMarca) {
        this.fkMarca = fkMarca;
    }

    @Basic
    @Column(name = "fk_tipo")
    public int getFkTipo() {
        return fkTipo;
    }

    public void setFkTipo(int fkTipo) {
        this.fkTipo = fkTipo;
    }

}
