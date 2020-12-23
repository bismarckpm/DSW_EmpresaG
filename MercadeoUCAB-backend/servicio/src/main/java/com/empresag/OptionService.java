package com.empresag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/options")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class OptionService {
    @GET
    @Path("/all-by-question/{id}")
    public List<OpcionEntity> allQuestionOptions(@PathParam("id") long id){
        DaoOpcion daoOpcion = new DaoOpcion();
        return daoOpcion.getAllOptionsByQuestion(id);
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteOption(@PathParam("id") long id){
        DaoOpcion daoOpcion = new DaoOpcion();
        try {
            OpcionEntity opcion = daoOpcion.find(id, OpcionEntity.class);
            daoOpcion.delete(opcion);
            return Response.ok().entity(opcion).build();
        }
        catch (NullPointerException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
