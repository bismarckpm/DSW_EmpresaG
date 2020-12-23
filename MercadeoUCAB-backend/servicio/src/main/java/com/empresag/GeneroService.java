package com.empresag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path( "/genero" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class GeneroService {

    @PUT
    @Path("/add")
    public GeneroDto addGenero ( GeneroDto generoDto ) {

        System.out.println("DATA: " + generoDto.toString());

        GeneroDto resultado = new GeneroDto();
        try {

            DaoGenero daoGenero = new DaoGenero();

            GeneroEntity genero = new GeneroEntity();
            genero.setNombre(generoDto.getNombre());

            GeneroEntity resul = daoGenero.insert(genero);
            resultado.set_id(resul.get_id());

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return resultado;

    }

    @GET
    @Path( "/consulta" )
    public List<GeneroDto> consulta()
    {
        List<GeneroDto> resultado = new ArrayList<>();

        try {
            DaoGenero daoGenero = new DaoGenero();
            List<GeneroEntity> generos = daoGenero.findAll(GeneroEntity.class);

            for (int i = 0; i < generos.size(); i++){
                GeneroEntity genero = generos.get(i);

                GeneroDto resul_dto = new GeneroDto();

                resul_dto.set_id(genero.get_id());
                resul_dto.setNombre(genero.getNombre());

                resultado.add(resul_dto);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }


        return resultado;
    }
}
