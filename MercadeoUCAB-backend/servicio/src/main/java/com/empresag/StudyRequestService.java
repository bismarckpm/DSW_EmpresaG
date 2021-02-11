package com.empresag;

import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.List;

@Path("/requests")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class StudyRequestService {
    @GET
    @Path("/all")
    public RespuestaDto<List<FiltroEntity>> getAllRequests(){
        RespuestaDto<List<FiltroEntity>> respuesta = new RespuestaDto<>();

        try{
            DaoFiltro daoFiltro = FabricaDao.crearDaoFiltro();
            respuesta.setCodigo(0);
            respuesta.setEstado("OK");
            respuesta.setObjeto(daoFiltro.getAllRequests());
        }
        catch (NullPointerException e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje("No existen filtros creados");
        }
        catch (Exception e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje(e.getMessage());
        }

        return respuesta;
    }

    @GET
    @Path("/find-by-user/{userId}")
    public RespuestaDto<List<FiltroEntity>> getUserRequests(@PathParam("userId") long id){
        RespuestaDto<List<FiltroEntity>> respuesta = new RespuestaDto<>();
        try {
            DaoFiltro daoFiltro = FabricaDao.crearDaoFiltro();
            respuesta.setCodigo(0);
            respuesta.setEstado("OK");
            respuesta.setObjeto(daoFiltro.getUserRequests(id));
        }
        catch (NullPointerException e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje("No se encontro filtro disponible");
        }
        catch (Exception e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje(e.getMessage());

        }

        return respuesta;
    }

    @GET
    @Path("/find-specific-by-user/{userId}/{requestId}")
    public RespuestaDto<FiltroEntity> getUserRequest(@PathParam("userId") long id, @PathParam("requestId") long requestId){
        RespuestaDto<FiltroEntity> respuesta = new RespuestaDto<>();
        try {
            DaoFiltro daoFiltro = FabricaDao.crearDaoFiltro();
            respuesta.setCodigo(0);
            respuesta.setEstado("OK");
            respuesta.setObjeto(daoFiltro.getUserRequest(id, requestId));
        }
        catch (NullPointerException e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje("No se encontro filtro disponible");
        }
        catch (Exception e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje(e.getMessage());

        }

        return respuesta;
    }

    @GET
    @Path("/find/{id}")
    public RespuestaDto<FiltroEntity> getRequest(@PathParam("id") long id){
        RespuestaDto<FiltroEntity> respuesta = new RespuestaDto<>();
        try{
            DaoFiltro daoFiltro = FabricaDao.crearDaoFiltro();
            respuesta.setCodigo(0);
            respuesta.setEstado("OK");
            respuesta.setObjeto(daoFiltro.getCurrentRequest(id));
        }
        catch (NullPointerException e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje("No se encontro filtro disponible");
        }
        catch (Exception e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje(e.getMessage());

        }

        return respuesta;
    }

    @POST
    @Path("/add")
    public RespuestaDto<Boolean> addRequest(FiltroDto filtroDto){
        RespuestaDto<Boolean> respuesta = new RespuestaDto<>();

        DaoFiltro daoFiltro = FabricaDao.crearDaoFiltro();
        DaoSolicitud daoSolicitud = FabricaDao.crearDaoSolicitud();
        DaoUsuario daoUsuario = FabricaDao.crearDaoUsuario();
        DaoCategoria daoCategoria = FabricaDao.crearDaoCategoria();
        DaoSubcategoria daoSubcategoria = FabricaDao.crearDaoSubcategoria();
        DaoEdoCivil daoEdoCivil = FabricaDao.crearDaoEdoCivil();
        DaoNivelAcademico daoNivelAcademico = FabricaDao.crearDaoNivelAcademico();
        DaoNivelSocioeconomico daoNivelSocioeconomico = FabricaDao.crearDaoNivelSocioeconomico();
        DaoGenero daoGenero = FabricaDao.crearDaoGenero();
        DaoLugar daoLugar = FabricaDao.crearDaoLugar();

        FiltroEntity filtro = FabricaEntity.crearFiltroEntity();
        UsuarioEntity usuario = FabricaEntity.crearUsuarioEntity();
        SolicitudEntity solicitud = FabricaEntity.crearSolicitudEntity();

        try {
            usuario = daoUsuario.find(filtroDto.getFkSolicitud().getFkUsuario().get_id(), UsuarioEntity.class);
        }
        catch (NullPointerException e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje("No se encontraron datos para la informacion suministrada");

            return respuesta;
        }
        catch (Exception e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje(e.getMessage());

            return respuesta;

        }

        CategoriaEntity categoria = daoCategoria.find(filtroDto.getFkCategoria().get_id(), CategoriaEntity.class);
        SubcategoriaEntity subcategoria =
                daoSubcategoria.find(filtroDto.getFkSubcategoria().get_id(), SubcategoriaEntity.class);

        EdoCivilEntity estadoCivil = null;
        GeneroEntity genero = null;
        NivelAcademicoEntity nivelAcademico = null;
        NivelSocioeconomicoEntity nivelSocioeconomico = null;
        LugarEntity lugar = null;

        solicitud.setEstado(0);
        solicitud.setFkUsuario(usuario);
        solicitud.setNombre(filtroDto.getNombre());
        daoSolicitud.insert(solicitud);

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

        filtro.setFkSolicitud(solicitud);
        filtro.setFkCategoria(categoria);
        filtro.setFkSubcategoria(subcategoria);
        filtro.setFkEdoCivil(estadoCivil);
        filtro.setFkGenero(genero);
        filtro.setFkNivelAcademico(nivelAcademico);
        filtro.setFkNivelSocioeconomico(nivelSocioeconomico);
        filtro.setFkLugar(lugar);
        daoFiltro.update(filtro);


        respuesta.setCodigo(0);
        respuesta.setEstado("OK");
        respuesta.setMensaje("Filtro creado exitosamente.");
        respuesta.setObjeto(true);

        return respuesta;
    }

    @PUT
    @Path("/update/{id}")
    public RespuestaDto<FiltroEntity> updateRequest(@PathParam("id") long id, FiltroDto filtroDto){
        RespuestaDto<FiltroEntity> respuesta = new RespuestaDto<>();

        DaoFiltro daoFiltro = FabricaDao.crearDaoFiltro();
        DaoSolicitud daoSolicitud = FabricaDao.crearDaoSolicitud();
        DaoUsuario daoUsuario = FabricaDao.crearDaoUsuario();
        DaoCategoria daoCategoria = FabricaDao.crearDaoCategoria();
        DaoSubcategoria daoSubcategoria = FabricaDao.crearDaoSubcategoria();
        DaoEdoCivil daoEdoCivil = FabricaDao.crearDaoEdoCivil();
        DaoNivelAcademico daoNivelAcademico = FabricaDao.crearDaoNivelAcademico();
        DaoNivelSocioeconomico daoNivelSocioeconomico = FabricaDao.crearDaoNivelSocioeconomico();
        DaoGenero daoGenero = FabricaDao.crearDaoGenero();
        DaoLugar daoLugar = FabricaDao.crearDaoLugar();

        FiltroEntity filtro = FabricaEntity.crearFiltroEntity();
        UsuarioEntity usuario = FabricaEntity.crearUsuarioEntity();
        SolicitudEntity solicitud = FabricaEntity.crearSolicitudEntity();

        try {
            solicitud = daoSolicitud.find(filtroDto.getFkSolicitud().get_id(), SolicitudEntity.class);
            filtro = daoFiltro.getCurrentRequest(solicitud.get_id());
            usuario = daoUsuario.find(filtroDto.getFkSolicitud().getFkUsuario().get_id(), UsuarioEntity.class);
        }
        catch (NullPointerException e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje("No se encontraron datos para la informacion suministrada");

            return respuesta;
        }
        catch (Exception e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje(e.getMessage());

            return respuesta;

        }

        CategoriaEntity categoria = daoCategoria.find(filtroDto.getFkCategoria().get_id(), CategoriaEntity.class);
        SubcategoriaEntity subcategoria =
                daoSubcategoria.find(filtroDto.getFkSubcategoria().get_id(), SubcategoriaEntity.class);

        EdoCivilEntity estadoCivil = null;
        GeneroEntity genero = null;
        NivelAcademicoEntity nivelAcademico = null;
        NivelSocioeconomicoEntity nivelSocioeconomico = null;
        LugarEntity lugar = null;

        solicitud.setEstado(0);
        solicitud.setFkUsuario(usuario);
        daoSolicitud.update(solicitud);

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

        filtro.setFkSolicitud(solicitud);
        filtro.setFkCategoria(categoria);
        filtro.setFkSubcategoria(subcategoria);
        filtro.setFkEdoCivil(estadoCivil);
        filtro.setFkGenero(genero);
        filtro.setFkNivelAcademico(nivelAcademico);
        filtro.setFkNivelSocioeconomico(nivelSocioeconomico);
        filtro.setFkLugar(lugar);
        daoFiltro.update(filtro);


        respuesta.setCodigo(0);
        respuesta.setEstado("OK");
        respuesta.setMensaje("Filtro actualizado exitosamente.");
        respuesta.setObjeto(filtro);

        return respuesta;
    }

    @PUT
    @Path("/update-status/{id}")
    public RespuestaDto<FiltroEntity> updateStatus(@PathParam("id") long id){
        RespuestaDto<FiltroEntity> respuesta = new RespuestaDto<>();

        DaoSolicitud daoSolicitud = FabricaDao.crearDaoSolicitud();
        DaoFiltro daoFiltro = FabricaDao.crearDaoFiltro();
        DaoEstudio daoEstudio = FabricaDao.crearDaoEstudio();
        DaoSolicitudEstudio daoSolicitudEstudio = FabricaDao.crearDaoSolicitudEstudio();

        FiltroEntity solicitudWithFilter = null;
        SolicitudEntity solicitud = null;
        SolicitudEstudioEntity solicitudEstudio = null;
        EstudioEntity estudio = null;

        try {
            solicitud = daoSolicitud.find(id, SolicitudEntity.class);
            solicitud.setEstado(1);
            daoSolicitud.update(solicitud);

            estudio = new EstudioEntity();
            estudio.setFechaRealizacion(new Date(System.currentTimeMillis()));
            estudio.setEstado(1);
            estudio.setNombre(solicitud.getNombre());
            daoEstudio.insert(estudio);

            solicitudWithFilter = daoFiltro.getCurrentRequest(id);
            solicitudWithFilter.setFkEstudio(estudio);
            daoFiltro.update(solicitudWithFilter);

            solicitudEstudio = new SolicitudEstudioEntity();
            solicitudEstudio.setFkEstudio(estudio);
            solicitudEstudio.setFkSolicitud(solicitud);
            daoSolicitudEstudio.insert(solicitudEstudio);

            respuesta.setCodigo(0);
            respuesta.setEstado("OK");
            respuesta.setMensaje("Filtro actualizado exitosamente.");
            respuesta.setObjeto(solicitudWithFilter);
        }
        catch (NullPointerException e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje("No se encontraron datos para la informacion suministrada");

        }
        catch (Exception e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje(e.getMessage());

        }

        return respuesta;
    }

    @DELETE
    @Path("/delete/{id}")
    public RespuestaDto<Boolean> deleteRequest(@PathParam("id") long id){
        RespuestaDto<Boolean> respuesta = new RespuestaDto<>();

        try {
            DaoSolicitud daoSolicitud = FabricaDao.crearDaoSolicitud();
            SolicitudEntity solicitud = daoSolicitud.find(id, SolicitudEntity.class);
            daoSolicitud.delete(solicitud);
            respuesta.setCodigo(0);
            respuesta.setEstado("OK");
            respuesta.setObjeto(true);
        }
        catch (NullPointerException e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje("No se encontro filtro disponible");
            respuesta.setObjeto(false);
        }
        catch (Exception e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje(e.getMessage());
            respuesta.setObjeto(false);

        }

        return respuesta;
    }
}
