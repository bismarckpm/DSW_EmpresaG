package com.empresag.survey;

import com.empresag.ComandoBase;
import com.empresag.DaoEncuesta;
import com.empresag.FabricaDao;
import com.empresag.RespuestaDto;

public class ComandoIsPersonPartOfAvailablePopulationInterview extends ComandoBase {

    private long studyId, personId;
    private RespuestaDto<Boolean> respuesta;

    public ComandoIsPersonPartOfAvailablePopulationInterview(long studyId, long personId) {
        this.studyId = studyId;
        this.personId = personId;
        respuesta = new RespuestaDto<>();
    }

    @Override
    public void execute() {
        DaoEncuesta daoEncuesta = FabricaDao.crearDaoEncuesta();
        boolean isPartOfAvailablePopulation = daoEncuesta.isPersonPartOfAvailablePopulationInterview(studyId, personId);
        if (isPartOfAvailablePopulation){
            respuesta.setCodigo(0);
            respuesta.setEstado("OK");
            respuesta.setObjeto(isPartOfAvailablePopulation);
        }
        else {
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje( "La persona seleccionada no es parte de la poblacion." );
        }
    }

    @Override
    public RespuestaDto<Boolean> getResult() {
        return respuesta;
    }
}
