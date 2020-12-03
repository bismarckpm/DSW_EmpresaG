package com.empresag;

import java.util.List;

public class PreguntaCatSubcatDto extends BaseDto {
    private PreguntaDto fkPregunta;
    private CategoriaDto fkCategoria;
    private SubcategoriaDto fkSubcategoria;
    private List<OpcionDto> listOpciones;

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

    public List<OpcionDto> getListOpciones() {
        return listOpciones;
    }

    public void setListOpciones(List<OpcionDto> listOpciones) {
        this.listOpciones = listOpciones;
    }
}
