package com.empresag;

public class OpcionDto extends BaseDto {
    private String valor;
    private Integer rangoInicial;
    private Integer rangoFinal;
    private Long numeroDePersonas; // Number of people that answered selecting this question

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

    public Long getNumeroDePersonas() {
        return numeroDePersonas;
    }

    public void setNumeroDePersonas(Long numeroDePersonas) {
        this.numeroDePersonas = numeroDePersonas;
    }

    @Override
    public String toString() {
        return "OpcionDto{" +
                "valor='" + valor + '\'' +
                ", rangoInicial=" + rangoInicial +
                ", rangoFinal=" + rangoFinal +
                '}';
    }
}
