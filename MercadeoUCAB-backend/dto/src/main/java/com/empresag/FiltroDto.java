package com.empresag;

public class FiltroDto extends BaseDto {
    private EstudioDto fkEstudio;
    private Integer tipoFiltroLugar;
    private Integer edadMinima;
    private Integer edadMaxima;
    private String nombre;
    private NivelSocioeconomicoDto fkNivelSocioeconomico;
    private EdoCivilDto fkEdoCivil;
    private NivelAcademicoDto fkNivelAcademico;
    private GeneroDto fkGenero;
    private LugarDto fkLugar;
    private CategoriaDto fkCategoria;
    private SubcategoriaDto fkSubcategoria;
    private SolicitudDto fkSolicitud;

    public EstudioDto getFkEstudio() {
        return fkEstudio;
    }

    public void setFkEstudio(EstudioDto fkEstudio) {
        this.fkEstudio = fkEstudio;
    }

    public Integer getTipoFiltroLugar() {
        return tipoFiltroLugar;
    }

    public void setTipoFiltroLugar(Integer tipoFiltroLugar) {
        this.tipoFiltroLugar = tipoFiltroLugar;
    }

    public Integer getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(Integer edadMinima) {
        this.edadMinima = edadMinima;
    }

    public Integer getEdadMaxima() {
        return edadMaxima;
    }

    public void setEdadMaxima(Integer edadMaxima) {
        this.edadMaxima = edadMaxima;
    }

    public NivelSocioeconomicoDto getFkNivelSocioeconomico() {
        return fkNivelSocioeconomico;
    }

    public void setFkNivelSocioeconomico(NivelSocioeconomicoDto fkNivelSocioeconomico) {
        this.fkNivelSocioeconomico = fkNivelSocioeconomico;
    }

    public EdoCivilDto getFkEdoCivil() {
        return fkEdoCivil;
    }

    public void setFkEdoCivil(EdoCivilDto fkEdoCivil) {
        this.fkEdoCivil = fkEdoCivil;
    }

    public NivelAcademicoDto getFkNivelAcademico() {
        return fkNivelAcademico;
    }

    public void setFkNivelAcademico(NivelAcademicoDto fkNivelAcademico) {
        this.fkNivelAcademico = fkNivelAcademico;
    }

    public GeneroDto getFkGenero() {
        return fkGenero;
    }

    public void setFkGenero(GeneroDto fkGenero) {
        this.fkGenero = fkGenero;
    }

    public LugarDto getFkLugar() {
        return fkLugar;
    }

    public void setFkLugar(LugarDto fkLugar) {
        this.fkLugar = fkLugar;
    }

    public CategoriaDto getFkCategoria() {
        return fkCategoria;
    }

    public void setFkCategoria(CategoriaDto fkCategoria) {
        this.fkCategoria = fkCategoria;
    }

    public SubcategoriaDto getFkSubcategoria() {
        return fkSubcategoria;
    }

    public void setFkSubcategoria(SubcategoriaDto fkSubcategoria) {
        this.fkSubcategoria = fkSubcategoria;
    }

    public SolicitudDto getFkSolicitud() {
        return fkSolicitud;
    }

    public void setFkSolicitud(SolicitudDto fkSolicitud) {
        this.fkSolicitud = fkSolicitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
