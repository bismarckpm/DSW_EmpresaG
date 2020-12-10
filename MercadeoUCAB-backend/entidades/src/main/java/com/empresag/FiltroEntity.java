package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "filtro", schema = "empresag")
public class FiltroEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "fk_estudio")
    private EstudioEntity fkEstudio;

    public EstudioEntity getFkEstudio() {
        return fkEstudio;
    }

    public void setFkEstudio(EstudioEntity fkEstudio) {
        this.fkEstudio = fkEstudio;
    }

    @Basic
    @Column(name = "tipo_filtro_lugar")
    private Integer tipoFiltroLugar;

    public Integer getTipoFiltroLugar() {
        return tipoFiltroLugar;
    }

    public void setTipoFiltroLugar(Integer tipoFiltroLugar) {
        this.tipoFiltroLugar = tipoFiltroLugar;
    }

    @Basic
    @Column(name = "edad_minima")
    private Integer edadMinima;

    public Integer getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(Integer edadMinima) {
        this.edadMinima = edadMinima;
    }

    @Basic
    @Column(name = "edad_maxima")
    private Integer edadMaxima;

    public Integer getEdadMaxima() {
        return edadMaxima;
    }

    public void setEdadMaxima(Integer edadMaxima) {
        this.edadMaxima = edadMaxima;
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
    @JoinColumn(name = "fk_edo_civil")
    private EdoCivilEntity fkEdoCivil;

    public EdoCivilEntity getFkEdoCivil() {
        return fkEdoCivil;
    }

    public void setFkEdoCivil(EdoCivilEntity fkEdoCivil) {
        this.fkEdoCivil = fkEdoCivil;
    }

    @ManyToOne
    @JoinColumn(name = "fk_nivel_academico")
    private NivelAcademicoEntity fkNivelAcademico;

    public NivelAcademicoEntity getFkNivelAcademico() {
        return fkNivelAcademico;
    }

    public void setFkNivelAcademico(NivelAcademicoEntity fkNivelAcademico) {
        this.fkNivelAcademico = fkNivelAcademico;
    }

    @ManyToOne
    @JoinColumn(name = "fk_genero")
    private GeneroEntity fkGenero;

    public GeneroEntity getFkGenero() {
        return fkGenero;
    }

    public void setFkGenero(GeneroEntity fkGenero) {
        this.fkGenero = fkGenero;
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

    @ManyToOne
    @JoinColumn(name = "fk_categoria")
    private CategoriaEntity fkCategoria;

    public CategoriaEntity getFkCategoria() {
        return fkCategoria;
    }

    public void setFkCategoria(CategoriaEntity fkCategoria) {
        this.fkCategoria = fkCategoria;
    }

    @ManyToOne
    @JoinColumn(name = "fk_subcategoria")
    private SubcategoriaEntity fkSubcategoria;

    public SubcategoriaEntity getFkSubcategoria() {
        return fkSubcategoria;
    }

    public void setFkSubcategoria(SubcategoriaEntity fkSubcategoria) {
        this.fkSubcategoria = fkSubcategoria;
    }

    @ManyToOne
    @JoinColumn(name = "fk_solicitud")
    private SolicitudEntity fkSolicitud;

    public SolicitudEntity getFkSolicitud() {
        return fkSolicitud;
    }

    public void setFkSolicitud(SolicitudEntity fkSolicitud) {
        this.fkSolicitud = fkSolicitud;
    }

    @Override
    public String toString() {
        return "FiltroEntity{" +
                "fkEstudio=" + fkEstudio +
                ", tipoFiltroLugar=" + tipoFiltroLugar +
                ", edadMinima=" + edadMinima +
                ", edadMaxima=" + edadMaxima +
                '}';
    }
}
