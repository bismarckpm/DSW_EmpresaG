package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pregunta_cat_subcat", schema = "empresag")
public class PreguntaCatSubcatEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "fk_pregunta")
    private PreguntaEntity fkPregunta;

    public PreguntaEntity getFkPregunta() {
        return fkPregunta;
    }

    public void setFkPregunta(PreguntaEntity fkPregunta) {
        this.fkPregunta = fkPregunta;
    }

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

    public PreguntaCatSubcatEntity(long id) {
        super(id);
    }

    public PreguntaCatSubcatEntity() {
    }
}
