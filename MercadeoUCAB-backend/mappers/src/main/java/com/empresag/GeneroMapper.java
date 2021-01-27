package com.empresag;

public class GeneroMapper {

    public static GeneroEntity mapDtoToEntity(GeneroDto dto){

//        GeneroEntity entity = FabricaEntity.crearGeneroEntity(dto.get_id());
//        entity.setNombre(dto.getNombre());

        DaoGenero daoGenero = FabricaDao.crearDaoGenero();
        return daoGenero.find(dto.get_id(), GeneroEntity.class);
    }

    public static GeneroDto mapEntityToDto( GeneroEntity entity ) throws IndexDatabaseException {
        final GeneroDto dto = new GeneroDto();

        dto.set_id(entity.get_id());
        dto.setNombre( entity.getNombre());

        return dto;
    }
}
