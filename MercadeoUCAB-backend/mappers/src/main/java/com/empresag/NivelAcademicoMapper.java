package com.empresag;

public class NivelAcademicoMapper {

    public static NivelAcademicoEntity mapIntToEntity (long nivelAcademico){

        NivelAcademicoEntity entity = new NivelAcademicoEntity(nivelAcademico);

        return entity;

    }

}
