package com.empresag;

public class DisponibilidadMapper {

    public static DisponibilidadEntity mapDtoToEntity(DisponibilidadDto dto){

//        DisponibilidadEntity entity = FabricaEntity.crearDisponibilidadEntity(dto.get_id());
//        entity.setHora(dto.getHora());

        DaoDisponibilidad daoDisponibilidad = FabricaDao.crearDaoDisponibilidad();
        return daoDisponibilidad.find(dto.get_id(), DisponibilidadEntity.class);
    }

    public static DisponibilidadDto mapEntityToDto( DisponibilidadEntity entity ) throws IndexDatabaseException {
        final DisponibilidadDto dto = new DisponibilidadDto();

        dto.set_id(entity.get_id());
        dto.setHora( entity.getHora());

        return dto;
    }

}
