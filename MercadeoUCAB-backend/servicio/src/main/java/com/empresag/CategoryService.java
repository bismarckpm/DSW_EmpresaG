package com.empresag;

import com.empresag.brand.ComandoEliminarBrand;
import com.empresag.categoria.ComandoCrearCategoria;
import com.empresag.categoria.ComandoEditarCategoria;
import com.empresag.categoria.ComandoEliminarCategoria;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/categories")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class CategoryService {
    @GET
    @Path("/all")
    public RespuestaDto<List<CategoriaEntity>> allCategories(){
        DaoCategoria categoriaDao = FabricaDao.crearDaoCategoria();

        RespuestaDto<List<CategoriaEntity>> respuesta = new RespuestaDto<>();

        try{


            respuesta.setCodigo(0);
            respuesta.setEstado( "OK" );
            respuesta.setObjeto( categoriaDao.findAll(CategoriaEntity.class) );
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

//    @POST
//    @Path("/add")
//    public CategoriaEntity addCategory(CategoriaDto categoriaDto){
//        DaoCategoria categoriaDao = FabricaDao.crearDaoCategoria();
//
//        CategoriaEntity categoria = new CategoriaEntity();
//        categoria.setNombre(categoriaDto.getNombre());
//        categoria.setDescripcion(categoriaDto.getDescripcion());
//
//        categoriaDao.insert(categoria);
//        return categoria;
//    }
//
//    @PUT
//    @Path("/update/{id}")
//    public Response updateCategory(@PathParam("id") long id, CategoriaDto categoriaDto){
//        DaoCategoria categoriaDao = FabricaDao.crearDaoCategoria();
//        CategoriaEntity categoriaOld = categoriaDao.find(id, CategoriaEntity.class);
//
//        if (categoriaOld != null){
//            categoriaOld.setNombre(categoriaDto.getNombre());
//            categoriaOld.setDescripcion(categoriaDto.getDescripcion());
//            categoriaDao.update(categoriaOld);
//            return Response.ok().entity(categoriaOld).build();
//        }
//
//        else {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }

    @POST
    @Path("/add")
    public RespuestaDto<CategoriaEntity> addCategory(CategoriaDto categoriaDto){

        RespuestaDto<CategoriaEntity> respuesta = new RespuestaDto<>();

        try{
            ComandoCrearCategoria crearBrand = new ComandoCrearCategoria(categoriaDto);
            crearBrand.execute();

            respuesta.setCodigo(0);
            respuesta.setEstado( "OK" );
            respuesta.setObjeto( crearBrand.getResult() );
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

    @PUT
    @Path("/update/{id}")
    public RespuestaDto<Boolean> updateCategory(@PathParam("id") long id, CategoriaDto categoriaDto){
        RespuestaDto<Boolean> respuesta = new RespuestaDto<>();

        try {
            ComandoEditarCategoria editarCategoria = new ComandoEditarCategoria(id, categoriaDto);
            editarCategoria.execute();

            if (editarCategoria.getResult()){
                respuesta.setCodigo(0);
                respuesta.setEstado( "OK" );
                respuesta.setMensaje( "Categoria editada" );
            }else{
                respuesta.setCodigo(-1);
                respuesta.setEstado( "ERROR" );
                respuesta.setMensaje("Categoria no editada");
            }

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

//    @DELETE
//    @Path("/delete/{id}")
//    public Response deleteCategory(@PathParam("id") long id){
//        DaoCategoria categoriaDao = FabricaDao.crearDaoCategoria();
//        CategoriaEntity categoriaOld = categoriaDao.find(id, CategoriaEntity.class);
//
//        if (categoriaOld != null){
//            categoriaDao.delete(categoriaOld);
//            return Response.ok().entity(categoriaOld).build();
//        }
//        else {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }

    @DELETE
    @Path("/delete/{id}")
    public RespuestaDto<Boolean> deleteCategory(@PathParam("id") long id){
        RespuestaDto<Boolean> respuesta = new RespuestaDto<>();

        try {
            ComandoEliminarCategoria eliminarCategoria = new ComandoEliminarCategoria(id);
            eliminarCategoria.execute();

            if (eliminarCategoria.getResult()){
                respuesta.setCodigo(0);
                respuesta.setEstado( "OK" );
                respuesta.setMensaje("Categoria eliminada");
            }else{
                respuesta.setCodigo(-1);
                respuesta.setEstado( "OK" );
                respuesta.setMensaje("Categoria no eliminada");
            }
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
