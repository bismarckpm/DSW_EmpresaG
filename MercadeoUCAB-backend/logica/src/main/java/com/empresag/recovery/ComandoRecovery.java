package com.empresag.recovery;

import com.empresag.*;

import java.util.List;

public class ComandoRecovery extends ComandoBase {

    private String email;
    private  String response;

    public ComandoRecovery(String email) {
        this.email = email;
    }

    @Override
    public void execute() throws Exception {

        DaoToken token = FabricaDao.crearDaoToken();
        DaoUsuario usuario = FabricaDao.crearDaoUsuario();

        long id_user;

        String hash = token.getHASH();

        List<UsuarioEntity> users = usuario.emailExist(email);

        if (!users.isEmpty()){
            id_user = users.get(0).get_id();

            TokenEntity tokenEntity = token.findToken(id_user);

            tokenEntity.setToken_reset(hash);

            token.update(tokenEntity);

            Email em = new Email(email,hash);

            response = em.sendEmail();

        }
        else
           response = "notfound";
    }

    @Override
    public String getResult() {
        return response;
    }
}
