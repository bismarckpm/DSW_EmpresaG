package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "lugar", schema = "empresag", catalog = "")
public class LugarEntity extends BaseEntity{
    @Basic
    @Column(name = "nombre")
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "tipo")
    private int tipo;

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @ManyToOne
    @JoinColumn(name = "fk_nivel_socioeconomico")
    private NivelSocioeconomicoEntity fkNivelSocioeconomico;

    public NivelSocioeconomicoEntity getFkNivelSocioeconomico() {
        return fkNivelSocioeconomico;
    }

    public void setFkNivelSocioeconomico(NivelSocioeconomicoEntity fkNivelSocioeconomico) {
        this.fkNivelSocioeconomico = fkNivelSocioeconomico;
    }

    @ManyToOne
    @JoinColumn(name = "fk_lugar")
    private LugarEntity fkLugar;

    public LugarEntity getFkLugar() {
        return fkLugar;
    }

    public void setFkLugar(LugarEntity fkLugar) {
        this.fkLugar = fkLugar;
    }

    @Override
    public String toString() {
        String FK_Lugar = "";
        String FK_NivelSocioEconomico = "";

        if (fkLugar != null)
            FK_Lugar = fkLugar.toString();
        if (fkNivelSocioeconomico != null)
            FK_NivelSocioEconomico = fkNivelSocioeconomico.toString();

        return "LugarEntity{" +
                "nombre='" + nombre + '\'' +
                ", tipo=" + tipo +
                ", fkNivelSocioeconomico=" + FK_NivelSocioEconomico +
                ", fkLugar=" + FK_Lugar +
                '}';
    }
}
