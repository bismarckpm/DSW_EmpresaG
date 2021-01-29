package com.empresag.usuario;

import com.empresag.*;

import java.util.ArrayList;
import java.util.List;

public class ComandoConsultarUsuario extends ComandoBase {

    private long id = 0;
    private UsuarioEntity usuarioEntity;


    public ComandoConsultarUsuario(long id) {
        this.id = id;
        usuarioEntity = FabricaEntity.crearUsuarioEntity();
    }

    @Override
    public void execute() throws Exception {

        DaoUsuario daoUsuario = FabricaDao.crearDaoUsuario();
        usuarioEntity = daoUsuario.find(id, UsuarioEntity.class);

    }

    @Override
    public UsuarioEntity getResult() {
            return usuarioEntity;
    }
}
