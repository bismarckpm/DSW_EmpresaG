package com.empresag;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lugar", schema = "empresag", catalog = "")
public class LugarEntity extends BaseEntity{
    @Basic
    @Column(name = "nombre")
    private String nombre;

    @Basic
    @Column(name = "tipo")
    private int tipo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_nivel_socioeconomico")
    private NivelSocioeconomicoEntity fkNivelSocioeconomico;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_lugar")
    private LugarEntity fkLugar;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fkLugar")
    private List<PersonaEntity> personas;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fkLugar")
    private List<LugarEntity> superior;

    public LugarEntity(long id) {
        super(id);
    }

    public LugarEntity() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }


    public NivelSocioeconomicoEntity getFkNivelSocioeconomico() {
        return fkNivelSocioeconomico;
    }

    public void setFkNivelSocioeconomico(NivelSocioeconomicoEntity fkNivelSocioeconomico) {
        this.fkNivelSocioeconomico = fkNivelSocioeconomico;
    }

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
