package com.empresag;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
}
