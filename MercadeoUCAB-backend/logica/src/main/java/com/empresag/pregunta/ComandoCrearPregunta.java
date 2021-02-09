package com.empresag.pregunta;

import com.empresag.*;
import org.eclipse.persistence.exceptions.DatabaseException;

public class ComandoCrearPregunta extends ComandoBase {

    private PreguntaEntity preguntaEntity;
    private PreguntaDto preguntaDto;

    public ComandoCrearPregunta(PreguntaDto _preguntaDto) {
        preguntaDto = _preguntaDto;
        preguntaEntity = PreguntaMapper.mapDtoToEntity( _preguntaDto );
    }

    @Override
    public void execute() throws Exception {
        DaoPregunta daoPregunta = FabricaDao.crearDaoPregunta();

        try {
            daoPregunta.insert(preguntaEntity);
        }
        catch (DatabaseException e){

            e.printStackTrace();
            daoPregunta.delete(preguntaEntity);

        }
        catch (NullPointerException e){

            e.printStackTrace();
            daoPregunta.delete(preguntaEntity);;
            throw new Exception("Error en la data recibida.");
        }
    }

    @Override
    public PreguntaEntity getResult() {
        return preguntaEntity;
    }

}
