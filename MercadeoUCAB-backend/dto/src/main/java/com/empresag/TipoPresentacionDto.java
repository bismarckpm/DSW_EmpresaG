package com.empresag;

public class TipoPresentacionDto extends BaseDto {
    private TipoDto fkTipo;
    private PresentacionDto fkPresentacion;

    public TipoDto getFkTipo() {
        return fkTipo;
    }

    public void setFkTipo(TipoDto fkTipo) {
        this.fkTipo = fkTipo;
    }

    public PresentacionDto getFkPresentacion() {
        return fkPresentacion;
    }

    public void setFkPresentacion(PresentacionDto fkPresentacion) {
        this.fkPresentacion = fkPresentacion;
    }
}
