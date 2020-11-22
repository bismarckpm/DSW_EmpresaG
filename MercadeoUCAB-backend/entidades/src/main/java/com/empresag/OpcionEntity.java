package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "opcion", schema = "empresag", catalog = "")
public class OpcionEntity extends BaseEntity{
    private String valor;
    private Integer rangoInicial;
    private Integer rangoFinal;

    @Basic
    @Column(name = "valor")
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Basic
    @Column(name = "rango_inicial")
    public Integer getRangoInicial() {
        return rangoInicial;
    }

    public void setRangoInicial(Integer rangoInicial) {
        this.rangoInicial = rangoInicial;
    }

    @Basic
    @Column(name = "rango_final")
    public Integer getRangoFinal() {
        return rangoFinal;
    }

    public void setRangoFinal(Integer rangoFinal) {
        this.rangoFinal = rangoFinal;
    }

}
