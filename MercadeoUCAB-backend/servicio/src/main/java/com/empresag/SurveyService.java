package com.empresag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/survey")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class SurveyService {
    @GET
    @Path("/available-population/{id}")
    public Response getAvailablePopulation(@PathParam("id") long id){
        DaoEncuesta daoEncuesta = new DaoEncuesta();
        try {
            List<PersonaEntity> personas = daoEncuesta.getAvailablePopulation(id);
            return Response.ok().entity(personas).build();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
