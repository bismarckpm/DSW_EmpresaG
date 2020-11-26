package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pregunta_cat_subcat", schema = "empresag", catalog = "")
public class PreguntaCatSubcatEntity extends BaseEntity{
    private PreguntaEntity fkPregunta;
    private CategoriaEntity fkCategoria;
    private SubcategoriaEntity fkSubcategoria;

    @ManyToOne
    @JoinColumn(name = "fk_pregunta")
    public PreguntaEntity getFkPregunta() {
        return fkPregunta;
    }

    public void setFkPregunta(PreguntaEntity fkPregunta) {
        this.fkPregunta = fkPregunta;
    }

    @ManyToOne
    @JoinColumn(name = "fk_categoria")
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
