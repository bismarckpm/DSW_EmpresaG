package com.empresag.usuario;

import com.empresag.*;

import javax.ws.rs.core.Response;

public class ComandoEliminarUsuaio extends ComandoBase {

    private long id = 0;

    private boolean eliminado = false;

    public ComandoEliminarUsuaio(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws Exception {


        DaoUsuario usuarioDao = FabricaDao.crearDaoUsuario();
        UsuarioEntity userRemove = usuarioDao.find(id, UsuarioEntity.class);

        if (userRemove != null){

            usuarioDao.delete(userRemove);
            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setEmail(userRemove.getEmail());
            DirectorioActivo ldap = new DirectorioActivo();
            ldap.deleteEntry(usuarioDto);
            eliminado = true;

        }

    }

    @Override
    public Boolean getResult() {
        return eliminado;
    }
}
