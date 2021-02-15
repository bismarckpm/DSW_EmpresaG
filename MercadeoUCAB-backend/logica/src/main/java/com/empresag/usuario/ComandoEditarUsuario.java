package com.empresag.usuario;

import com.empresag.*;

import javax.ws.rs.core.Response;
import java.util.List;

public class ComandoEditarUsuario extends ComandoBase {

    private long id;
    private UsuarioDto usuarioDto;
    private boolean editado = false;

    public ComandoEditarUsuario(long id, UsuarioDto usuarioDto) {
        this.id = id;
        this.usuarioDto = usuarioDto;
    }

    @Override
    public void execute() throws Exception {

        DaoUsuario usuarioDao =  FabricaDao.crearDaoUsuario();
        UsuarioEntity usuarioOld = usuarioDao.find(id, UsuarioEntity.class);

        if (usuarioOld != null){

//            UsuarioEntity usuarioUpdated = UsuarioMapper.mapDtoToEntity(usuarioDto);

            usuarioOld.setEstado(usuarioDto.getEstado());
            if (!usuarioDto.getPassword().equals("")) {
                usuarioOld.setPassword(usuarioDto.getPassword());
                DirectorioActivo ldap = new DirectorioActivo();
                ldap.changePassword(usuarioDto);
            }
            else {
                usuarioOld.setPassword(usuarioOld.getPassword());
            }

////            INSERTAR ROL
            RolEntity rolEntity = new RolEntity();
            DaoRol daoRol = new DaoRol();
            rolEntity = daoRol.find(usuarioDto.getFkRol().get_id(), RolEntity.class);
            usuarioOld.setFk_Rol(rolEntity);


            if (usuarioDto.getFkRol().get_id() == 4){

                DaoPersona daoPersona = FabricaDao.crearDaoPersona();
                PersonaEntity persona = daoPersona.find(usuarioDto.getFkPersona().get_id(), PersonaEntity.class);
                persona.setDocumentoIdentidad(usuarioDto.getFkPersona().getDocumentoIdentidad());
                persona.setPrimerNombre(usuarioDto.getFkPersona().getPrimerNombre());
                persona.setSegundoNombre(usuarioDto.getFkPersona().getSegundoNombre());
                persona.setPrimerApellido(usuarioDto.getFkPersona().getPrimerApellido());
                persona.setSegundoApellido(usuarioDto.getFkPersona().getSegundoApellido());
                persona.setFechaNacimiento(usuarioDto.getFkPersona().getFechaNacimiento());
                persona.setNumero_personas_encasa(usuarioDto.getFkPersona().getNumero_personas_encasa());

//            OBTENER GENERO
                DaoGenero daoGenero = new DaoGenero();
                GeneroEntity generoPersona = daoGenero.find(usuarioDto.getFkPersona().getFkGenero().get_id(), GeneroEntity.class);
                persona.setFkGenero(generoPersona);

//            OBTENER EDO CIVIL
                DaoEdoCivil daoEdoCivil = new DaoEdoCivil();
                EdoCivilEntity edoCivilPersona = daoEdoCivil.find(usuarioDto.getFkPersona().getFkEdoCivil().get_id(), EdoCivilEntity.class);
                persona.setFkEdoCivil(edoCivilPersona);

//            OBTENER LUGAR
                DaoLugar daoLugar = new DaoLugar();
                LugarEntity lugarPersona = daoLugar.find(usuarioDto.getFkPersona().getFkLugar().get_id(), LugarEntity.class);
                persona.setFkLugar(lugarPersona);


//            OBTENER DISPONIBILIDAD
                DisponibilidadDto dispo_inicial = usuarioDto.getFkPersona().getId_horario_inicial();
                DisponibilidadDto dispo_final = usuarioDto.getFkPersona().getId_horario_final();

                DaoDisponibilidad daoDisponibilidad = new DaoDisponibilidad();

                DisponibilidadEntity disponibilidadEntity_i = daoDisponibilidad.find(dispo_inicial.get_id(), DisponibilidadEntity.class);
                DisponibilidadEntity disponibilidadEntity_f = daoDisponibilidad.find(dispo_final.get_id(), DisponibilidadEntity.class);

                persona.setFkDisponibilidadInicial(disponibilidadEntity_i);
                persona.setFkDisponibilidadFinal(disponibilidadEntity_f);


//            INSERTAR PERSONA
                daoPersona.update(persona);

                usuarioOld.setFk_Persona(persona);

                usuarioDao.update(usuarioOld);


//                BUSCAR LOS HIJOS DE UN ID DE PERSONA
                List<PersonaEntity> hijos = daoPersona.findSons(usuarioOld.getFk_Persona().get_id());
//                BORRAR TODOS LOS HIJOS DE UN ID DE PERSONA
                for (PersonaEntity hijo : hijos) {
                    daoPersona.delete(hijo);
                }

//            INSERTAR HIJOS
                for (int i = 0; i < usuarioDto.getFkPersona().getHijos().length; i++) {
                        PersonaEntity hijo = PersonaMapper
                                .mapDtoToEntity(usuarioDto.getFkPersona().getHijos()[i], usuarioOld.getFk_Persona());
                        DaoPersona daoHijo = FabricaDao.crearDaoPersona();
                        daoHijo.insert(hijo);
                    }


//                BUSCAR LOS DISPOSITIVOS DE UN ID DE PERSONA
                DaoPersonaDispositivo daoPersonaDispositivo = new DaoPersonaDispositivo();
                List<PersonaDispositivoEntity> dispositivos = daoPersonaDispositivo.findPersonaDispositivos(usuarioOld.getFk_Persona().get_id());
//                BORRAR TODOS LOS DISPOSITIVOS DE UN ID DE PERSONA
                for (PersonaDispositivoEntity device :
                        dispositivos) {
                    daoPersonaDispositivo.delete(device);
                }
//            INSERTAR DISPOSITIVOS USADOS
                for (int i = 0; i < usuarioDto.getFkPersona().getDispositivos().length; i++) {
                    PersonaDispositivoEntity personaDispositivo = PersonaDispositivoMapper
                            .mapDtoToEntity(usuarioDto.getFkPersona().getDispositivos()[i], usuarioOld.getFk_Persona());
//                    DaoPersonaDispositivo daoPersonaDispositivo = FabricaDao.crearDaoPersonaDispositivo();
                    daoPersonaDispositivo.insert(personaDispositivo);
                }

//                BUSCAR LOS NUMERO DE TELEFONO DE UN ID DE PERSONA
                DaoTelefono daoTelefono = FabricaDao.crearDaoTelefono();
                List<TelefonoEntity> telefonos = daoTelefono.findTelefono(usuarioOld.getFk_Persona().get_id());
//                BORRAR TODOS LOS NUMERO DE TELEFONO DE UN ID DE PERSONA
                for (TelefonoEntity phone:
                        telefonos) {
                    daoTelefono.delete(phone);
                }

//            INSERTAR NUMERO DE TELEFONO
                TelefonoEntity telefonoPersona = TelefonoMapper
                        .mapDtoToEntity(usuarioDto.getFkPersona().getTelefono(), usuarioOld.getFk_Persona());
//                DaoTelefono daoTelefono = FabricaDao.crearDaoTelefono();
                daoTelefono.insert(telefonoPersona);

//            OBTENER OCUPACION
//                DaoOcupacion daoOcupacion = new DaoOcupacion();
//                OcupacionEntity ocupacion = daoOcupacion.find(usuarioDto.getFkPersona().getOcupacion(), OcupacionEntity.class);

//                BUSCAR LOS PERSONA OCUPACION DE UN ID DE PERSONA
                DaoPersonaOcupacion daoPersonaOcupacion = FabricaDao.crearDaoPersonaOcupacion();
                List<PersonaOcupacionEntity> ocupaciones = daoPersonaOcupacion.findOcupacionPersona(usuarioOld.getFk_Persona().get_id());
//                BORRAR TODOS LOS PERSONA OCUPACION DE UN ID DE PERSONA
                for (PersonaOcupacionEntity empleo:
                        ocupaciones) {
                    daoPersonaOcupacion.delete(empleo);
                }
//            INSERTAR PERSONA OCUPACION
                PersonaOcupacionEntity personaOcupacion = PersonaOcupacionMapper
                        .mapIntToEntity(usuarioDto.getFkPersona().getOcupacion(), usuarioOld.getFk_Persona());
//                DaoPersonaOcupacion daoPersonaOcupacion = FabricaDao.crearDaoPersonaOcupacion();
                daoPersonaOcupacion.insert(personaOcupacion);


//            OBTENER NIVEL ACADEMICO
//                DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
//                NivelAcademicoEntity nivelAcademicoPersona = daoNivelAcademico.find(usuarioDto.getFkPersona().getId_nivel_academico(), NivelAcademicoEntity.class);

//                BUSCAR LOS NIVEL ACADEMICO DE UN ID DE PERSONA
                DaoPersonaNvlacademico daoPersonaNvlacademico = FabricaDao.crearDaoPersonaNvlacademico();
                List<PersonaNvlacademicoEntity> grados = daoPersonaNvlacademico.findNivAcademicos(usuarioOld.getFk_Persona().get_id());
//                BORRAR TODOS LOS NIVEL ACADEMICO DE UN ID DE PERSONA
                for (PersonaNvlacademicoEntity grado:
                        grados) {
                    daoPersonaNvlacademico.delete(grado);
                }
//            INSERTAR NIVEL ACADEMICO
                PersonaNvlacademicoEntity personaNvlacademico = PersonaNvlacademicoMapper
                        .mapIntToEntity(usuarioDto.getFkPersona().getId_nivel_academico(), usuarioOld.getFk_Persona());
//                DaoPersonaNvlacademico daoPersonaNvlacademico = FabricaDao.crearDaoPersonaNvlacademico();
                daoPersonaNvlacademico.insert(personaNvlacademico);


            }
            else {
                usuarioDao.update(usuarioOld);
            }

            editado = true;

        }


    }

    @Override
    public Boolean getResult() {
        return editado;
    }
}
