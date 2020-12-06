package com.empresag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/user")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )

public class UserService {

    @POST
    @Path("/add")
    public UsuarioDto addUser ( UsuarioDto usuarioDto ) {

        System.out.println("DATA: " + usuarioDto.toString());
        System.out.println("PERSONA: " + usuarioDto.getFkPersona().toString());

        UsuarioDto resultado = new UsuarioDto();

        try{

//            INSERTAR USUARIO
            UsuarioEntity usuario = new UsuarioEntity();
            usuario.setEmail(usuarioDto.getEmail());
            usuario.setPassword(usuarioDto.getPassword());
            usuario.setEstado(usuarioDto.getEstado());

//            INSERTAR ROL
            RolEntity rolEntity = new RolEntity();
            DaoRol daoRol = new DaoRol();
            rolEntity = daoRol.find(usuarioDto.getFkRol().get_id(), RolEntity.class);
            usuario.setFk_Rol(rolEntity);

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

        return resultado;

    }

    @GET
    @Path("/{id}")
    public UsuarioDto getUser (@PathParam("id") long id){

        UsuarioDto resultado = new UsuarioDto();

        try {

            DaoUsuario daoUsuario = new DaoUsuario();
            UsuarioEntity usuario = daoUsuario.find(id, UsuarioEntity.class);

            resultado.set_id(usuario.get_id());
            resultado.setEmail(usuario.getEmail());
            resultado.setEstado(usuario.getEstado());

//          Busqueda de rol de Persona
            DaoRol daoRol = new DaoRol();
            RolEntity rol = daoRol.find(usuario.getFk_Rol().get_id(), RolEntity.class);
            RolDto userRol = new RolDto();
            userRol.set_id(rol.get_id());
            userRol.setNombre(rol.getNombre());

            resultado.setFkRol(userRol);

//          Busqueda de datos Persona
            DaoPersona daoPersona = new DaoPersona();
            PersonaEntity persona = daoPersona.find(usuario.getFk_Persona().get_id(), PersonaEntity.class);
            PersonaDto usuarioPersona = new PersonaDto();
            usuarioPersona.setDocumentoIdentidad(persona.getDocumentoIdentidad());
            usuarioPersona.setPrimerNombre(persona.getPrimerNombre());
            usuarioPersona.setSegundoNombre(persona.getSegundoNombre());
            usuarioPersona.setPrimerApellido(persona.getPrimerApellido());
            usuarioPersona.setSegundoApellido(persona.getSegundoApellido());
            usuarioPersona.setFechaNacimiento(persona.getFechaNacimiento());
            usuarioPersona.setNumero_personas_encasa(persona.getNumero_personas_encasa());

//          Busqueda de disponibilidad Inicial y Final de Persona
            DaoDisponibilidad daoDisponibilidad = new DaoDisponibilidad();
            DisponibilidadEntity dispoInicial = daoDisponibilidad
                    .find(persona.getFkDisponibilidadInicial().get_id(), DisponibilidadEntity.class);
            DisponibilidadEntity dispoFinal = daoDisponibilidad
                    .find(persona.getFkDisponibilidadFinal().get_id(), DisponibilidadEntity.class);
            DisponibilidadDto dispoInicialDto = new DisponibilidadDto();
            DisponibilidadDto dispoFinalDto = new DisponibilidadDto();
            dispoInicialDto.setHora(dispoInicial.getHora());
            dispoInicialDto.set_id(dispoInicial.get_id());
            dispoFinalDto.setHora(dispoFinal.getHora());
            dispoFinalDto.set_id(dispoFinal.get_id());

            usuarioPersona.setId_horario_inicial(dispoInicialDto);
            usuarioPersona.setId_horario_final(dispoFinalDto);

//          Busqueda de genero de Persona
            DaoGenero daoGenero = new DaoGenero();
            GeneroEntity generoPersona = daoGenero.find(persona.getFkGenero().get_id(), GeneroEntity.class);
            GeneroDto personaGenero = new GeneroDto();
            personaGenero.setNombre(generoPersona.getNombre());
            personaGenero.set_id(generoPersona.get_id());

            usuarioPersona.setFkGenero(personaGenero);

//          Busqueda de EdoCivil de Persona
            DaoEdoCivil daoEdoCivil = new DaoEdoCivil();
            EdoCivilEntity edoCivilEntity = daoEdoCivil.find(persona.getFkEdoCivil().get_id(), EdoCivilEntity.class);
            EdoCivilDto personaEdoCivil = new EdoCivilDto();
            personaEdoCivil.setNombre(edoCivilEntity.getNombre());
            personaEdoCivil.set_id(edoCivilEntity.get_id());

            usuarioPersona.setFkEdoCivil(personaEdoCivil);

//          Busqueda de Hijos
            DaoPersona daoHijo = new DaoPersona();
            List<PersonaEntity> listaHijos = daoHijo.findSons(persona.get_id());

            PersonaDto[] hijos = new PersonaDto[listaHijos.size()];

            for (int i = 0; i < listaHijos.size(); i++) {
                PersonaEntity hijo = listaHijos.get(i);
                PersonaDto hijoDto = new PersonaDto();
                hijoDto.set_id(hijo.get_id());
                hijoDto.setPrimerNombre(hijo.getPrimerNombre());
                hijoDto.setPrimerApellido(hijo.getPrimerApellido());
                hijoDto.setFechaNacimiento(hijo.getFechaNacimiento());

                DaoGenero daoGeneroHijo = new DaoGenero();
                GeneroEntity generoHijo = daoGeneroHijo.find(hijo.getFkGenero().get_id(), GeneroEntity.class);
                GeneroDto genHijo = new GeneroDto();

                genHijo.set_id(generoHijo.get_id());
                genHijo.setNombre(generoHijo.getNombre());
                hijoDto.setFkGenero(genHijo);

                hijos[i] = hijoDto;
            }

            usuarioPersona.setHijos(hijos);

//          Busqueda de lugar de Persona
            DaoLugar daoLugar = new DaoLugar();
            LugarEntity lugarPersona = daoLugar.find(persona.getFkLugar().get_id(), LugarEntity.class);
            LugarService lugarService = new LugarService();
            LugarDto usuarioLugar = lugarService.getSuperior(lugarPersona.get_id());
            usuarioPersona.setFkLugar(usuarioLugar);

//          Busqueda de telefono de Persona
            DaoTelefono daoTelefonoPersona = new DaoTelefono();
            List<TelefonoEntity> telefonoPersona = daoTelefonoPersona
                    .findTelefono(usuario.getFk_Persona().get_id());
            if (telefonoPersona.size()>0) {
                usuarioPersona.setTelefono(telefonoPersona.get(0).getNumero());
            }


//          Busqueda de ocupacion de Persona
            DaoPersonaOcupacion daoPersonaOcupacion = new DaoPersonaOcupacion();
            usuarioPersona.setOcupacion(daoPersonaOcupacion
                    .findOcupacion(usuario.getFk_Persona().get_id()).get(0).get_id());

//          Busqueda de nivel academico de Persona
            DaoPersonaNvlacademico daoPersonaNvlacademico = new DaoPersonaNvlacademico();
            usuarioPersona.setId_nivel_academico(daoPersonaNvlacademico
                    .findNivAcademico(usuario.getFk_Persona().get_id()).get(0).get_id());


//          Busqueda de dispositivos de Persona
            DaoDispositivo daoDispositivo = new DaoDispositivo();
            List<DispositivoEntity> listaDispositivos = daoDispositivo
                    .findDispositivos(persona.get_id());

            int[] listaDis = new int[listaDispositivos.size()];

            for (int i = 0; i < listaDispositivos.size(); i++) {
                listaDis[i] = (int) listaDispositivos.get(i).get_id();
            }

            usuarioPersona.setDispositivos(listaDis);

            resultado.setFkPersona(usuarioPersona);

        }
        catch (IndexDatabaseException e){
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }

        return resultado;

    }

    @GET
    @Path("/")
    public List<UsuarioDto> getUser (){

        List<UsuarioDto> resultados = new ArrayList<>();

        try {

            DaoUsuario daoUsuario = new DaoUsuario();
            List<UsuarioEntity> usuarios = daoUsuario.findAll( UsuarioEntity.class);

            for (int u = 0; u < usuarios.size(); u++) {

                UsuarioDto resultado = new UsuarioDto();
                UsuarioEntity usuario = usuarios.get(u);

                resultado.set_id(usuario.get_id());
                resultado.setEmail(usuario.getEmail());
                resultado.setEstado(usuario.getEstado());

//          Busqueda de rol de Persona
                DaoRol daoRol = new DaoRol();
                RolEntity rol = daoRol.find(usuario.getFk_Rol().get_id(), RolEntity.class);
                RolDto userRol = new RolDto();
                userRol.set_id(rol.get_id());
                userRol.setNombre(rol.getNombre());

                resultado.setFkRol(userRol);

//          Busqueda de datos Persona
                DaoPersona daoPersona = new DaoPersona();
                PersonaEntity persona = daoPersona.find(usuario.getFk_Persona().get_id(), PersonaEntity.class);
                PersonaDto usuarioPersona = new PersonaDto();
                usuarioPersona.setDocumentoIdentidad(persona.getDocumentoIdentidad());
                usuarioPersona.setPrimerNombre(persona.getPrimerNombre());
                usuarioPersona.setSegundoNombre(persona.getSegundoNombre());
                usuarioPersona.setPrimerApellido(persona.getPrimerApellido());
                usuarioPersona.setSegundoApellido(persona.getSegundoApellido());
                usuarioPersona.setFechaNacimiento(persona.getFechaNacimiento());
                usuarioPersona.setNumero_personas_encasa(persona.getNumero_personas_encasa());

//          Busqueda de disponibilidad Inicial y Final de Persona
                DaoDisponibilidad daoDisponibilidad = new DaoDisponibilidad();
                DisponibilidadEntity dispoInicial = daoDisponibilidad
                        .find(persona.getFkDisponibilidadInicial().get_id(), DisponibilidadEntity.class);
                DisponibilidadEntity dispoFinal = daoDisponibilidad
                        .find(persona.getFkDisponibilidadFinal().get_id(), DisponibilidadEntity.class);
                DisponibilidadDto dispoInicialDto = new DisponibilidadDto();
                DisponibilidadDto dispoFinalDto = new DisponibilidadDto();
                dispoInicialDto.setHora(dispoInicial.getHora());
                dispoInicialDto.set_id(dispoInicial.get_id());
                dispoFinalDto.setHora(dispoFinal.getHora());
                dispoFinalDto.set_id(dispoFinal.get_id());

                usuarioPersona.setId_horario_inicial(dispoInicialDto);
                usuarioPersona.setId_horario_final(dispoFinalDto);

//          Busqueda de genero de Persona
                DaoGenero daoGenero = new DaoGenero();
                GeneroEntity generoPersona = daoGenero.find(persona.getFkGenero().get_id(), GeneroEntity.class);
                GeneroDto personaGenero = new GeneroDto();
                personaGenero.setNombre(generoPersona.getNombre());
                personaGenero.set_id(generoPersona.get_id());

                usuarioPersona.setFkGenero(personaGenero);

//          Busqueda de EdoCivil de Persona
                DaoEdoCivil daoEdoCivil = new DaoEdoCivil();
                EdoCivilEntity edoCivilEntity = daoEdoCivil.find(persona.getFkEdoCivil().get_id(), EdoCivilEntity.class);
                EdoCivilDto personaEdoCivil = new EdoCivilDto();
                personaEdoCivil.setNombre(edoCivilEntity.getNombre());
                personaEdoCivil.set_id(edoCivilEntity.get_id());

                usuarioPersona.setFkEdoCivil(personaEdoCivil);

//          Busqueda de Hijos
                DaoPersona daoHijo = new DaoPersona();
                List<PersonaEntity> listaHijos = daoHijo.findSons(persona.get_id());

                PersonaDto[] hijos = new PersonaDto[listaHijos.size()];

                for (int i = 0; i < listaHijos.size(); i++) {
                    PersonaEntity hijo = listaHijos.get(i);
                    PersonaDto hijoDto = new PersonaDto();
                    hijoDto.set_id(hijo.get_id());
                    hijoDto.setPrimerNombre(hijo.getPrimerNombre());
                    hijoDto.setPrimerApellido(hijo.getPrimerApellido());
                    hijoDto.setFechaNacimiento(hijo.getFechaNacimiento());

                    DaoGenero daoGeneroHijo = new DaoGenero();
                    GeneroEntity generoHijo = daoGeneroHijo.find(hijo.getFkGenero().get_id(), GeneroEntity.class);
                    GeneroDto genHijo = new GeneroDto();

                    genHijo.set_id(generoHijo.get_id());
                    genHijo.setNombre(generoHijo.getNombre());
                    hijoDto.setFkGenero(genHijo);

                    hijos[i] = hijoDto;
                }

                usuarioPersona.setHijos(hijos);

//          Busqueda de lugar de Persona
                DaoLugar daoLugar = new DaoLugar();
                LugarEntity lugarPersona = daoLugar.find(persona.getFkLugar().get_id(), LugarEntity.class);
                LugarService lugarService = new LugarService();
                LugarDto usuarioLugar = lugarService.getSuperior(lugarPersona.get_id());
                usuarioPersona.setFkLugar(usuarioLugar);

//          Busqueda de telefono de Persona
                DaoTelefono daoTelefonoPersona = new DaoTelefono();
                List<TelefonoEntity> telefonoPersona = daoTelefonoPersona
                        .findTelefono(usuario.getFk_Persona().get_id());
                if (telefonoPersona.size() > 0) {
                    usuarioPersona.setTelefono(telefonoPersona.get(0).getNumero());
                }


//          Busqueda de ocupacion de Persona
                DaoPersonaOcupacion daoPersonaOcupacion = new DaoPersonaOcupacion();
                usuarioPersona.setOcupacion(daoPersonaOcupacion
                        .findOcupacion(usuario.getFk_Persona().get_id()).get(0).get_id());

//          Busqueda de nivel academico de Persona
                DaoPersonaNvlacademico daoPersonaNvlacademico = new DaoPersonaNvlacademico();
                usuarioPersona.setId_nivel_academico(daoPersonaNvlacademico
                        .findNivAcademico(usuario.getFk_Persona().get_id()).get(0).get_id());


//          Busqueda de dispositivos de Persona
                DaoDispositivo daoDispositivo = new DaoDispositivo();
                List<DispositivoEntity> listaDispositivos = daoDispositivo
                        .findDispositivos(persona.get_id());

                int[] listaDis = new int[listaDispositivos.size()];

                for (int i = 0; i < listaDispositivos.size(); i++) {
                    listaDis[i] = (int) listaDispositivos.get(i).get_id();
                }

                usuarioPersona.setDispositivos(listaDis);

                resultado.setFkPersona(usuarioPersona);

                resultados.add(resultado);
            }
        }
        catch (IndexDatabaseException e){
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }

        return resultados;

    }

}
