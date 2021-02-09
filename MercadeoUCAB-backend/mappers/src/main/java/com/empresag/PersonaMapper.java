package com.empresag;

import java.util.List;
import java.util.Objects;

public class PersonaMapper {

    public static PersonaEntity mapDtoToEntity(PersonaDto dto) {

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
        entity.setFkGenero(daoGenero.find(dto.getFkGenero().get_id(), GeneroEntity.class));

//        if ( Objects.nonNull( dto.getFkEdoCivil() ) )
//        {
//            entity.setFkEdoCivil( EdoCivilMapper.mapDtoToEntity( dto.getFkEdoCivil() ) );
//        }

        DaoEdoCivil daoEdoCivil = FabricaDao.crearDaoEdoCivil();
        entity.setFkEdoCivil(daoEdoCivil.find(dto.getFkEdoCivil().get_id(), EdoCivilEntity.class));

//        if ( Objects.nonNull( dto.getFkLugar() ) )
//        {
//            entity.setFkLugar( LugarMapper.mapDtoToEntity( dto.getFkLugar() ) );
//        }

        DaoLugar daoLugar = FabricaDao.crearDaoLugar();
        entity.setFkLugar(daoLugar.find(dto.getFkLugar().get_id(), LugarEntity.class));

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

    public static PersonaEntity mapDtoToEntity(PersonaDto dto, PersonaEntity padre) {

        PersonaEntity entity = FabricaEntity.crearPersonaEntity(dto.get_id());

        entity.setPrimerNombre(dto.getPrimerNombre());
        entity.setSegundoNombre(dto.getSegundoNombre());
        entity.setPrimerApellido(dto.getPrimerApellido());
        entity.setSegundoApellido(dto.getSegundoApellido());
        entity.setFechaNacimiento(dto.getFechaNacimiento());

        entity.setFkPersona(padre);

//        if ( Objects.nonNull( dto.getFkGenero() ) )
//        {
//            entity.setFkGenero( GeneroMapper.mapDtoToEntity( dto.getFkGenero() ) );
//        }

        DaoGenero daoGenero = FabricaDao.crearDaoGenero();
        entity.setFkGenero(daoGenero.find(dto.getFkGenero().get_id(), GeneroEntity.class));

        return entity;
    }

    public static PersonaDto mapEntityToDto(PersonaEntity entity) throws IndexDatabaseException {
        final PersonaDto dto = new PersonaDto();

        dto.set_id(entity.get_id());
        dto.setPrimerNombre(entity.getPrimerNombre());
        dto.setSegundoNombre(entity.getSegundoNombre());
        dto.setPrimerApellido(entity.getPrimerApellido());
        dto.setSegundoApellido(entity.getSegundoApellido());
        dto.setFechaNacimiento(entity.getFechaNacimiento());
        dto.setNumero_personas_encasa(entity.getNumero_personas_encasa());
        dto.setDocumentoIdentidad(entity.getDocumentoIdentidad());

        if (Objects.nonNull(entity.getFkGenero())) {
            dto.setFkGenero(GeneroMapper.mapEntityToDto(entity.getFkGenero()));
        }

        if (Objects.nonNull(entity.getFkEdoCivil())) {
            dto.setFkEdoCivil(EdoCivilMapper.mapEntityToDto(entity.getFkEdoCivil()));
        }

        if (Objects.nonNull(entity.getFkLugar())) {
            dto.setFkLugar(LugarMapper.mapEntityToDto(entity.getFkLugar()));
        }

        if (Objects.nonNull(entity.getFkDisponibilidadInicial())) {
            dto.setId_horario_inicial(DisponibilidadMapper.mapEntityToDto(entity.getFkDisponibilidadInicial()));
        }

        if (Objects.nonNull(entity.getFkDisponibilidadFinal())) {
            dto.setId_horario_final(DisponibilidadMapper.mapEntityToDto(entity.getFkDisponibilidadFinal()));
        }

        if (Objects.nonNull(entity.getFkPersona())) {
            dto.setFkPersona(PersonaMapper.mapEntityToDto(entity.getFkPersona()));
        }


//        TODO - agregar a dto:
//            hijos
//            dispositivos
//            telefono
//            ocupacion
//            nivel academico


////          Busqueda de disponibilidad Inicial y Final de Persona
//        DaoDisponibilidad daoDisponibilidad = new DaoDisponibilidad();
//        DisponibilidadEntity dispoInicial = daoDisponibilidad
//                .find(entity.getFkDisponibilidadInicial().get_id(), DisponibilidadEntity.class);
//        DisponibilidadEntity dispoFinal = daoDisponibilidad
//                .find(entity.getFkDisponibilidadFinal().get_id(), DisponibilidadEntity.class);
//
//        DisponibilidadDto dispoInicialDto = new DisponibilidadDto();
//        DisponibilidadDto dispoFinalDto = new DisponibilidadDto();
//        dispoInicialDto.setHora(dispoInicial.getHora());
//        dispoInicialDto.set_id(dispoInicial.get_id());
//        dispoFinalDto.setHora(dispoFinal.getHora());
//        dispoFinalDto.set_id(dispoFinal.get_id());

        dto.setId_horario_inicial(DisponibilidadMapper.mapEntityToDto(entity.getFkDisponibilidadInicial()));
        dto.setId_horario_final(DisponibilidadMapper.mapEntityToDto(entity.getFkDisponibilidadFinal()));


//          Busqueda de Hijos
        DaoPersona daoHijo = FabricaDao.crearDaoPersona();
        List<PersonaEntity> listaHijos = daoHijo.findSons(entity.get_id());

        PersonaDto[] hijos = new PersonaDto[listaHijos.size()];

        for (int i = 0; i < listaHijos.size(); i++) {
//            PersonaEntity hijo = listaHijos.get(i);
//            PersonaDto hijoDto = new PersonaDto();
//            hijoDto.set_id(hijo.get_id());
//            hijoDto.setPrimerNombre(hijo.getPrimerNombre());
//            hijoDto.setPrimerApellido(hijo.getPrimerApellido());
//            hijoDto.setFechaNacimiento(hijo.getFechaNacimiento());
//
//            DaoGenero daoGeneroHijo = new DaoGenero();
//            GeneroEntity generoHijo = daoGeneroHijo.find(hijo.getFkGenero().get_id(), GeneroEntity.class);
//            GeneroDto genHijo = new GeneroDto();
//
//            genHijo.set_id(generoHijo.get_id());
//            genHijo.setNombre(generoHijo.getNombre());
//            hijoDto.setFkGenero(genHijo);
            hijos[i] = mapEntityHijoToDto(listaHijos.get(i));
        }

        dto.setHijos(hijos);


//          Busqueda de telefono de Persona
        DaoTelefono daoTelefonoPersona = FabricaDao.crearDaoTelefono();
        List<TelefonoEntity> telefonoPersona = daoTelefonoPersona
                .findTelefono(entity.get_id());
        if (telefonoPersona.size() > 0) {
            dto.setTelefono(telefonoPersona.get(0).getNumero());
        }

//          Busqueda de ocupacion de Persona
        DaoPersonaOcupacion daoPersonaOcupacion = FabricaDao.crearDaoPersonaOcupacion();
        dto.setOcupacion(daoPersonaOcupacion
                .findOcupacion(entity.get_id()).get(0).get_id());

//          Busqueda de nivel academico de Persona
        DaoPersonaNvlacademico daoPersonaNvlacademico = FabricaDao.crearDaoPersonaNvlacademico();
        dto.setId_nivel_academico(daoPersonaNvlacademico
                .findNivAcademico(entity.get_id()).get(0).get_id());

//          Busqueda de dispositivos de Persona
        DaoDispositivo daoDispositivo = FabricaDao.crearDaoDispositivo();
        List<DispositivoEntity> listaDispositivos = daoDispositivo
                .findDispositivos(entity.get_id());

        int[] listaDis = new int[listaDispositivos.size()];

        for (int i = 0; i < listaDispositivos.size(); i++) {
            listaDis[i] = (int) listaDispositivos.get(i).get_id();
        }

        dto.setDispositivos(listaDis);


        return dto;
    }

    public static PersonaDto mapEntityHijoToDto(PersonaEntity entity) throws IndexDatabaseException {
        final PersonaDto dto = new PersonaDto();

        dto.set_id(entity.get_id());
        dto.setPrimerNombre( entity.getPrimerNombre() );
        dto.setSegundoNombre( entity.getSegundoNombre() );
        dto.setPrimerApellido( entity.getPrimerApellido() );
        dto.setSegundoApellido( entity.getSegundoApellido() );
        dto.setFechaNacimiento( entity.getFechaNacimiento() );

        if ( Objects.nonNull( entity.getFkGenero() ) )
        {
            dto.setFkGenero( GeneroMapper.mapEntityToDto( entity.getFkGenero() ) );
        }

        return dto;
    }
}
