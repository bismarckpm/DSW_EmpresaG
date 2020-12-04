package com.empresag;

import java.util.List;

public class PreguntaCatSubcatDto extends BaseDto {
    private PreguntaDto fkPregunta;
    private CategoriaDto fkCategoria;
    private SubcategoriaDto fkSubcategoria;

    public PreguntaDto getFkPregunta() {
        return fkPregunta;
    }

    public void setFkPregunta(PreguntaDto fkPregunta) {
        this.fkPregunta = fkPregunta;
    }

    public CategoriaDto getFkCategoria() {
        return fkCategoria;
    }

    public void setFkCategoria(CategoriaDto fkCategoria) {
        this.fkCategoria = fkCategoria;
    }

    public SubcategoriaDto getFkSubcategoria() {
        return fkSubcategoria;
    }

    public void setFkSubcategoria(SubcategoriaDto fkSubcategoria) {
        this.fkSubcategoria = fkSubcategoria;
    }
}
