package com.empresag;

import java.util.Objects;

public class PersonaMapper {

    public static PersonaEntity mapDtoToEntity(PersonaDto dto){

        PersonaEntity entity = FabricaEntity.crearPersonaEntity(dto.get_id());

        entity.setDocumentoIdentidad(dto.getDocumentoIdentidad());
        entity.setPrimerNombre(dto.getPrimerNombre());
        entity.setSegundoNombre(dto.getSegundoNombre());
        entity.setPrimerApellido(dto.getPrimerApellido());
        entity.setSegundoApellido(dto.getSegundoApellido());
        entity.setFechaNacimiento(dto.getFechaNacimiento());
        entity.setNumero_personas_encasa(dto.getNumero_personas_encasa());

//        if ( Objects.nonNull( dto.getFkGenero() ) )
//        {
//            entity.setFkGenero( GeneroMapper.mapDtoToEntity( dto.getFkGenero() ) );
//        }

        DaoGenero daoGenero = FabricaDao.crearDaoGenero();
        entity.setFkGenero( daoGenero.find( dto.getFkGenero().get_id(), GeneroEntity.class ));

//        if ( Objects.nonNull( dto.getFkEdoCivil() ) )
//        {
//            entity.setFkEdoCivil( EdoCivilMapper.mapDtoToEntity( dto.getFkEdoCivil() ) );
//        }

        DaoEdoCivil daoEdoCivil = FabricaDao.crearDaoEdoCivil();
        entity.setFkEdoCivil( daoEdoCivil.find( dto.getFkEdoCivil().get_id(), EdoCivilEntity.class));

//        if ( Objects.nonNull( dto.getFkLugar() ) )
//        {
//            entity.setFkLugar( LugarMapper.mapDtoToEntity( dto.getFkLugar() ) );
//        }

        DaoLugar daoLugar = FabricaDao.crearDaoLugar();
        entity.setFkLugar( daoLugar.find(dto.getFkLugar().get_id(),LugarEntity.class) );

//        if ( Objects.nonNull( dto.getId_horario_inicial() ) )
//        {
//            entity.setFkDisponibilidadInicial( DisponibilidadMapper.mapDtoToEntity( dto.getId_horario_inicial() ) );
//        }

        DaoDisponibilidad daoDisponibilidad = FabricaDao.crearDaoDisponibilidad();
        entity.setFkDisponibilidadInicial(daoDisponibilidad.find(dto.getId_horario_inicial().get_id(), DisponibilidadEntity.class));

//        if ( Objects.nonNull( dto.getId_horario_final() ) )
//        {
//            entity.setFkDisponibilidadFinal( DisponibilidadMapper.mapDtoToEntity( dto.getId_horario_final() ) );
//        }

        entity.setFkDisponibilidadFinal(daoDisponibilidad.find(dto.getId_horario_final().get_id(), DisponibilidadEntity.class));

//        if (Objects.nonNull( dto.getFkPersona() )){
//            entity.setFkPersona( PersonaMapper.mapDtoToEntity( dto.getFkPersona() ) );
//        }

        return entity;
    }

    public static PersonaEntity mapDtoToEntity(PersonaDto dto, PersonaEntity padre){

        PersonaEntity entity = FabricaEntity.crearPersonaEntity(dto.get_id());

        entity.setPrimerNombre(dto.getPrimerNombre());
        entity.setSegundoNombre(dto.getSegundoNombre());
        entity.setPrimerApellido(dto.getPrimerApellido());
        entity.setSegundoApellido(dto.getSegundoApellido());
        entity.setFechaNacimiento(dto.getFechaNacimiento());

        entity.setFkPersona( padre );

//        if ( Objects.nonNull( dto.getFkGenero() ) )
//        {
//            entity.setFkGenero( GeneroMapper.mapDtoToEntity( dto.getFkGenero() ) );
//        }

        DaoGenero daoGenero = FabricaDao.crearDaoGenero();
        entity.setFkGenero( daoGenero.find( dto.getFkGenero().get_id(), GeneroEntity.class ));

        return entity;
    }

    public static PersonaDto mapEntityToDto(PersonaEntity entity) throws IndexDatabaseException {
        final PersonaDto dto = new PersonaDto();

        dto.setPrimerNombre( entity.getPrimerNombre() );
        dto.setSegundoNombre( entity.getSegundoNombre() );
        dto.setPrimerApellido( entity.getPrimerApellido() );
        dto.setSegundoApellido( entity.getSegundoApellido() );
        dto.setFechaNacimiento( entity.getFechaNacimiento() );
        dto.setNumero_personas_encasa(entity.getNumero_personas_encasa());

        if ( Objects.nonNull( entity.getFkGenero() ) )
        {
            dto.setFkGenero( GeneroMapper.mapEntityToDto( entity.getFkGenero() ) );
        }

        if ( Objects.nonNull( entity.getFkEdoCivil() ) )
        {
            dto.setFkEdoCivil( EdoCivilMapper.mapEntityToDto( entity.getFkEdoCivil() ) );
        }

        if ( Objects.nonNull( entity.getFkLugar() ) )
        {
            dto.setFkLugar( LugarMapper.mapEntityToDto( entity.getFkLugar() ) );
        }

        if ( Objects.nonNull( entity.getFkDisponibilidadInicial() ) )
        {
            dto.setId_horario_inicial( DisponibilidadMapper.mapEntityToDto( entity.getFkDisponibilidadInicial() ) );
        }

        if ( Objects.nonNull( entity.getFkDisponibilidadFinal() ) )
        {
            dto.setId_horario_final( DisponibilidadMapper.mapEntityToDto( entity.getFkDisponibilidadFinal() ) );
        }

        if (Objects.nonNull( entity.getFkPersona() )){
            dto.setFkPersona( PersonaMapper.mapEntityToDto( entity.getFkPersona() ) );
        }

        return dto;
    }
}
