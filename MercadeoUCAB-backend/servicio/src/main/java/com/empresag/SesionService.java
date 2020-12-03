package com.empresag;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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

//        try {

            UsuarioEntity usuario = new UsuarioEntity();
            usuario.setEmail(usuarioDto.getEmail());
            usuario.setPassword(usuarioDto.getPassword());
            usuario.setEstado(usuarioDto.getEstado());

            RolEntity rolEntity = new RolEntity(1);
            usuario.setFk_Rol(rolEntity);

            PersonaEntity persona = new PersonaEntity();
            persona.setDocumentoIdentidad(usuarioDto.getFkPersona().getDocumentoIdentidad());
            persona.setPrimerNombre(usuarioDto.getFkPersona().getPrimerNombre());
            persona.setSegundoNombre(usuarioDto.getFkPersona().getSegundoNombre());
            persona.setPrimerApellido(usuarioDto.getFkPersona().getPrimerApellido());
            persona.setSegundoApellido(usuarioDto.getFkPersona().getSegundoApellido());
            persona.setFechaNacimiento(usuarioDto.getFkPersona().getFechaNacimiento());

            DaoGenero daoGenero = new DaoGenero();
            GeneroEntity generoPersona = daoGenero.find(usuarioDto.getFkPersona().getFkGenero().get_id(), GeneroEntity.class);

            persona.setFkGenero(generoPersona);

            DaoEdoCivil daoEdoCivil = new DaoEdoCivil();
            EdoCivilEntity edoCivilPersona = daoEdoCivil.find(usuarioDto.getFkPersona().getFkEdoCivil().get_id(), EdoCivilEntity.class);

            persona.setFkEdoCivil(edoCivilPersona);

            DaoPersona daoPersona = new DaoPersona();
//            PersonaEntity personaResul = daoPersona.insert(persona);


            for (int i = 0; i < usuarioDto.getFkPersona().getHijos().length; i++) {
                PersonaDto hijoDto = usuarioDto.getFkPersona().getHijos()[i];
                PersonaEntity hijo = new PersonaEntity();
                hijo.setDocumentoIdentidad(hijoDto.getDocumentoIdentidad());
                hijo.setPrimerNombre(hijoDto.getPrimerNombre());
                hijo.setSegundoNombre(hijoDto.getSegundoNombre());
                hijo.setPrimerApellido(hijoDto.getPrimerApellido());
                hijo.setSegundoApellido(hijoDto.getSegundoApellido());
                hijo.setFechaNacimiento(hijoDto.getFechaNacimiento());
//                hijo.setFkPersona(personaResul);

                DaoPersona daoHijo = new DaoPersona();
//                daoHijo.insert(hijo);

            }

//            usuario.setFk_Persona(personaResul);


            DaoUsuario daoUsuario = new DaoUsuario();
//            UsuarioEntity resul = daoUsuario.insert(usuario);
//            resultado.set_id(resul.get_id());
//        }
//        catch (IndexDatabaseException e) {
//            e.printStackTrace();
//        }
        return resultado;

    }

}
