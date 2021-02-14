package com.empresag;

public class TelefonoMapper {

    public static TelefonoEntity mapStringToEntity(String telefono, PersonaEntity persona){

        TelefonoEntity entity = new TelefonoEntity();

        entity.setNumero(telefono);
        entity.setFkPersona(persona);

        return entity;
    }

    public static TelefonoEntity mapDtoToEntity(TelefonoDto telefono, PersonaEntity persona){

        TelefonoEntity entity = new TelefonoEntity();

        entity.setNumero(telefono.getNumero());
        entity.setFkPersona(persona);

        return entity;
    }

    public static TelefonoDto mapEntityToDto(TelefonoEntity entity){
        TelefonoDto dto = new TelefonoDto();

        dto.setNumero(entity.getNumero());

        return dto;
    }

}
