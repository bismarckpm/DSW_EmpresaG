package com.empresag.token;

import com.empresag.*;
import org.eclipse.persistence.exceptions.DatabaseException;

public class ComandoCrearToken extends  ComandoBase {

    private TokenEntity tokenEntity;
    private TokenDto tokenDto;

    public ComandoCrearToken(TokenDto _tokenDto) {
        tokenDto = _tokenDto;
        tokenEntity = TokenMapper.mapDtoToEntity( _tokenDto );
    }

    @Override
    public void execute() throws Exception {

        DaoToken daoToken = FabricaDao.crearDaoToken();

        try {
            tokenEntity = daoToken.insert(tokenEntity);
        }
        catch (DatabaseException e){

            e.printStackTrace();
            daoToken.delete(tokenEntity);

            throw  new LoginException();

        }
        catch (NullPointerException e){

            e.printStackTrace();
            daoToken.delete(tokenEntity);
            throw new Exception("Error en la data recibida.");

        }
    }

    @Override
    public TokenEntity getResult() {
        return tokenEntity;
    }

}
