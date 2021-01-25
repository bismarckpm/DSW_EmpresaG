package com.empresag;

public class PersonaDispositivoMapper {

    public static PersonaDispositivoEntity mapDtoToEntity ( int device, PersonaEntity persona){

        PersonaDispositivoEntity entity = new PersonaDispositivoEntity();

        entity.setFkDispositivo(DispositivoMapper.mapIntToEntity( device ));
        entity.setFkPersona(persona);

        return entity;
    }

}
