package com.empresag.respuesta;

import com.empresag.*;
import org.eclipse.persistence.exceptions.DatabaseException;

import java.util.*;

public class ComandoBuscarPosiblesRespuestas extends ComandoBase {

    private List<PosibleRespuestaEntity> posibleRespuestaList;

    private long id;

    public ComandoBuscarPosiblesRespuestas(long id, List<PosibleRespuestaEntity> list) {
        posibleRespuestaList = list;
        this.id = id;
    }

    @Override
    public void execute() throws Exception { }

    public List<OpcionDto> executeList() throws Exception {

        List<OpcionDto> list = new ArrayList<>();

        for (PosibleRespuestaEntity pre: posibleRespuestaList) {
            OpcionDto opcionDto = FabricaDto.crearOpcionDto();
            if (id == 5){
                opcionDto.setRangoInicial(pre.getFkOpcion().getRangoInicial());
                opcionDto.setRangoFinal(pre.getFkOpcion().getRangoFinal());
            }
            else {
                opcionDto.setValor(pre.getFkOpcion().getValor());
            }
            opcionDto.set_id(pre.getFkOpcion().get_id());

            list.add(opcionDto);
        }

        return list;

    }

    @Override
    public List<PosibleRespuestaEntity> getResult() {
        return posibleRespuestaList;
    }

}
