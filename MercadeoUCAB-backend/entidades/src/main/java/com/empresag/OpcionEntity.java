package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "opcion", schema = "empresag", catalog = "")
public class OpcionEntity extends BaseEntity{
    @Basic
    @Column(name = "valor")
    private String valor;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Basic
    @Column(name = "rango_inicial")
    private Integer rangoInicial;

    public Integer getRangoInicial() {
        return rangoInicial;
    }

    public void setRangoInicial(Integer rangoInicial) {
        this.rangoInicial = rangoInicial;
    }

    @Basic
    @Column(name = "rango_final")
    private Integer rangoFinal;

    public Integer getRangoFinal() {
        return rangoFinal;
    }

    public void setRangoFinal(Integer rangoFinal) {
        this.rangoFinal = rangoFinal;
    }

    public OpcionEntity(long id) {
        super(id);
    }

    public OpcionEntity(long id, String valor) {
        super(id);
        this.valor = valor;
    }

    public OpcionEntity(long id, Integer rangoInicial, Integer rangoFinal) {
        super(id);
        this.rangoInicial = rangoInicial;
        this.rangoFinal = rangoFinal;
    }

    public OpcionEntity(String valor) {
        this.valor = valor;
    }

    public OpcionEntity(Integer rangoInicial, Integer rangoFinal) {
        this.rangoInicial = rangoInicial;
        this.rangoFinal = rangoFinal;
    }

    public OpcionEntity() {
    }

    @Override
    public String toString() {
        return "OpcionEntity{" +
                "valor='" + valor + '\'' +
                ", rangoInicial=" + rangoInicial +
                ", rangoFinal=" + rangoFinal +
                '}';
    }
}
