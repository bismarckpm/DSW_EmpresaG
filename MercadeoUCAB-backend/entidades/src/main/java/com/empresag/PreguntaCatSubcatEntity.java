package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pregunta_cat_subcat", schema = "empresag", catalog = "")
public class PreguntaCatSubcatEntity extends BaseEntity{
    private int id;
    private PreguntaEntity fkPregunta;
    private CategoriaEntity fkCategoria;
    private SubcategoriaEntity fkSubcategoria;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PreguntaCatSubcatEntity that = (PreguntaCatSubcatEntity) o;
        return id == that.id &&
                fkPregunta == that.fkPregunta &&
                Objects.equals(fkCategoria, that.fkCategoria) &&
                Objects.equals(fkSubcategoria, that.fkSubcategoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fkPregunta, fkCategoria, fkSubcategoria);
    }
}
