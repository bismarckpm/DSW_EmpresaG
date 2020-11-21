package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "opcion", schema = "empresag", catalog = "")
public class OpcionEntity extends BaseEntity{
    private int id;
    private String valor;
    private Integer rangoInicial;
    private Integer rangoFinal;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpcionEntity that = (OpcionEntity) o;
        return id == that.id &&
                Objects.equals(valor, that.valor) &&
                Objects.equals(rangoInicial, that.rangoInicial) &&
                Objects.equals(rangoFinal, that.rangoFinal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, valor, rangoInicial, rangoFinal);
    }
}
