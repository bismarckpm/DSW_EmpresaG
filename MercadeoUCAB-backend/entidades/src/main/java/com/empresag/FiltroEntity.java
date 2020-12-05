package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "filtro", schema = "empresag", catalog = "")
public class FiltroEntity extends BaseEntity{
    private EstudioEntity fkEstudio;
    private Integer tipoFiltroLugar;
    private Integer edadMinima;
    private Integer edadMaxima;
    private NivelSocioeconomicoEntity fkNivelSocioeconomico;
    private EdoCivilEntity fkEdoCivil;
    private NivelAcademicoEntity fkNivelAcademico;
    private GeneroEntity fkGenero;
    private LugarEntity fkLugar;
    private CategoriaEntity fkCategoria;
    private SubcategoriaEntity fkSubcategoria;

    @ManyToOne
    @JoinColumn(name = "fk_estudio")
    public EstudioEntity getFkEstudio() {
        return fkEstudio;
    }

    public void setFkEstudio(EstudioEntity fkEstudio) {
        this.fkEstudio = fkEstudio;
    }

    @Basic
    @Column(name = "tipo_filtro_lugar")
    public Integer getTipoFiltroLugar() {
        return tipoFiltroLugar;
    }

    public void setTipoFiltroLugar(Integer tipoFiltroLugar) {
        this.tipoFiltroLugar = tipoFiltroLugar;
    }

    @Basic
    @Column(name = "edad_minima")
    public Integer getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(Integer edadMinima) {
        this.edadMinima = edadMinima;
    }

    @Basic
    @Column(name = "edad_maxima")
    public Integer getEdadMaxima() {
        return edadMaxima;
    }

    public void setEdadMaxima(Integer edadMaxima) {
        this.edadMaxima = edadMaxima;
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
    @JoinColumn(name = "fk_edo_civil")
    public EdoCivilEntity getFkEdoCivil() {
        return fkEdoCivil;
    }

    public void setFkEdoCivil(EdoCivilEntity fkEdoCivil) {
        this.fkEdoCivil = fkEdoCivil;
    }

    @ManyToOne
    @JoinColumn(name = "fk_nivel_academico")
    public NivelAcademicoEntity getFkNivelAcademico() {
        return fkNivelAcademico;
    }

    public void setFkNivelAcademico(NivelAcademicoEntity fkNivelAcademico) {
        this.fkNivelAcademico = fkNivelAcademico;
    }

    @ManyToOne
    @JoinColumn(name = "fk_genero")
    public GeneroEntity getFkGenero() {
        return fkGenero;
    }

    public void setFkGenero(GeneroEntity fkGenero) {
        this.fkGenero = fkGenero;
    }

    @ManyToOne
    @JoinColumn(name = "fk_lugar")
    public LugarEntity getFkLugar() {
        return fkLugar;
    }

    public void setFkLugar(LugarEntity fkLugar) {
        this.fkLugar = fkLugar;
    }

    @ManyToOne
    @JoinColumn(name = "fk_categoria")
    public CategoriaEntity getFkCategoria() {
        return fkCategoria;
    }

    public void setFkCategoria(CategoriaEntity fkCategoria) {
        this.fkCategoria = fkCategoria;
    }

    @ManyToOne
    @JoinColumn(name = "fk_subcategoria")
    public SubcategoriaEntity getFkSubcategoria() {
        return fkSubcategoria;
    }

    public void setFkSubcategoria(SubcategoriaEntity fkSubcategoria) {
        this.fkSubcategoria = fkSubcategoria;
    }

}
