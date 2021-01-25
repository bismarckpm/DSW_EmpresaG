package com.empresag;

public class PersonaNvlacademicoMapper {

    public static PersonaNvlacademicoEntity mapIntToEntity (long NvlAcademico, PersonaEntity persona){

        PersonaNvlacademicoEntity entity = new PersonaNvlacademicoEntity();

        entity.setFkPersona(persona);
        entity.setFkNivelAcademico( NivelAcademicoMapper.mapIntToEntity(NvlAcademico) );

        return entity;

    }

}