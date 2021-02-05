package com.empresag.analisis;

import com.empresag.*;
import org.eclipse.persistence.exceptions.DatabaseException;

public class ComandoCrearConclusion extends ComandoBase {
    private AnalisisEntity analisisEntity;
    private AnalisisDto analisisDto;

    public ComandoCrearConclusion(AnalisisDto _analisis) {
        analisisDto = _analisis;
        analisisEntity = AnalisisMapper.mapDtoToEntity( _analisis );
    }

    @Override
    public void execute() throws Exception {

        DaoAnalisis dao = FabricaDao.crearDaoAnalisis();

        try {
            analisisEntity = dao.insert(analisisEntity);
        }
        catch (DatabaseException e){

            e.printStackTrace();
            dao.delete(analisisEntity);

            throw new UsuarioRepetidoException();
        }
        catch (NullPointerException e){

            e.printStackTrace();
            dao.delete(analisisEntity);
            throw new Exception("Error en la data recibida.");
        }
    }

    @Override
    public AnalisisEntity getResult() {
        return analisisEntity;
    }
}
