package com.empresag;

public class CategoriaSubcategoriaDto extends BaseDto {

    private CategoriaDto fkCategoria;
    private SubcategoriaDto fkSubcategoria;

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
