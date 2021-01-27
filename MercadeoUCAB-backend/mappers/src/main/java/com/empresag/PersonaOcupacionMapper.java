package com.empresag;

public class PersonaOcupacionMapper {

    public static PersonaOcupacionEntity mapIntToEntity (long ocupacion, PersonaEntity persona ){
        PersonaOcupacionEntity entity = new PersonaOcupacionEntity();

        entity.setFkPersona(persona);
//        entity.setFkOcupacion( OcupacionMapper.mapIntToEntity(ocupacion) );

        DaoOcupacion daoOcupacion = FabricaDao.crearDaoOcupacion();
        entity.setFkOcupacion( daoOcupacion.find( ocupacion, OcupacionEntity.class ) );

        return entity;

    }

}
