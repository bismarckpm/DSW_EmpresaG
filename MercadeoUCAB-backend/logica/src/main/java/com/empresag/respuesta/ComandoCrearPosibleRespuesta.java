package com.empresag.respuesta;

import com.empresag.*;
import org.eclipse.persistence.exceptions.DatabaseException;

public class ComandoCrearPosibleRespuesta extends ComandoBase {

    private PosibleRespuestaEntity posibleRespuestaEntity;
    private PosibleRespuestaDto posibleRespuestaDto;

    public ComandoCrearPosibleRespuesta(PosibleRespuestaDto _posibleRespuestaDto) {
        posibleRespuestaDto = _posibleRespuestaDto;
        posibleRespuestaEntity = PosibleRespuestaMapper.mapDtoToEntity( _posibleRespuestaDto );
    }

    @Override
    public void execute() throws Exception {
        DaoPosibleRespuesta daoPosibleRespuesta = FabricaDao.crearDaoPosibleRespuesta();

        try {
            daoPosibleRespuesta.insert(posibleRespuestaEntity);
        }
        catch (DatabaseException e){

            e.printStackTrace();
            daoPosibleRespuesta.delete(posibleRespuestaEntity);

        }
        catch (NullPointerException e){

            e.printStackTrace();
            daoPosibleRespuesta.delete(posibleRespuestaEntity);;
            throw new Exception("Error en la data recibida.");
        }
    }

    @Override
    public PosibleRespuestaEntity getResult() {
        return posibleRespuestaEntity;
    }

}
