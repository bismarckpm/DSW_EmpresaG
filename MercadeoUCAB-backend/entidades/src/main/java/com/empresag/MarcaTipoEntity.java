package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "marca_tipo", schema = "empresag", catalog = "")
public class MarcaTipoEntity extends BaseEntity{
    private int id;
    private int fkMarca;
    private int fkTipo;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarcaTipoEntity that = (MarcaTipoEntity) o;
        return id == that.id &&
                fkMarca == that.fkMarca &&
                fkTipo == that.fkTipo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fkMarca, fkTipo);
    }
}
