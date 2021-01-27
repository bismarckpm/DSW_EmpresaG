package com.empresag;

public class PersonaDispositivoMapper {

    public static PersonaDispositivoEntity mapDtoToEntity ( int device, PersonaEntity persona){

        PersonaDispositivoEntity entity = new PersonaDispositivoEntity();

//        entity.setFkDispositivo(DispositivoMapper.mapIntToEntity( device ));

        DaoDispositivo daoDispositivo = FabricaDao.crearDaoDispositivo();
        entity.setFkDispositivo(daoDispositivo.find( (long) device , DispositivoEntity.class));
        entity.setFkPersona(persona);

        return entity;
    }

}
