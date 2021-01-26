package com.empresag;

import java.util.Objects;

public class UsuarioMapper {

    public static UsuarioEntity mapDtoToEntity( UsuarioDto dto )
    {
        UsuarioEntity entity = FabricaEntity.crearUsuarioEntity(dto.get_id());

        entity.setEmail( dto.getEmail() );
        entity.setPassword( dto.getPassword() );
        entity.setEstado( dto.getEstado() );


        if ( Objects.nonNull( dto.getFkRol() ) )
        {
            entity.setFk_Rol( RolMapper.mapDtoToEntity( dto.getFkRol() ) );
        }

        if ( Objects.nonNull( dto.getFkPersona() ) )
        {
            entity.setFk_Persona( PersonaMapper.mapDtoToEntity( dto.getFkPersona() ) );
        }

        return entity;
    }

    public static UsuarioDto mapEntityToDto( UsuarioEntity entity ) throws IndexDatabaseException {
        final UsuarioDto dto = new UsuarioDto();

        dto.setEmail( entity.getEmail() );
        dto.setEstado( entity.getEstado() );
        dto.setFkRol( RolMapper.mapEntityToDto( entity.getFk_Rol() ) );
        dto.setFkPersona( PersonaMapper.mapEntityToDto( entity.getFk_Persona() ) );

//        TODO - agregar a dto:
//            hijos
//            dispositivos
//            telefono
//            ocupacion
//            nivel academico

        return dto;
    }
}
