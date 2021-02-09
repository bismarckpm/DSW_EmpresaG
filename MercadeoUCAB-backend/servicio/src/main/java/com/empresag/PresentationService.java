package com.empresag;

import com.empresag.presentation.ComandoCrearPresentacion;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/presentations")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class PresentationService {

    @GET
    @Path("/all")
    public RespuestaDto<List<TipoPresentacionEntity>> allPresentations(){
        RespuestaDto<List<TipoPresentacionEntity>> respuesta = new RespuestaDto<>();

        try {

            DaoTipoPresentacion daoTipoPresentacion = FabricaDao.crearDaoTipoPresentacion();

            respuesta.setCodigo(0);
            respuesta.setEstado("OK");
            respuesta.setObjeto(daoTipoPresentacion.findAll(TipoPresentacionEntity.class));

        }
        catch (Exception e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje(e.getMessage());
        }

        return respuesta;
    }

    @POST
    @Path("/add")
    public RespuestaDto<TipoPresentacionEntity> addPresentation(TipoPresentacionDto tipoPresentacionDto){
//        DaoTipoPresentacion daoTipoPresentacion = FabricaDao.crearDaoTipoPresentacion();
//        DaoPresentacion daoPresentacion = FabricaDao.crearDaoPresentacion();
//
//        TipoEntity tipo = FabricaEntity.crearTipoEntity(tipoPresentacionDto.getFkTipo().get_id());
//        tipo.setNombre(tipoPresentacionDto.getFkTipo().getNombre());
//
//        PresentacionEntity presentacion = FabricaEntity.crearPresentacionEntity();
//        presentacion.setNombre(tipoPresentacionDto.getFkPresentacion().getNombre());
//        presentacion.setDescripcion(tipoPresentacionDto.getFkPresentacion().getDescripcion());
//        daoPresentacion.insert(presentacion);
//
//        TipoPresentacionEntity tp = FabricaEntity.crearTipoPresentacionEntity();
//        tp.setFkPresentacion(presentacion);
//        tp.setFkTipo(tipo);
//        daoTipoPresentacion.insert(tp);

        RespuestaDto<TipoPresentacionEntity> respuesta = new RespuestaDto<>();

        try {
            ComandoCrearPresentacion comando = new ComandoCrearPresentacion(tipoPresentacionDto);
            comando.execute();
            respuesta.setCodigo(0);
            respuesta.setEstado("OK");
            respuesta.setMensaje("Presentacion agregada exitosamente.");
            respuesta.setObjeto(comando.getResult());
        }
        catch (Exception e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje(e.getMessage());
        }

        return respuesta;
    }


    @PUT
    @Path("/update/{id}")
    public RespuestaDto<TipoPresentacionEntity> updateType(@PathParam("id") long id, TipoPresentacionDto tipoPresentacionDto){
        RespuestaDto<TipoPresentacionEntity> respuesta = new RespuestaDto<>();

        try {
            DaoTipoPresentacion daoTipoPresentacion = new DaoTipoPresentacion();
            DaoPresentacion daoPresentacion = new DaoPresentacion();
            TipoPresentacionEntity tp = daoTipoPresentacion.find(id, TipoPresentacionEntity.class);

            if (tp != null) {
                tp.setFkTipo(new TipoEntity(tipoPresentacionDto.getFkTipo().get_id(),
                        tipoPresentacionDto.getFkPresentacion().getNombre()));
                tp.setFkPresentacion(new PresentacionEntity(tipoPresentacionDto.getFkPresentacion().get_id()));

                PresentacionEntity presentacion = daoPresentacion.find(tipoPresentacionDto.getFkPresentacion().get_id(),
                        PresentacionEntity.class);

                if (presentacion != null) {
                    presentacion.setNombre(tipoPresentacionDto.getFkPresentacion().getNombre());
                    presentacion.setDescripcion(tipoPresentacionDto.getFkPresentacion().getDescripcion());
                    daoPresentacion.update(presentacion);
                    daoTipoPresentacion.update(tp);

                    respuesta.setCodigo(0);
                    respuesta.setEstado("OK");
                    respuesta.setMensaje("La presentacion ha sido actualizada");
                    respuesta.setObjeto(tp);
                } else {
                    respuesta.setCodigo(-1);
                    respuesta.setEstado("ERROR");
                    respuesta.setMensaje("No existe la presentacion solicitada.");
                }
            } else {
                respuesta.setCodigo(-1);
                respuesta.setEstado("ERROR");
                respuesta.setMensaje("No existe la presentacion solicitada.");
            }
        }
        catch (NullPointerException e){

            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje("No existe la presentacion solicitada.");
        }
        catch (Exception e){

            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje(e.getMessage());
        }

        return respuesta;
    }


    @DELETE
    @Path("/delete/{id}")
    public RespuestaDto<Boolean> deletePresentation(@PathParam("id") long id){
        RespuestaDto<Boolean> respuesta = new RespuestaDto<>();
        DaoTipoPresentacion daoTipoPresentacion = FabricaDao.crearDaoTipoPresentacion();
        try {
            TipoPresentacionEntity tp = daoTipoPresentacion.find(id, TipoPresentacionEntity.class);

            if (tp != null) {
                daoTipoPresentacion.delete(tp);
                respuesta.setCodigo(0);
                respuesta.setEstado("OK");
                respuesta.setMensaje("La presentacion ha sido eliminada.");
                respuesta.setObjeto(true);
            } else {
                respuesta.setCodigo(-1);
                respuesta.setEstado("ERROR");
                respuesta.setMensaje("No existe la presentacion solicitada.");
                respuesta.setObjeto(false);
            }
        }
        catch (NullPointerException e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje("No existe la presentacion solicitada.");
            respuesta.setObjeto(false);
        }
        catch (Exception e){

            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje(e.getMessage());
            respuesta.setObjeto(false);
        }

        return respuesta;
    }
}
