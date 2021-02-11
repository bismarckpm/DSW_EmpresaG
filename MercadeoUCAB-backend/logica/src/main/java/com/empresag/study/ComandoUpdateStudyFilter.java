package com.empresag.study;

import com.empresag.*;

import javax.ws.rs.core.Response;

public class ComandoUpdateStudyFilter extends ComandoBase {

    private long id;
    private FiltroDto filtroDto;
    private FiltroEntity res;

    public ComandoUpdateStudyFilter(long id, FiltroDto filtroDto) {
        this.id = id;
        this.filtroDto = filtroDto;
    }

    @Override
    public void execute() throws Exception {

//        DaoFiltro daoFiltro = new DaoFiltro();
//        DaoEstudio daoEstudio = new DaoEstudio();
//        DaoCategoria daoCategoria = new DaoCategoria();
//        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
//        DaoEdoCivil daoEdoCivil = new DaoEdoCivil();
//        DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
//        DaoNivelSocioeconomico daoNivelSocioeconomico = new DaoNivelSocioeconomico();
//        DaoGenero daoGenero = new DaoGenero();
//        DaoLugar daoLugar = new DaoLugar();

        DaoFiltro daoFiltro = FabricaDao.crearDaoFiltro();
        DaoEstudio daoEstudio = FabricaDao.crearDaoEstudio();
        DaoCategoria daoCategoria = FabricaDao.crearDaoCategoria();
        DaoSubcategoria daoSubcategoria = FabricaDao.crearDaoSubcategoria();
        DaoEdoCivil daoEdoCivil = FabricaDao.crearDaoEdoCivil();
        DaoNivelAcademico daoNivelAcademico = FabricaDao.crearDaoNivelAcademico();
        DaoNivelSocioeconomico daoNivelSocioeconomico = FabricaDao.crearDaoNivelSocioeconomico();
        DaoGenero daoGenero = FabricaDao.crearDaoGenero();
        DaoLugar daoLugar = FabricaDao.crearDaoLugar();

        FiltroEntity filtro = null;

        try {
            filtro = daoFiltro.find(id, FiltroEntity.class);
        }
        catch (NullPointerException e){
            e.printStackTrace();
//            return Response.status(Response.Status.NOT_FOUND).build();
        }

        CategoriaEntity categoria = daoCategoria.find(filtroDto.getFkCategoria().get_id(), CategoriaEntity.class);
        SubcategoriaEntity subcategoria =
                daoSubcategoria.find(filtroDto.getFkSubcategoria().get_id(), SubcategoriaEntity.class);
        EstudioEntity estudio = daoEstudio.find(filtroDto.getFkEstudio().get_id(), EstudioEntity.class);

        EdoCivilEntity estadoCivil = null;
        GeneroEntity genero = null;
        NivelAcademicoEntity nivelAcademico = null;
        NivelSocioeconomicoEntity nivelSocioeconomico = null;
        LugarEntity lugar = null;

        /* Optional filters */
        if (filtroDto.getFkEdoCivil() != null){
            estadoCivil = daoEdoCivil.find(filtroDto.getFkEdoCivil().get_id(), EdoCivilEntity.class);
        }
        if (filtroDto.getFkGenero() != null){
            genero = daoGenero.find(filtroDto.getFkGenero().get_id(), GeneroEntity.class);
        }
        if (filtroDto.getFkNivelAcademico() != null){
            nivelAcademico = daoNivelAcademico.find
                    (filtroDto.getFkNivelAcademico().get_id(), NivelAcademicoEntity.class);
        }
        if (filtroDto.getFkNivelSocioeconomico() != null){
            nivelSocioeconomico = daoNivelSocioeconomico.find(filtroDto.getFkNivelSocioeconomico().get_id(),
                    NivelSocioeconomicoEntity.class);
        }
        if (filtroDto.getFkLugar() != null){
            lugar = daoLugar.find(filtroDto.getFkLugar().get_id(), LugarEntity.class);
        }
        if (filtroDto.getEdadMaxima() != null && filtroDto.getEdadMinima() != null){
            filtro.setEdadMinima(filtroDto.getEdadMinima());
            filtro.setEdadMaxima(filtroDto.getEdadMaxima());
        }
        if (filtroDto.getTipoFiltroLugar() != null){
            filtro.setTipoFiltroLugar(filtroDto.getTipoFiltroLugar());
        }

        filtro.setFkEstudio(estudio);
        filtro.setFkCategoria(categoria);
        filtro.setFkSubcategoria(subcategoria);
        filtro.setFkEdoCivil(estadoCivil);
        filtro.setFkGenero(genero);
        filtro.setFkNivelAcademico(nivelAcademico);
        filtro.setFkNivelSocioeconomico(nivelSocioeconomico);
        filtro.setFkLugar(lugar);
        daoFiltro.update(filtro);

//        return Response.ok().entity(filtro).build();
        res = filtro;
    }

    @Override
    public FiltroEntity getResult() {
        return res;
    }
}
