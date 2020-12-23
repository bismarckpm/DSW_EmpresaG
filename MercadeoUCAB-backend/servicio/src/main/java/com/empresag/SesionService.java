package com.empresag;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/sesion")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class SesionService {

    @POST
    @Path("/register")
    public UsuarioDto addRegistro ( UsuarioDto usuarioDto ) {

        System.out.println("DATA: " + usuarioDto.toString());
        System.out.println("PERSONA: " + usuarioDto.getFkPersona().toString());

        UsuarioDto resultado = new UsuarioDto();

        try {

//            INSERTAR USUARIO
            UsuarioEntity usuario = new UsuarioEntity();
            usuario.setEmail(usuarioDto.getEmail());
            usuario.setPassword(usuarioDto.getPassword());
            usuario.setEstado(usuarioDto.getEstado());

//            INSERTAR ROL
            DaoRol daoRol = new DaoRol();
            List<RolEntity> roles = daoRol.getEncuestadoRol();
            usuario.setFk_Rol(roles.get(0));

            PersonaEntity persona = new PersonaEntity();
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

//            INSERTAR DISPONIBILIDAD
            persona.setFkDisponibilidadInicial(disponibilidadEntity_i);
            persona.setFkDisponibilidadFinal(disponibilidadEntity_f);

//            INSERTAR PERSONA
            DaoPersona daoPersona = new DaoPersona();
            PersonaEntity personaResul = daoPersona.insert(persona);

            usuario.setFk_Persona(personaResul);
            DaoUsuario daoUsuario = new DaoUsuario();
            UsuarioEntity resul = daoUsuario.insert(usuario);

//            INSERTAR HIJOS
            for (int i = 0; i < usuarioDto.getFkPersona().getHijos().length; i++) {
                PersonaDto hijoDto = usuarioDto.getFkPersona().getHijos()[i];
                PersonaEntity hijo = new PersonaEntity();
                hijo.setDocumentoIdentidad(hijoDto.getDocumentoIdentidad());
                hijo.setPrimerNombre(hijoDto.getPrimerNombre());
                hijo.setSegundoNombre(hijoDto.getSegundoNombre());
                hijo.setPrimerApellido(hijoDto.getPrimerApellido());
                hijo.setSegundoApellido(hijoDto.getSegundoApellido());
                hijo.setFechaNacimiento(hijoDto.getFechaNacimiento());
                hijo.setFkPersona(personaResul);

                GeneroEntity generoHijo = daoGenero.find(hijoDto.getFkGenero().get_id(), GeneroEntity.class);
                hijo.setFkGenero(generoHijo);

                DaoPersona daoHijo = new DaoPersona();
                daoHijo.insert(hijo);

            }

//            INSERTAR DISPOSITIVOS USADOS
            for (int i = 0; i < usuarioDto.getFkPersona().getDispositivos().length; i++) {
                DaoDispositivo daoDispositivo = new DaoDispositivo();

                DispositivoEntity dispositivo = daoDispositivo.find((long) usuarioDto.getFkPersona().getDispositivos()[i], DispositivoEntity.class);

                PersonaDispositivoEntity personaDispositivo = new PersonaDispositivoEntity();
                personaDispositivo.setFkPersona(personaResul);
                personaDispositivo.setFkDispositivo(dispositivo);

                DaoPersonaDispositivo daoPersonaDispositivo = new DaoPersonaDispositivo();
                daoPersonaDispositivo.insert(personaDispositivo);

            }

//            INSERTAR NUMERO DE TELEFONO
            TelefonoEntity telefonoPersona = new TelefonoEntity();
            telefonoPersona.setNumero(usuarioDto.getFkPersona().getTelefono());
            telefonoPersona.setFkPersona(personaResul);
            DaoTelefono daoTelefono = new DaoTelefono();
            daoTelefono.insert(telefonoPersona);

//            OBTENER OCUPACION
            DaoOcupacion daoOcupacion = new DaoOcupacion();
            OcupacionEntity ocupacion = daoOcupacion.find(usuarioDto.getFkPersona().getOcupacion(), OcupacionEntity.class);

//            INSERTAR PERSONA OCUPACION
            PersonaOcupacionEntity personaOcupacion = new PersonaOcupacionEntity();
            personaOcupacion.setFkOcupacion(ocupacion);
            personaOcupacion.setFkPersona(personaResul);

            DaoPersonaOcupacion daoPersonaOcupacion = new DaoPersonaOcupacion();
            daoPersonaOcupacion.insert(personaOcupacion);


//            OBTENER NIVEL ACADEMICO
            DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
            NivelAcademicoEntity nivelAcademicoPersona = daoNivelAcademico.find(usuarioDto.getFkPersona().getId_nivel_academico(), NivelAcademicoEntity.class);

//            INSERTAR NIVEL ACADEMICO
            PersonaNvlacademicoEntity personaNvlacademico = new PersonaNvlacademicoEntity();
            personaNvlacademico.setFkNivelAcademico(nivelAcademicoPersona);
            personaNvlacademico.setFkPersona(personaResul);

            DaoPersonaNvlacademico daoPersonaNvlacademico = new DaoPersonaNvlacademico();
            daoPersonaNvlacademico.insert(personaNvlacademico);


            resultado.set_id(resul.get_id());
        }
        catch (IndexDatabaseException e) {
            e.printStackTrace();
        }

        DirectorioActivo ldap = new DirectorioActivo();
        ldap.addEntryToLdap(usuarioDto);

        return resultado;

    }

    @POST
    @Path("/validRegister")
    public Response checkRegistro ( UsuarioDto usuarioDto ) {

        DaoUsuario daoUsuario = new DaoUsuario();
        List<UsuarioEntity> usuario = daoUsuario.emailExist(usuarioDto.getEmail());


        if (usuario.size() == 0)
            return Response.status(Response.Status.OK).build();
        else
            return Response.status(Response.Status.FOUND).build();


    }

}
