package com.empresag;

public class MarcaTipoDto {
    private MarcaDto fkMarca;
    private TipoDto fkTipo;

    public MarcaDto getFkMarca() {
        return fkMarca;
    }

    public void setFkMarca(MarcaDto fkMarca) {
        this.fkMarca = fkMarca;
    }

    public TipoDto getFkTipo() {
        return fkTipo;
    }

    public void setFkTipo(TipoDto fkTipo) {
        this.fkTipo = fkTipo;
    }
}
