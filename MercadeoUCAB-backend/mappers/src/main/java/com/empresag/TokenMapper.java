package com.empresag;

import java.util.Objects;

public class TokenMapper {

    public static TokenEntity mapDtoToEntity( TokenDto dto )
    {
        TokenEntity entity = FabricaEntity.crearTokenEntity(dto.get_id());

        entity.setToken_login(dto.getToken_login());
        entity.setToken_reset(dto.getToken_reset());

        if ( Objects.nonNull( dto.getFkUsuario() ) )
        {
            entity.setFkUsuario( UsuarioMapper.mapDtoToEntity( dto.getFkUsuario() ) );
        }

        return entity;
    }

    public static TokenDto mapEntityToDto( TokenEntity entity ) throws IndexDatabaseException {
        final TokenDto dto = FabricaDto.crearTokenDto();

        dto.set_id(entity.get_id());
        dto.setToken_login(entity.getToken_login());
        dto.setToken_reset(entity.getToken_reset());

        dto.setFkUsuario(UsuarioMapper.mapEntityToDto(entity.getFkUsuario()));

        return dto;
    }

}
