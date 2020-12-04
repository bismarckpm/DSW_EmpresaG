package com.empresag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/types")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class TypeService {
    @GET
    @Path("/all")
    public List<MarcaTipoEntity> allTypes(){
        DaoMarcaTipo daoMarcaTipo = new DaoMarcaTipo();
        return daoMarcaTipo.findAll(MarcaTipoEntity.class);
    }

    @POST
    @Path("/add")
    public MarcaTipoEntity addType(MarcaTipoDto marcaTipoDto) {
        DaoMarcaTipo daoMarcaTipo = new DaoMarcaTipo();
        DaoTipo daoTipo = new DaoTipo();
        DaoMarca daoMarca = new DaoMarca();

        MarcaEntity marca = daoMarca.find(marcaTipoDto.getFkMarca().get_id(), MarcaEntity.class);
        marca.setNombre(marcaTipoDto.getFkMarca().getNombre());

        TipoEntity tipo = new TipoEntity();
        tipo.setNombre(marcaTipoDto.getFkTipo().getNombre());
        tipo.setDescripcion(marcaTipoDto.getFkTipo().getDescripcion());
        daoTipo.insert(tipo);

        MarcaTipoEntity mt = new MarcaTipoEntity();
        mt.setFkTipo(tipo);
        mt.setFkMarca(marca);
        daoMarcaTipo.insert(mt);

        return mt;
    }

    @PUT
    @Path("/update/{id}")
    public Response updateType(@PathParam("id") long id, MarcaTipoDto marcaTipoDto){
        DaoMarcaTipo daoMarcaTipo = new DaoMarcaTipo();
        DaoTipo daoTipo = new DaoTipo();
        MarcaTipoEntity mt = daoMarcaTipo.find(id, MarcaTipoEntity.class);

        if (mt != null){
            mt.setFkMarca(new MarcaEntity(marcaTipoDto.getFkMarca().get_id(),
                    marcaTipoDto.getFkMarca().getNombre()));

            mt.setFkTipo(new TipoEntity(marcaTipoDto.getFkTipo().get_id()));

            TipoEntity tipo = daoTipo.find(marcaTipoDto.getFkTipo().get_id(), TipoEntity.class);

            if (tipo != null){
                tipo.setNombre(marcaTipoDto.getFkTipo().getNombre());
                tipo.setDescripcion(marcaTipoDto.getFkTipo().getDescripcion());
                daoTipo.update(tipo);
                daoMarcaTipo.update(mt);
                return Response.ok().entity(mt).build();
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
    public Response deleteType(@PathParam("id") long id){
        DaoMarcaTipo daoMarcaTipo = new DaoMarcaTipo();
        MarcaTipoEntity mt = daoMarcaTipo.find(id, MarcaTipoEntity.class);

        if (mt != null){
            daoMarcaTipo.delete(mt);
            return Response.ok().entity(mt).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}


