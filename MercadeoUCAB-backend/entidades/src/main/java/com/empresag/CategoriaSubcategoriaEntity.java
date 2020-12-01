package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categoria_subcategoria", schema = "empresag")
public class CategoriaSubcategoriaEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "fk_categoria")
    private CategoriaEntity fkCategoria;

    public CategoriaEntity getFkCategoria() {
        return fkCategoria;
    }

    public void setFkCategoria(CategoriaEntity fkCategoria) {
        this.fkCategoria = fkCategoria;
    }

    @ManyToOne
    @JoinColumn(name = "fk_subcategoria")
    private SubcategoriaEntity fkSubcategoria;

    public SubcategoriaEntity getFkSubcategoria() {
        return fkSubcategoria;
    }

    public void setFkSubcategoria(SubcategoriaEntity fkSubcategoria) {
        this.fkSubcategoria = fkSubcategoria;
    }

    public CategoriaSubcategoriaEntity() {
    }

    public CategoriaSubcategoriaEntity(long id) {
        super(id);
    }
}
