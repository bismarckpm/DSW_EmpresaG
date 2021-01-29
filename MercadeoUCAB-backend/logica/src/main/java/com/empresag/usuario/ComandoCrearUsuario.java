package com.empresag.usuario;

import com.empresag.*;
import org.eclipse.persistence.exceptions.DatabaseException;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class ComandoCrearUsuario extends ComandoBase {

    private UsuarioEntity usuarioEntity;
    private UsuarioDto usuarioDto;

    public ComandoCrearUsuario(UsuarioDto _usuario) {
        usuarioDto = _usuario;
        usuarioEntity = UsuarioMapper.mapDtoToEntity( _usuario );
    }

    @Override
    public void execute() throws Exception {

        DaoUsuario dao = FabricaDao.crearDaoUsuario();

        try {
            usuarioEntity = dao.insert(usuarioEntity);

//            INSERTAR HIJOS
            for (int i = 0; i < usuarioDto.getFkPersona().getHijos().length; i++) {
                PersonaEntity hijo = PersonaMapper
                        .mapDtoToEntity(usuarioDto.getFkPersona().getHijos()[i], usuarioEntity.getFk_Persona());
                DaoPersona daoHijo = FabricaDao.crearDaoPersona();
                daoHijo.insert(hijo);
            }

//            INSERTAR DISPOSITIVOS USADOS
            for (int i = 0; i < usuarioDto.getFkPersona().getDispositivos().length; i++) {
                PersonaDispositivoEntity personaDispositivo = PersonaDispositivoMapper
                        .mapDtoToEntity(usuarioDto.getFkPersona().getDispositivos()[i], usuarioEntity.getFk_Persona());
                DaoPersonaDispositivo daoPersonaDispositivo = FabricaDao.crearDaoPersonaDispositivo();
                daoPersonaDispositivo.insert(personaDispositivo);
            }

//            INSERTAR NUMERO DE TELEFONO
            TelefonoEntity telefonoPersona = TelefonoMapper
                    .mapStringToEntity(usuarioDto.getFkPersona().getTelefono(), usuarioEntity.getFk_Persona());
            DaoTelefono daoTelefono = FabricaDao.crearDaoTelefono();
            daoTelefono.insert(telefonoPersona);

//            INSERTAR PERSONA OCUPACION
            PersonaOcupacionEntity personaOcupacion = PersonaOcupacionMapper
                    .mapIntToEntity(usuarioDto.getFkPersona().getOcupacion(), usuarioEntity.getFk_Persona());
            DaoPersonaOcupacion daoPersonaOcupacion = FabricaDao.crearDaoPersonaOcupacion();
            daoPersonaOcupacion.insert(personaOcupacion);

//            INSERTAR NIVEL ACADEMICO
            PersonaNvlacademicoEntity personaNvlacademico = PersonaNvlacademicoMapper
                    .mapIntToEntity(usuarioDto.getFkPersona().getId_nivel_academico(), usuarioEntity.getFk_Persona());
            DaoPersonaNvlacademico daoPersonaNvlacademico = FabricaDao.crearDaoPersonaNvlacademico();
            daoPersonaNvlacademico.insert(personaNvlacademico);

        }
        catch (DatabaseException e){

            e.printStackTrace();
            dao.delete(usuarioEntity);

            throw new UsuarioRepetidoException();
        }
        catch (NullPointerException e){

            e.printStackTrace();
            dao.delete(usuarioEntity);

//            TODO - Aca podria crear una excepcion nueva para indicar que no falta algun campo
            throw new Exception("Error en la data recibida.");

        }
    }

    @Override
    public UsuarioEntity getResult() {
        return usuarioEntity;
    }
}
