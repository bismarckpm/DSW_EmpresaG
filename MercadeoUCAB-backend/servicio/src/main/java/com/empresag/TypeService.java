package com.empresag;

import com.empresag.type.ComandoAddType;
import com.empresag.type.ComandoDeleteType;
import com.empresag.type.ComandoUpdateType;

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
    public RespuestaDto<List<MarcaTipoEntity>> allTypes(){

        DaoMarcaTipo marcaTipo = FabricaDao.crearDaoMarcaTipo();

        RespuestaDto<List<MarcaTipoEntity>> res = new RespuestaDto<>();

        try {
            res.setCodigo(0);
            res.setEstado("OK");
            res.setObjeto(marcaTipo.findAll(MarcaTipoEntity.class));

        }catch (Exception e){
            e.printStackTrace();
            res.setCodigo(-1);
            res.setEstado("ERROR");
            res.setMensaje(e.getMessage());
        }

        return res;

    }

    @POST
    @Path("/add")
    public RespuestaDto<MarcaTipoEntity> addType(MarcaTipoDto marcaTipoDto) {

        RespuestaDto<MarcaTipoEntity> res = new RespuestaDto<>();

        ComandoAddType addType = new ComandoAddType(marcaTipoDto);

        try {
            addType.execute();
            res.setCodigo(0);
            res.setEstado("OK");
            res.setObjeto(addType.getResult());
        } catch (Exception e) {
            e.printStackTrace();
            res.setCodigo(-1);
            res.setEstado("ERROR");
            res.setMensaje(e.getMessage());
        }

        return res;
    }

    @PUT
    @Path("/update/{id}")
    public RespuestaDto<String> updateType(@PathParam("id") long id, MarcaTipoDto marcaTipoDto){

       RespuestaDto<String> res = new RespuestaDto<>();
       ComandoUpdateType updateType = new ComandoUpdateType(id, marcaTipoDto);

        try{
            updateType.execute();
            res.setCodigo(0);
            res.setEstado("OK");
            res.setObjeto(updateType.getResult());

        } catch (Exception e) {
            e.printStackTrace();
            res.setCodigo(-1);
            res.setEstado("ERROR");
            res.setMensaje(e.getMessage());
        }

        return  res;

    }

    @DELETE
    @Path("/delete/{id}")
    public RespuestaDto<String> deleteType(@PathParam("id") long id){

        RespuestaDto<String> res = new RespuestaDto<>();
        ComandoDeleteType deleteType = new ComandoDeleteType(id);

        try {
            deleteType.execute();
            res.setCodigo(0);
            res.setEstado("OK");
            res.setObjeto(deleteType.getResult());

        } catch (Exception e) {
            e.printStackTrace();
            res.setCodigo(-1);
            res.setEstado("ERROR");
            res.setMensaje(e.getMessage());
        }

        return res;

    }
}


