package com.empresag;

import com.empresag.subcategory.ComandoCrearSubcategory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/subcategories")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class SubcategoryService {
    @QueryParam("category_id") long id;

    @GET
    @Path("/all")
    public RespuestaDto<List<CategoriaSubcategoriaEntity>> allSubcategories(){
        DaoCategoriaSubcategoria daoCategoriaSubcategoria = FabricaDao.crearDaoCategoriaSubcategoria();

        RespuestaDto<List<CategoriaSubcategoriaEntity>> respuesta = new RespuestaDto();

        try {
            respuesta.setCodigo(0);
            respuesta.setEstado("OK");
            respuesta.setObjeto(daoCategoriaSubcategoria.findAll(CategoriaSubcategoriaEntity.class));

        }
        catch (Exception e){

            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado( "ERROR" );
            respuesta.setMensaje( e.getMessage() );
            respuesta.setMensajesoporte( e.getLocalizedMessage() );

        }

        return respuesta;
    }

    @GET
    @Path("/filtered-by-category")
    public RespuestaDto<List<CategoriaSubcategoriaEntity>> findByCategoryID(){
        DaoCategoriaSubcategoria daoCategoriaSubcategoria = new DaoCategoriaSubcategoria();

        RespuestaDto<List<CategoriaSubcategoriaEntity>> respuesta = new RespuestaDto();

        try {
            respuesta.setCodigo(0);
            respuesta.setEstado("OK");
            respuesta.setObjeto(daoCategoriaSubcategoria.findByCategoryID(id));

        }
        catch (Exception e){

            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado( "ERROR" );
            respuesta.setMensaje( e.getMessage() );
            respuesta.setMensajesoporte( e.getLocalizedMessage() );

        }

        return respuesta;
    }

    @POST
    @Path("/add")
    public RespuestaDto<CategoriaSubcategoriaDto> addSubcategoria(CategoriaSubcategoriaDto categoriaSubcategoriaDto){

        RespuestaDto<CategoriaSubcategoriaDto> respuesta = new RespuestaDto();
        try {
            ComandoCrearSubcategory comando = new ComandoCrearSubcategory(categoriaSubcategoriaDto);
            comando.execute();

            respuesta.setCodigo(0);
            respuesta.setEstado("OK");
            respuesta.setMensaje("Subcategoria agregada exitosamente.");
            respuesta.setObjeto(comando.getResult());

        } catch (Exception e) {
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado( "ERROR" );
            respuesta.setMensaje( e.getMessage() );
            respuesta.setMensajesoporte( e.getLocalizedMessage() );
        }

        return respuesta;
    }

    @PUT
    @Path("/update/{id}")
    public RespuestaDto<Boolean> updateSubcategoria(@PathParam("id") long id, CategoriaSubcategoriaDto categoriaSubcategoriaDto){
        RespuestaDto<Boolean> respuesta = new RespuestaDto();

        try {
            DaoSubcategoria daoSubcategoria = FabricaDao.crearDaoSubcategoria();
            DaoCategoriaSubcategoria daoCategoriaSubcategoria = FabricaDao.crearDaoCategoriaSubcategoria();
            CategoriaSubcategoriaEntity cs = daoCategoriaSubcategoria.find(id, CategoriaSubcategoriaEntity.class);

            if (cs != null) {
                cs.setFkCategoria(new CategoriaEntity(
                        categoriaSubcategoriaDto.getFkCategoria().get_id(),
                        categoriaSubcategoriaDto.getFkCategoria().getNombre()
                ));
                cs.setFkSubcategoria(new SubcategoriaEntity(
                        categoriaSubcategoriaDto.getFkSubcategoria().get_id()));

                SubcategoriaEntity subcategoria = daoSubcategoria.find(
                        categoriaSubcategoriaDto.getFkSubcategoria().get_id(),
                        SubcategoriaEntity.class);

                if (subcategoria != null) {
                    subcategoria.setNombre(categoriaSubcategoriaDto.getFkSubcategoria().getNombre());
                    subcategoria.setDescripcion(categoriaSubcategoriaDto.getFkSubcategoria().getDescripcion());

                    daoSubcategoria.update(subcategoria);
                    daoCategoriaSubcategoria.update(cs);

                    respuesta.setCodigo(0);
                    respuesta.setEstado("OK");
                    respuesta.setMensaje("Subcategoria actualizada exitosamente.");

                } else {
                    respuesta.setCodigo(-1);
                    respuesta.setEstado("ERROR");
                    respuesta.setMensaje("Subcategoria no encontrada");
                }
            } else {
                respuesta.setCodigo(-1);
                respuesta.setEstado("ERROR");
                respuesta.setMensaje("Subcategoria no encontrada");
            }
        }
        catch (NullPointerException e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado( "ERROR" );
            respuesta.setMensaje( "Subcategoria no encontrada" );
            respuesta.setMensajesoporte( e.getLocalizedMessage() );
        }
        catch (Exception e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado( "ERROR" );
            respuesta.setMensaje( e.getMessage() );
            respuesta.setMensajesoporte( e.getLocalizedMessage() );
        }
        return respuesta;
    }

    @DELETE
    @Path("/delete/{id}")
    public RespuestaDto<Boolean> deleteSubcategoria(@PathParam("id") long id){
        DaoCategoriaSubcategoria daoCategoriaSubcategoria = FabricaDao.crearDaoCategoriaSubcategoria();

        RespuestaDto<Boolean> respuesta = new RespuestaDto();
        try {

            CategoriaSubcategoriaEntity cs = daoCategoriaSubcategoria.find(id, CategoriaSubcategoriaEntity.class);

            if (cs != null) {
                daoCategoriaSubcategoria.delete(cs);

                respuesta.setCodigo(0);
                respuesta.setEstado( "OK" );
                respuesta.setMensaje("Subcategoria eliminada exitosamente.");

            } else {
                respuesta.setCodigo(-1);
                respuesta.setEstado( "ERROR" );
                respuesta.setMensaje( "Subcategoria no encontrada" );
            }
        }
        catch (NullPointerException e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado( "ERROR" );
            respuesta.setMensaje( "Subcategoria no encontrada" );
            respuesta.setMensajesoporte( e.getLocalizedMessage() );
        }
        catch (Exception e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado( "ERROR" );
            respuesta.setMensaje( e.getMessage() );
            respuesta.setMensajesoporte( e.getLocalizedMessage() );
        }
        return respuesta;
    }
}
