package com.empresag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/presentations")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class PresentationService {
    @GET
    @Path("/all")
    public List<TipoPresentacionEntity> allPresentations(){
        DaoTipoPresentacion daoTipoPresentacion = new DaoTipoPresentacion();
        return daoTipoPresentacion.findAll(TipoPresentacionEntity.class);
    }

    @POST
    @Path("/add")
    public TipoPresentacionEntity addPresentation(TipoPresentacionDto tipoPresentacionDto){
        DaoTipoPresentacion daoTipoPresentacion = new DaoTipoPresentacion();
        DaoPresentacion daoPresentacion = new DaoPresentacion();

        TipoEntity tipo = new TipoEntity(tipoPresentacionDto.getFkTipo().get_id());
        tipo.setNombre(tipoPresentacionDto.getFkTipo().getNombre());

        PresentacionEntity presentacion = new PresentacionEntity();
        presentacion.setNombre(tipoPresentacionDto.getFkPresentacion().getNombre());
        presentacion.setDescripcion(tipoPresentacionDto.getFkPresentacion().getDescripcion());
        daoPresentacion.insert(presentacion);

        TipoPresentacionEntity tp = new TipoPresentacionEntity();
        tp.setFkPresentacion(presentacion);
        tp.setFkTipo(tipo);
        daoTipoPresentacion.insert(tp);

        return tp;
    }


    @PUT
    @Path("/update/{id}")
    public Response updateType(@PathParam("id") long id, TipoPresentacionDto tipoPresentacionDto){
        DaoTipoPresentacion daoTipoPresentacion = new DaoTipoPresentacion();
        DaoPresentacion daoPresentacion = new DaoPresentacion();
        TipoPresentacionEntity tp = daoTipoPresentacion.find(id, TipoPresentacionEntity.class);

        if (tp != null){
            tp.setFkTipo(new TipoEntity(tipoPresentacionDto.getFkTipo().get_id(),
                    tipoPresentacionDto.getFkPresentacion().getNombre()));
            tp.setFkPresentacion(new PresentacionEntity(tipoPresentacionDto.getFkPresentacion().get_id()));

            PresentacionEntity presentacion = daoPresentacion.find(tipoPresentacionDto.getFkPresentacion().get_id(),
                    PresentacionEntity.class);

            if (presentacion != null){
                presentacion.setNombre(tipoPresentacionDto.getFkPresentacion().getNombre());
                presentacion.setDescripcion(tipoPresentacionDto.getFkPresentacion().getDescripcion());
                daoPresentacion.update(presentacion);
                daoTipoPresentacion.update(tp);
                return Response.ok().entity(tp).build();
            }
            else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @DELETE
    @Path("/delete/{id}")
    public Response deletePresentation(@PathParam("id") long id){
        DaoTipoPresentacion daoTipoPresentacion = new DaoTipoPresentacion();
        TipoPresentacionEntity tp = daoTipoPresentacion.find(id, TipoPresentacionEntity.class);

        if (tp != null){
            daoTipoPresentacion.delete(tp);
            return Response.ok().entity(tp).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
