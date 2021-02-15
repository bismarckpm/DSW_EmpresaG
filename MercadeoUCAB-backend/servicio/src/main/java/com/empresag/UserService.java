package com.empresag;

import com.empresag.usuario.ComandoConsultarListaUsuarios;
import com.empresag.usuario.ComandoCrearUsuario;
import com.empresag.usuario.ComandoEditarUsuario;
import com.empresag.usuario.ComandoEliminarUsuaio;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/user")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class UserService {

    @POST
    @Path("/add")
    public RespuestaDto<UsuarioDto> addUser ( UsuarioDto usuarioDto ) {

        System.out.println("DATA: " + usuarioDto.toString());
        System.out.println("PERSONA: " + usuarioDto.getFkPersona().toString());

        UsuarioDto resultado;
        RespuestaDto<UsuarioDto> respuesta = new RespuestaDto<>();

        try{

            ComandoCrearUsuario crearUsuario = new ComandoCrearUsuario( usuarioDto );
            crearUsuario.execute();

            UsuarioEntity resul =  crearUsuario.getResult();

            resultado = UsuarioMapper.mapEntityToDto( resul );

            respuesta.setCodigo(0);
            respuesta.setEstado( "OK" );
            respuesta.setObjeto( resultado );

            DirectorioActivo ldap = new DirectorioActivo();
            ldap.addEntryToLdap(usuarioDto);

        }
        catch (Exception e) {
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado( "ERROR" );
            respuesta.setMensaje( e.getMessage() );
            respuesta.setMensajesoporte( e.getLocalizedMessage() );
        }

        return respuesta;

    }

    @GET
    @Path("/{id}")
    public RespuestaDto<UsuarioDto> getUser (@PathParam("id") long id){

        UsuarioDto resultado;
        RespuestaDto<UsuarioDto> respuesta = new RespuestaDto<>();

        try {

            DaoUsuario daoUsuario = FabricaDao.crearDaoUsuario();
            UsuarioEntity usuario = daoUsuario.find(id, UsuarioEntity.class);
//
//            resultado.set_id(usuario.get_id());
//            resultado.setEmail(usuario.getEmail());
//            resultado.setEstado(usuario.getEstado());
//
////          Busqueda de rol de Persona
//            DaoRol daoRol = new DaoRol();
//            RolEntity rol = daoRol.find(usuario.getFk_Rol().get_id(), RolEntity.class);
//            RolDto userRol = new RolDto();
//            userRol.set_id(rol.get_id());
//            userRol.setNombre(rol.getNombre());
//
//            resultado.setFkRol(userRol);
//
//            if (usuario.getFk_Rol().get_id() == 4)
//            {
//
////          Busqueda de datos Persona
//                DaoPersona daoPersona = new DaoPersona();
//                PersonaEntity persona = daoPersona.find(usuario.getFk_Persona().get_id(), PersonaEntity.class);
//                PersonaDto usuarioPersona = new PersonaDto();
//                usuarioPersona.set_id(persona.get_id());
//                usuarioPersona.setDocumentoIdentidad(persona.getDocumentoIdentidad());
//                usuarioPersona.setPrimerNombre(persona.getPrimerNombre());
//                usuarioPersona.setSegundoNombre(persona.getSegundoNombre());
//                usuarioPersona.setPrimerApellido(persona.getPrimerApellido());
//                usuarioPersona.setSegundoApellido(persona.getSegundoApellido());
//                usuarioPersona.setFechaNacimiento(persona.getFechaNacimiento());
//                usuarioPersona.setNumero_personas_encasa(persona.getNumero_personas_encasa());
//
////          Busqueda de disponibilidad Inicial y Final de Persona
//                DaoDisponibilidad daoDisponibilidad = new DaoDisponibilidad();
//                DisponibilidadEntity dispoInicial = daoDisponibilidad
//                        .find(persona.getFkDisponibilidadInicial().get_id(), DisponibilidadEntity.class);
//                DisponibilidadEntity dispoFinal = daoDisponibilidad
//                        .find(persona.getFkDisponibilidadFinal().get_id(), DisponibilidadEntity.class);
//                DisponibilidadDto dispoInicialDto = new DisponibilidadDto();
//                DisponibilidadDto dispoFinalDto = new DisponibilidadDto();
//                dispoInicialDto.setHora(dispoInicial.getHora());
//                dispoInicialDto.set_id(dispoInicial.get_id());
//                dispoFinalDto.setHora(dispoFinal.getHora());
//                dispoFinalDto.set_id(dispoFinal.get_id());
//
//                usuarioPersona.setId_horario_inicial(dispoInicialDto);
//                usuarioPersona.setId_horario_final(dispoFinalDto);
//
////          Busqueda de genero de Persona
//                DaoGenero daoGenero = new DaoGenero();
//                GeneroEntity generoPersona = daoGenero.find(persona.getFkGenero().get_id(), GeneroEntity.class);
//                GeneroDto personaGenero = new GeneroDto();
//                personaGenero.setNombre(generoPersona.getNombre());
//                personaGenero.set_id(generoPersona.get_id());
//
//                usuarioPersona.setFkGenero(personaGenero);
//
////          Busqueda de EdoCivil de Persona
//                DaoEdoCivil daoEdoCivil = new DaoEdoCivil();
//                EdoCivilEntity edoCivilEntity = daoEdoCivil.find(persona.getFkEdoCivil().get_id(), EdoCivilEntity.class);
//                EdoCivilDto personaEdoCivil = new EdoCivilDto();
//                personaEdoCivil.setNombre(edoCivilEntity.getNombre());
//                personaEdoCivil.set_id(edoCivilEntity.get_id());
//
//                usuarioPersona.setFkEdoCivil(personaEdoCivil);
//
////          Busqueda de Hijos
//                DaoPersona daoHijo = new DaoPersona();
//                List<PersonaEntity> listaHijos = daoHijo.findSons(persona.get_id());
//
//                PersonaDto[] hijos = new PersonaDto[listaHijos.size()];
//
//                for (int i = 0; i < listaHijos.size(); i++) {
//                    PersonaEntity hijo = listaHijos.get(i);
//                    PersonaDto hijoDto = new PersonaDto();
//                    hijoDto.set_id(hijo.get_id());
//                    hijoDto.setPrimerNombre(hijo.getPrimerNombre());
//                    hijoDto.setPrimerApellido(hijo.getPrimerApellido());
//                    hijoDto.setFechaNacimiento(hijo.getFechaNacimiento());
//
//                    DaoGenero daoGeneroHijo = new DaoGenero();
//                    GeneroEntity generoHijo = daoGeneroHijo.find(hijo.getFkGenero().get_id(), GeneroEntity.class);
//                    GeneroDto genHijo = new GeneroDto();
//
//                    genHijo.set_id(generoHijo.get_id());
//                    genHijo.setNombre(generoHijo.getNombre());
//                    hijoDto.setFkGenero(genHijo);
//
//                    hijos[i] = hijoDto;
//                }
//
//                usuarioPersona.setHijos(hijos);
//
////          Busqueda de lugar de Persona
//                DaoLugar daoLugar = new DaoLugar();
//                LugarEntity lugarPersona = daoLugar.find(persona.getFkLugar().get_id(), LugarEntity.class);
//                LugarService lugarService = new LugarService();
//                LugarDto usuarioLugar = lugarService.getSuperior(lugarPersona.get_id());
//                usuarioPersona.setFkLugar(usuarioLugar);
//
////          Busqueda de telefono de Persona
//                DaoTelefono daoTelefonoPersona = new DaoTelefono();
//                List<TelefonoEntity> telefonoPersona = daoTelefonoPersona
//                        .findTelefono(usuario.getFk_Persona().get_id());
//                if (telefonoPersona.size()>0) {
//                    usuarioPersona.setTelefono(telefonoPersona.get(0).getNumero());
//                }
//
//
////          Busqueda de ocupacion de Persona
//                DaoPersonaOcupacion daoPersonaOcupacion = new DaoPersonaOcupacion();
//                usuarioPersona.setOcupacion(daoPersonaOcupacion
//                        .findOcupacion(usuario.getFk_Persona().get_id()).get(0).get_id());
//
////          Busqueda de nivel academico de Persona
//                DaoPersonaNvlacademico daoPersonaNvlacademico = new DaoPersonaNvlacademico();
//                usuarioPersona.setId_nivel_academico(daoPersonaNvlacademico
//                        .findNivAcademico(usuario.getFk_Persona().get_id()).get(0).get_id());
//
//
////          Busqueda de dispositivos de Persona
//                DaoDispositivo daoDispositivo = new DaoDispositivo();
//                List<DispositivoEntity> listaDispositivos = daoDispositivo
//                        .findDispositivos(persona.get_id());
//
//                int[] listaDis = new int[listaDispositivos.size()];
//
//                for (int i = 0; i < listaDispositivos.size(); i++) {
//                    listaDis[i] = (int) listaDispositivos.get(i).get_id();
//                }
//
//                usuarioPersona.setDispositivos(listaDis);
//
//                resultado.setFkPersona(usuarioPersona);
//            }
//            else {
//                resultado.setFkPersona(new PersonaDto());
//            }

            resultado = UsuarioMapper.mapEntityToDto(usuario);

            respuesta.setCodigo(0);
            respuesta.setEstado( "OK" );
            respuesta.setObjeto( resultado );

        }
        catch (IndexDatabaseException e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado( "ERROR" );
            respuesta.setMensaje( e.getMessage() );
            respuesta.setMensajesoporte( e.getLocalizedMessage() );
        }
        catch (NullPointerException e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado( "ERROR" );
            respuesta.setMensaje( "Usuario no encontrado" );
        }

        return respuesta;

    }

    @GET
    @Path("/")
    public RespuestaDto<List<UsuarioDto>> getUser(){


        RespuestaDto<List<UsuarioDto>> respuestaDto = new RespuestaDto<>();

        try {

            ComandoConsultarListaUsuarios consultarLista = new ComandoConsultarListaUsuarios();
            consultarLista.execute();

            List<UsuarioDto> res = consultarLista.getResult();


            respuestaDto.setCodigo(0);
            respuestaDto.setEstado( "OK" );
            respuestaDto.setObjeto( res );

        }
        catch (IndexDatabaseException e){
            e.printStackTrace();
            respuestaDto.setCodigo(-1);
            respuestaDto.setEstado( "ERROR" );
            respuestaDto.setMensaje( e.getMessage() );
            respuestaDto.setMensajesoporte( e.getLocalizedMessage() );
        }
        catch (NullPointerException e){
            e.printStackTrace();
            respuestaDto.setCodigo(-1);
            respuestaDto.setEstado( "ERROR" );
            respuestaDto.setMensaje( e.getMessage() );
            respuestaDto.setMensajesoporte( e.getLocalizedMessage() );
        } catch (Exception e) {
            e.printStackTrace();
            respuestaDto.setCodigo(-1);
            respuestaDto.setEstado( "ERROR" );
            respuestaDto.setMensaje( e.getMessage() );
            respuestaDto.setMensajesoporte( e.getLocalizedMessage() );
        }

//        System.out.println("RESPUESTA: "+resultados.toString());
        return respuestaDto;

    }

    @DELETE
    @Path("/delete/{id}")
    public RespuestaDto<Boolean> deleteUser(@PathParam("id") long id){

        ComandoEliminarUsuaio eliminar = new ComandoEliminarUsuaio(id);
        RespuestaDto<Boolean> respuesta = new RespuestaDto<>();

        try {

            eliminar.execute();

            if (eliminar.getResult()){
                respuesta.setCodigo(0);
                respuesta.setEstado( "OK" );
                respuesta.setMensaje( "Usuario eliminado" );

            }else{
                respuesta.setCodigo(-1);
                respuesta.setEstado( "ERROR" );
                respuesta.setMensaje( "Usuario no eliminado" );
            }

        } catch (Exception e) {
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado( "ERROR" );
            respuesta.setMensaje( "Usuario no eliminado" );
        }
        return respuesta;

    }

    @PUT
    @Path("/edit/{id}")
    public RespuestaDto<Boolean> editUser(@PathParam("id") long id, UsuarioDto usuarioDto){

        ComandoEditarUsuario editar = new ComandoEditarUsuario(id, usuarioDto);
        RespuestaDto<Boolean> respuesta = new RespuestaDto<>();

        try {

            editar.execute();

            if (editar.getResult()){
                respuesta.setCodigo(0);
                respuesta.setEstado( "OK" );
                respuesta.setMensaje( "Usuario editado" );
            }else{
                respuesta.setCodigo(-1);
                respuesta.setEstado( "ERROR" );
                respuesta.setMensaje( "Usuario no editado" );
            }

        } catch (Exception e) {
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado( "ERROR" );
            respuesta.setMensaje( "Usuario no editado" );
        }
        return respuesta;

    }



}
