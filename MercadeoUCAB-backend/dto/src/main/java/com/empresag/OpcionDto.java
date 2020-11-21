package com.empresag;

public class OpcionDto extends BaseDto {
    private String valor;
    private Integer rangoInicial;
    private Integer rangoFinal;

    public OpcionDto() {
    }

    public OpcionDto(long id) throws IndexDatabaseException {
        super(id);
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Integer getRangoInicial() {
        return rangoInicial;
    }

    public void setRangoInicial(Integer rangoInicial) {
        this.rangoInicial = rangoInicial;
    }

    public Integer getRangoFinal() {
        return rangoFinal;
    }

    public void setRangoFinal(Integer rangoFinal) {
        this.rangoFinal = rangoFinal;
    }
}