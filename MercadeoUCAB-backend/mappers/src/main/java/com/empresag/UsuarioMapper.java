package com.empresag;

import java.util.List;
import java.util.Objects;

public class UsuarioMapper {

    public static UsuarioEntity mapDtoToEntity( UsuarioDto dto )
    {
        UsuarioEntity entity = FabricaEntity.crearUsuarioEntity(dto.get_id());

        entity.setEmail( dto.getEmail() );
        entity.setPassword( dto.getPassword() );
        entity.setEstado( dto.getEstado() );


//        if ( Objects.nonNull( dto.getFkRol() ) )
//        {
//            entity.setFk_Rol( RolMapper.mapDtoToEntity( dto.getFkRol() ) );
//        }

        if (dto.getFkRol().get_id() == 4) {
            DaoRol daoRol = FabricaDao.crearDaoRol();
            entity.setFk_Rol(daoRol.getEncuestadoRol().get(0));


            if (Objects.nonNull(dto.getFkPersona())) {
                entity.setFk_Persona(PersonaMapper.mapDtoToEntity(dto.getFkPersona()));
            }
        }
        
        return entity;
    }

    public static UsuarioDto mapEntityToDto( UsuarioEntity entity ) throws IndexDatabaseException {
        final UsuarioDto dto = new UsuarioDto();


        dto.set_id(entity.get_id());
        dto.setEmail( entity.getEmail() );
        dto.setEstado( entity.getEstado() );
        dto.setFkRol( RolMapper.mapEntityToDto( entity.getFk_Rol() ) );

        if (dto.getFkRol().get_id() == 4)
            dto.setFkPersona( PersonaMapper.mapEntityToDto( entity.getFk_Persona() ) );


        return dto;
    }
}
