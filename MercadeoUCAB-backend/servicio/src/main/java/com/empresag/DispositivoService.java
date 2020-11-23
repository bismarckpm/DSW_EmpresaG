package com.empresag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path( "/dispositivo" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class DispositivoService {


    @PUT
    @Path("/add")
    public DispositivoDto addDispositivo ( DispositivoDto dispositivoDto ) {

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
    public List<DispositivoDto> consulta()
    {
        List<DispositivoDto> resultado = new ArrayList<>();

        try {
            DaoDispositivo daoDispositivo = new DaoDispositivo();
            List<DispositivoEntity> dispositivos = daoDispositivo.findAll(DispositivoEntity.class);

            for (int i = 0; i < dispositivos.size(); i++){
                DispositivoEntity dispositivo = dispositivos.get(i);

                DispositivoDto resul_dto = new DispositivoDto();

                resul_dto.set_id(dispositivo.get_id());
                resul_dto.setNombre(dispositivo.getNombre());

                resultado.add(resul_dto);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }


        return resultado;
    }

}
