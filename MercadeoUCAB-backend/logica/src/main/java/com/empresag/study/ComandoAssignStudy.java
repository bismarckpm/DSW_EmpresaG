package com.empresag.study;

import com.empresag.*;

import javax.ws.rs.core.Response;
import java.sql.Date;

public class ComandoAssignStudy extends ComandoBase {

    private long id;
    private long rid;
    private FiltroEntity res;

    public ComandoAssignStudy(long id, long rid) {
        this.id = id;
        this.rid = rid;
    }

    @Override
    public void execute() throws Exception {
//        DaoEstudio daoEstudio = new DaoEstudio();
//        DaoSolicitud daoSolicitud = new DaoSolicitud();
//        DaoFiltro daoFiltro = new DaoFiltro();

        DaoEstudio daoEstudio = FabricaDao.crearDaoEstudio();
        DaoSolicitud daoSolicitud = FabricaDao.crearDaoSolicitud();
        DaoFiltro daoFiltro = FabricaDao.crearDaoFiltro();
        FiltroEntity studyToClone = null;
        SolicitudEntity solicitud = null;

        EstudioEntity estudio = new EstudioEntity();

        try {
            studyToClone = daoFiltro.getCurrentRequest(rid);
            solicitud = daoSolicitud.find(rid, SolicitudEntity.class);
            solicitud.setEstado(1);
            daoSolicitud.update(solicitud);

            estudio.setFechaRealizacion(new Date(System.currentTimeMillis()));
            estudio.setEstado(1);
            estudio.setNombre(solicitud.getNombre());
            daoEstudio.insert(estudio);

            studyToClone.setFkEstudio(estudio);
            daoFiltro.update(studyToClone);

            daoEstudio.cloneStudy(id, estudio.get_id());

            res = studyToClone;
//            return Response.ok().entity(studyToClone).build();
        }
        catch (NullPointerException e){
            e.printStackTrace();
//            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public FiltroEntity getResult() {
        return res;
    }
}
