package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "lugar", schema = "empresag", catalog = "")
public class LugarEntity extends BaseEntity{
    private String nombre;
    @Basic
    @Column(name = "tipo")
    private int tipo;
    @ManyToOne
    @JoinColumn(name = "fk_nivel_socioeconomico")
    private NivelSocioeconomicoEntity fkNivelSocioeconomico;
    @ManyToOne
    @JoinColumn(name = "fk_lugar")
    private LugarEntity fkLugar;

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "tipo")
    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @ManyToOne
    @JoinColumn(name = "fk_nivel_socioeconomico")
    public NivelSocioeconomicoEntity getFkNivelSocioeconomico() {
        return fkNivelSocioeconomico;
    }

    public void setFkNivelSocioeconomico(NivelSocioeconomicoEntity fkNivelSocioeconomico) {
        this.fkNivelSocioeconomico = fkNivelSocioeconomico;
    }

    @ManyToOne
    @JoinColumn(name = "fk_lugar")
    public LugarEntity getFkLugar() {
        return fkLugar;
    }

    public void setFkLugar(LugarEntity fkLugar) {
        this.fkLugar = fkLugar;
    }

}
