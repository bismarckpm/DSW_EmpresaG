package com.empresag;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path( "/edocivil" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class EdoCivilService {

    @PUT
    @Path("/add")
    public EdoCivilDto addEdoCivil ( EdoCivilDto edoCivilDto ) {

        System.out.println("DATA: " + edoCivilDto.toString());

        EdoCivilDto resultado = new EdoCivilDto();
        try {

            DaoEdoCivil daoEdoCivil = new DaoEdoCivil();

            EdoCivilEntity edoCivil = new EdoCivilEntity();
            edoCivil.setNombre(edoCivilDto.getNombre());

            EdoCivilEntity resul = daoEdoCivil.insert(edoCivil);
            resultado.set_id(resul.get_id());

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return resultado;

    }

    @GET
    @Path( "/consulta" )
    public List<EdoCivilDto> consulta()
    {
        List<EdoCivilDto> resultado = new ArrayList<>();

        try {
            DaoEdoCivil daoEdoCivil = new DaoEdoCivil();
            List<EdoCivilEntity> edosciviles = daoEdoCivil.findAll(EdoCivilEntity.class);

            for (int i = 0; i < edosciviles.size(); i++){
                EdoCivilEntity edocivil = edosciviles.get(i);

                EdoCivilDto resul_dto = new EdoCivilDto();

                resul_dto.set_id(edocivil.get_id());
                resul_dto.setNombre(edocivil.getNombre());

                resultado.add(resul_dto);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }


        return resultado;
    }
}
