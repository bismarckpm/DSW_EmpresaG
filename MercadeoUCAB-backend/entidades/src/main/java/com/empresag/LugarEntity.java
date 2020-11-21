package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "lugar", schema = "empresag", catalog = "")
public class LugarEntity extends BaseEntity{
    private int id;
    private String nombre;
    private int tipo;
    private NivelSocioeconomicoEntity fkNivelSocioeconomico;
    private LugarEntity fkLugar;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LugarEntity that = (LugarEntity) o;
        return id == that.id &&
                tipo == that.tipo &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(fkNivelSocioeconomico, that.fkNivelSocioeconomico) &&
                Objects.equals(fkLugar, that.fkLugar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, tipo, fkNivelSocioeconomico, fkLugar);
    }
}
