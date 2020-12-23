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
    public List<FiltroEntity> getAllRequests(){
        DaoFiltro daoFiltro = new DaoFiltro();
        return daoFiltro.getAllRequests();
    }

    @GET
    @Path("/find-by-user/{userId}")
    public Response getUserRequests(@PathParam("userId") long id){
        DaoFiltro daoFiltro = new DaoFiltro();
        List<FiltroEntity> solicitudes = null;
        try {
            solicitudes = daoFiltro.getUserRequests(id);
            return Response.ok().entity(solicitudes).build();
        }
        catch (NullPointerException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/find-specific-by-user/{userId}/{requestId}")
    public Response getUserRequest(@PathParam("userId") long id, @PathParam("requestId") long requestId){
        DaoFiltro daoFiltro = new DaoFiltro();
        FiltroEntity solicitud = null;
        try {
            solicitud = daoFiltro.getUserRequest(id, requestId);
            return Response.ok().entity(solicitud).build();
        }
        catch (NullPointerException | NoResultException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/find/{id}")
    public Response getRequest(@PathParam("id") long id){
        DaoFiltro daoFiltro = new DaoFiltro();
        FiltroEntity solicitud = null;

        try{
            solicitud = daoFiltro.getCurrentRequest(id);
            return Response.ok().entity(solicitud).build();
        }
        catch (NullPointerException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/add")
    public Response addRequest(FiltroDto filtroDto){
        DaoFiltro daoFiltro = new DaoFiltro();
        DaoSolicitud daoSolicitud = new DaoSolicitud();
        DaoUsuario daoUsuario = new DaoUsuario();
        DaoCategoria daoCategoria = new DaoCategoria();
        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
        DaoEdoCivil daoEdoCivil = new DaoEdoCivil();
        DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
        DaoNivelSocioeconomico daoNivelSocioeconomico = new DaoNivelSocioeconomico();
        DaoGenero daoGenero = new DaoGenero();
        DaoLugar daoLugar = new DaoLugar();

        FiltroEntity filtro = new FiltroEntity();
        UsuarioEntity usuario = new UsuarioEntity();
        SolicitudEntity solicitud = new SolicitudEntity();

        try {
            usuario = daoUsuario.find(filtroDto.getFkSolicitud().getFkUsuario().get_id(), UsuarioEntity.class);
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
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

        return Response.ok().entity(filtro).build();
    }

    @PUT
    @Path("/update/{id}")
    public Response updateRequest(@PathParam("id") long id, FiltroDto filtroDto){
        DaoFiltro daoFiltro = new DaoFiltro();
        DaoSolicitud daoSolicitud = new DaoSolicitud();
        DaoUsuario daoUsuario = new DaoUsuario();
        DaoCategoria daoCategoria = new DaoCategoria();
        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
        DaoEdoCivil daoEdoCivil = new DaoEdoCivil();
        DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
        DaoNivelSocioeconomico daoNivelSocioeconomico = new DaoNivelSocioeconomico();
        DaoGenero daoGenero = new DaoGenero();
        DaoLugar daoLugar = new DaoLugar();

        FiltroEntity filtro = null;
        UsuarioEntity usuario = null;
        SolicitudEntity solicitud = null;

        try {
            solicitud = daoSolicitud.find(filtroDto.getFkSolicitud().get_id(), SolicitudEntity.class);
            filtro = daoFiltro.getCurrentRequest(solicitud.get_id());
            usuario = daoUsuario.find(filtroDto.getFkSolicitud().getFkUsuario().get_id(), UsuarioEntity.class);
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
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

        return Response.ok().entity(filtro).build();
    }

    @PUT
    @Path("/update-status/{id}")
    public Response updateStatus(@PathParam("id") long id){
        DaoSolicitud daoSolicitud = new DaoSolicitud();
        DaoFiltro daoFiltro = new DaoFiltro();
        DaoEstudio daoEstudio = new DaoEstudio();
        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();

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

            return Response.ok().entity(solicitudWithFilter).build();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteRequest(@PathParam("id") long id){
        DaoSolicitud daoSolicitud = new DaoSolicitud();
        SolicitudEntity solicitud = null;

        try {
            solicitud = daoSolicitud.find(id, SolicitudEntity.class);
            daoSolicitud.delete(solicitud);
            return Response.ok().entity(solicitud).build();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
