package com.empresag;

public class SubcategoriaMarcaDto extends BaseDto {
    private SubcategoriaDto fkSubcategoria;
    private MarcaDto fkMarca;

    public SubcategoriaDto getFkSubcategoria() {
        return fkSubcategoria;
    }

    public void setFkSubcategoria(SubcategoriaDto fkSubcategoria) {
        this.fkSubcategoria = fkSubcategoria;
    }

    public MarcaDto getFkMarca() {
        return fkMarca;
    }

    public void setFkMarca(MarcaDto fkMarca) {
        this.fkMarca = fkMarca;
    }
}
