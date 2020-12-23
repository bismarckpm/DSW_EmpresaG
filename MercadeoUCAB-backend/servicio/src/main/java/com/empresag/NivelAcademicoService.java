package com.empresag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


@Path( "/nivelacademico" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class NivelAcademicoService {

    @PUT
    @Path("/add")
    public NivelAcademicoDto addNivelAcademico ( NivelAcademicoDto nivelAcademicoDto ) {

        System.out.println("DATA: " + nivelAcademicoDto.toString());

        NivelAcademicoDto resultado = new NivelAcademicoDto();
        try {

            DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();

            NivelAcademicoEntity nivelAcademico = new NivelAcademicoEntity();
            nivelAcademico.setNombre(nivelAcademicoDto.getNombre());

            NivelAcademicoEntity resul = daoNivelAcademico.insert(nivelAcademico);
            resultado.set_id(resul.get_id());

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return resultado;

    }

    @GET
    @Path( "/consulta" )
    public List<NivelAcademicoDto> consulta()
    {
        List<NivelAcademicoDto> resultado = new ArrayList<>();

        try {
            DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
            List<NivelAcademicoEntity> nivelesAcademicos = daoNivelAcademico.findAll(NivelAcademicoEntity.class);

            for (int i = 0; i < nivelesAcademicos.size(); i++){
                NivelAcademicoEntity nivelAcademico = nivelesAcademicos.get(i);

                NivelAcademicoDto resul_dto = new NivelAcademicoDto();

                resul_dto.set_id(nivelAcademico.get_id());
                resul_dto.setNombre(nivelAcademico.getNombre());

                resultado.add(resul_dto);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }


        return resultado;
    }
}
