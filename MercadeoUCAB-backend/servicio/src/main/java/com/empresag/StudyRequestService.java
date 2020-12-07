package com.empresag;

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
}
