package com.empresag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path( "/rol" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class RolService {


    @PUT
    @Path("/add")
    public RolDto addRol ( RolDto rolDto ) {

        System.out.println("DATA: " + rolDto.toString());

        RolDto resultado = new RolDto();
        try {

            DaoRol daoRol = new DaoRol();

            RolEntity rol = new RolEntity();
            rol.setNombre(rolDto.getNombre());

            RolEntity resul = daoRol.insert(rol);
            resultado.set_id(resul.get_id());

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return resultado;

    }

    @GET
    @Path( "/consulta" )
    public List<RolDto> consulta()
    {
        List<RolDto> resultado = new ArrayList<>();

        try {
            DaoRol daoRol = new DaoRol();
            List<RolEntity> roles = daoRol.findAll(RolEntity.class);

            for (int i = 0; i < roles.size(); i++){
                RolEntity rol = roles.get(i);

                RolDto resul_dto = new RolDto();

                resul_dto.set_id(rol.get_id());
                resul_dto.setNombre(rol.getNombre());

                resultado.add(resul_dto);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }


        return resultado;
    }

}
