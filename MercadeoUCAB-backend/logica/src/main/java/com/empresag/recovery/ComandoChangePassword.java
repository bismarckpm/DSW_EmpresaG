package com.empresag.recovery;

import com.empresag.*;

public class ComandoChangePassword extends ComandoBase {

    private String hash;
    private String password;
    private String response;

    public ComandoChangePassword(String hash, String password) {
        this.hash = hash;
        this.password = password;
        this.response = "Clave no cambiada";
    }

    @Override
    public void execute() throws Exception {

        DaoUsuario usuario = FabricaDao.crearDaoUsuario();
        DaoToken token = FabricaDao.crearDaoToken();

        TokenEntity tokenEntity = token.getTokenByHASH(hash,false);

        UsuarioEntity user = usuario.find(tokenEntity.getFkUsuario().get_id(),UsuarioEntity.class);
        user.setPassword(password);

        UsuarioDto dtoUsuario = UsuarioMapper.mapEntityToDto(user);
        dtoUsuario.setPassword(password);

//        DirectorioActivo ldap = new DirectorioActivo();
//        ldap.changePassword(dtoUsuario);

        usuario.update(user);

        token.deleteTokenReset(user.get_id());

        response = "Clave actualizada";

    }

    @Override
    public String getResult() {
        return response;
    }
}
