package com.empresag;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path( "/disponibilidad" )
@Produces( MediaType.APPLICATION_JSON )
public class DisponibilidadService {

    @PUT
    @Path("/add")
    public DisponibilidadDto addDisponibilidad ( DisponibilidadDto disponibilidadDto ) {

        System.out.println("DATA: " + disponibilidadDto.toString());

        DisponibilidadDto resultado = new DisponibilidadDto();
        try {

            DaoDisponibilidad daoDisponibilidad = new DaoDisponibilidad();

            DisponibilidadEntity disponibilidad = new DisponibilidadEntity();
            disponibilidad.setHora(disponibilidadDto.getHora());

            DisponibilidadEntity resul = daoDisponibilidad.insert(disponibilidad);
            resultado.set_id(resul.get_id());

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return resultado;

    }

    @GET
    @Path( "/consulta" )
    public List<DisponibilidadDto> consulta()
    {
        List<DisponibilidadDto> resultado = new ArrayList<>();

        try {
            DaoDisponibilidad daoDisponibilidad = new DaoDisponibilidad();
            List<DisponibilidadEntity> disponibilidades = daoDisponibilidad.findAll(DisponibilidadEntity.class);

            for (int i = 0; i < disponibilidades.size(); i++){
                DisponibilidadEntity disponibilidad = disponibilidades.get(i);

                DisponibilidadDto resul_dto = new DisponibilidadDto();

                resul_dto.set_id(disponibilidad.get_id());
                resul_dto.setHora(disponibilidad.getHora());

                resultado.add(resul_dto);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }


        return resultado;
    }
}
