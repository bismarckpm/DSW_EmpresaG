package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categoria_subcategoria", schema = "empresag", catalog = "")
public class CategoriaSubcategoriaEntity extends BaseEntity {
    private CategoriaEntity fkCategoria;
    private SubcategoriaEntity fkSubcategoria;

    @ManyToOne
    @Column(name = "fk_categoria")
    public CategoriaEntity getFkCategoria() {
        return fkCategoria;
    }

    public void setFkCategoria(CategoriaEntity fkCategoria) {
        this.fkCategoria = fkCategoria;
    }

    @ManyToOne
    @JoinColumn(name = "fk_subcategoria")
    public SubcategoriaEntity getFkSubcategoria() {
        return fkSubcategoria;
    }

    public void setFkSubcategoria(SubcategoriaEntity fkSubcategoria) {
        this.fkSubcategoria = fkSubcategoria;
    }

}
