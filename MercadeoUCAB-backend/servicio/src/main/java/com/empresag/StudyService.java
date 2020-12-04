package com.empresag;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/studies")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class StudyService {
    @GET
    @Path("/existing")
    public List<FiltroEntity> allExistingStudies(){
        DaoFiltro daoFiltro = new DaoFiltro();
        return daoFiltro.findAll(FiltroEntity.class);
    }
}
