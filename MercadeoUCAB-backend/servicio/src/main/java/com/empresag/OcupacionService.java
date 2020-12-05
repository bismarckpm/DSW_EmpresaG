package com.empresag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path( "/ocupacion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class OcupacionService {

    @PUT
    @Path("/add")
    public OcupacionDto addOcupacion ( OcupacionDto ocupacionDto ) {

        System.out.println("DATA: " + ocupacionDto.toString());

        OcupacionDto resultado = new OcupacionDto();
        try {

            DaoOcupacion daoOcupacion = new DaoOcupacion();

            OcupacionEntity ocupacion = new OcupacionEntity();
            ocupacion.setNombre(ocupacionDto.getNombre());

            OcupacionEntity resul = daoOcupacion.insert(ocupacion);
            resultado.set_id(resul.get_id());

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return resultado;

    }

    @GET
    @Path( "/consulta" )
    public List<OcupacionDto> consulta()
    {
        List<OcupacionDto> resultado = new ArrayList<>();

        try {
            DaoOcupacion daoOcupacion = new DaoOcupacion();
            List<OcupacionEntity> ocupaciones = daoOcupacion.findAll(OcupacionEntity.class);

            for (int i = 0; i < ocupaciones.size(); i++){
                OcupacionEntity ocupacion = ocupaciones.get(i);

                OcupacionDto resul_dto = new OcupacionDto();

                resul_dto.set_id(ocupacion.get_id());
                resul_dto.setNombre(ocupacion.getNombre());

                resultado.add(resul_dto);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }


        return resultado;
    }
}
