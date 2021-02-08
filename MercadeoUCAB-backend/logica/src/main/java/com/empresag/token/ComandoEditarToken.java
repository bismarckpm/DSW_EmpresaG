package com.empresag.token;

import com.empresag.*;

public class ComandoEditarToken extends ComandoBase {

    private long id;
    private TokenDto tokenDto;
    private boolean editado = false;

    public ComandoEditarToken(long _id,TokenDto _tokenDto) {
        this.id = _id;
        this.tokenDto = _tokenDto;
    }

    @Override
    public void execute() throws Exception {

        DaoToken daoToken =  FabricaDao.crearDaoToken();
        try {
            TokenEntity tokenOld = daoToken.find(id, TokenEntity.class);

            if (tokenOld != null) {

                tokenOld.setToken_reset(tokenDto.getToken_reset());
                tokenOld.setToken_login(tokenDto.getToken_login());

                tokenOld.setFkUsuario(UsuarioMapper.mapDtoToEntity(tokenDto.getFkUsuario()));

                daoToken.update(tokenOld);

                editado = true;

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Boolean getResult() {
        return editado;
    }

}
