package com.empresag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/prueba" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class DispositivoService {


    @PUT
    @Path("/addDispositivo")
    public DispositivoDto addDispositivo ( DispositivoDto dispositivoDto ){

        System.out.println("DATA: " + dispositivoDto.toString());

        DispositivoDto resultado = new DispositivoDto();
        try {

            DaoDispositivo daoDispositivo = new DaoDispositivo();

            DispositivoEntity dispositivo = new DispositivoEntity();
            dispositivo.setNombre(dispositivoDto.getNombre());

            DispositivoEntity resul = daoDispositivo.insert(dispositivo);
            resultado.set_id(resul.get_id());

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return resultado;

    }


    @GET
    @Path( "/consulta" )
    public String consulta()
    {
        return "Epa";
    }

}
