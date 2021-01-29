package com.empresag.usuario;

import com.empresag.*;

import java.util.ArrayList;
import java.util.List;

public class ComandoConsultarListaUsuarios extends ComandoBase {


    private List<UsuarioDto> usuarioDtoList;

    public ComandoConsultarListaUsuarios() {
        usuarioDtoList = new ArrayList<>();
    }

    @Override
    public void execute() throws Exception {

        DaoUsuario daoUsuario = FabricaDao.crearDaoUsuario();
        List<UsuarioEntity> usuarios = daoUsuario.findAll( UsuarioEntity.class);

        for (int u = 0; u < usuarios.size(); u++) {

            UsuarioDto resultado = UsuarioMapper.mapEntityToDto(usuarios.get(u));
            usuarioDtoList.add(resultado);
        }

    }

    @Override
    public List<UsuarioDto> getResult() {
        return usuarioDtoList;
    }


}
