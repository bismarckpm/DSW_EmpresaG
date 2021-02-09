package com.empresag.opcion;

import com.empresag.*;
import org.eclipse.persistence.exceptions.DatabaseException;

public class ComandoCrearOpcion extends ComandoBase {

    private OpcionEntity opcionEntity;
    private OpcionDto opcionDto;

    public ComandoCrearOpcion(OpcionDto _opcionDto) {
        opcionDto = _opcionDto;
        opcionEntity = OpcionMapper.mapDtoToEntity( _opcionDto );
    }

    @Override
    public void execute() throws Exception {
        DaoOpcion daoOpcion = FabricaDao.crearDaoOpcion();

        try {
            daoOpcion.insert(opcionEntity);
        }
        catch (DatabaseException e){

            e.printStackTrace();
            daoOpcion.delete(opcionEntity);

        }
        catch (NullPointerException e){

            e.printStackTrace();
            daoOpcion.delete(opcionEntity);;
            throw new Exception("Error en la data recibida.");
        }
    }

    @Override
    public OpcionEntity getResult() {
        return opcionEntity;
    }

}
