package com.empresag.study;

import com.empresag.*;

import javax.ws.rs.core.Response;

public class ComandoLinkCreateQuestionToStudy extends ComandoBase {

    private long questionId;
    private long studyId;
    private PreguntaEstudioEntity res;

    public ComandoLinkCreateQuestionToStudy(long questionId, long studyId) {
        this.questionId = questionId;
        this.studyId = studyId;
    }

    @Override
    public void execute() throws Exception {

//        DaoPreguntaEstudio daoPreguntaEstudio = new DaoPreguntaEstudio();
//        DaoPreguntaCategoriaSubcategoria daoPreguntaCategoriaSubcategoria = new DaoPreguntaCategoriaSubcategoria();
//        DaoEstudio daoEstudio = new DaoEstudio();

        DaoPreguntaEstudio daoPreguntaEstudio = FabricaDao.crearDaoPreguntaEstudio();
        DaoPreguntaCategoriaSubcategoria daoPreguntaCategoriaSubcategoria = FabricaDao.crearDaoPreguntaCategoriaSubcategoria();
        DaoEstudio daoEstudio = FabricaDao.crearDaoEstudio();

        PreguntaCatSubcatEntity pcs = null;
        EstudioEntity estudio = null;

        try {
            pcs = daoPreguntaCategoriaSubcategoria.find(questionId,
                    PreguntaCatSubcatEntity.class);

            estudio = daoEstudio.find(studyId, EstudioEntity.class);
        }
        catch (NullPointerException e){
            e.printStackTrace();
//            return Response.status(Response.Status.NOT_FOUND).build();
        }

        PreguntaEstudioEntity pe = new PreguntaEstudioEntity();
        pe.setFkEstudio(estudio);
        pe.setFkPregunta(pcs.getFkPregunta());
        pe.setRequerido(1);
        daoPreguntaEstudio.insert(pe);

//        return Response.ok().entity(pe).build();
        res = pe;
    }

    @Override
    public PreguntaEstudioEntity getResult() {
        return res;
    }
}
