package com.empresag;

import com.empresag.brand.ComandoCrearBrand;
import com.empresag.brand.ComandoEditarBrand;
import com.empresag.brand.ComandoEliminarBrand;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/brands")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class BrandService {
    @QueryParam("subcategory_id") long id;

//    @GET
//    @Path("/all")
//    public List<SubcategoriaMarcaEntity> allBrands(){
//        DaoSubcategoriaMarca daoSubcategoriaMarca = FabricaDao.crearDaoSubcategoriaMarca();
//        return daoSubcategoriaMarca.findAll(SubcategoriaMarcaEntity.class);
//    }

    @GET
    @Path("/all")
    public RespuestaDto<List<SubcategoriaMarcaEntity>> allBrands(){

        RespuestaDto<List<SubcategoriaMarcaEntity>> respuesta = new RespuestaDto<>();

        try{
            DaoSubcategoriaMarca daoSubcategoriaMarca = FabricaDao.crearDaoSubcategoriaMarca();

            respuesta.setCodigo(0);
            respuesta.setEstado( "OK" );
            respuesta.setObjeto( daoSubcategoriaMarca.findAll(SubcategoriaMarcaEntity.class) );
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

//    @GET
//    @Path("/filtered-by-subcategory")
//    public List<SubcategoriaMarcaEntity> findBySubcategoryID(){
//        DaoSubcategoriaMarca daoSubcategoriaMarca = FabricaDao.crearDaoSubcategoriaMarca();
//        return daoSubcategoriaMarca.findBySubcategoryID(id);
//    }

    @GET
    @Path("/filtered-by-subcategory")
    public RespuestaDto<List<SubcategoriaMarcaEntity>> findBySubcategoryID(){
        DaoSubcategoriaMarca daoSubcategoriaMarca = FabricaDao.crearDaoSubcategoriaMarca();

        RespuestaDto<List<SubcategoriaMarcaEntity>> respuesta = new RespuestaDto<>();

        try{
            respuesta.setCodigo(0);
            respuesta.setEstado( "OK" );
            respuesta.setObjeto( daoSubcategoriaMarca.findBySubcategoryID(id) );
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
//    public SubcategoriaMarcaEntity addBrand(SubcategoriaMarcaDto subcategoriaMarcaDto) {
//        DaoSubcategoriaMarca daoSubcategoriaMarca = FabricaDao.crearDaoSubcategoriaMarca();
//        DaoMarca daoMarca = FabricaDao.crearDaoMarca();
//
//        SubcategoriaEntity subcategoria = new SubcategoriaEntity(subcategoriaMarcaDto.getFkSubcategoria().get_id());
//        subcategoria.setNombre(subcategoriaMarcaDto.getFkSubcategoria().getNombre());
//
//        MarcaEntity marca = new MarcaEntity();
//        marca.setNombre(subcategoriaMarcaDto.getFkMarca().getNombre());
//        marca.setDescripcion(subcategoriaMarcaDto.getFkMarca().getDescripcion());
//        daoMarca.insert(marca);
//
//        SubcategoriaMarcaEntity sm = new SubcategoriaMarcaEntity();
//        sm.setFkMarca(marca);
//        sm.setFkSubcategoria(subcategoria);
//        daoSubcategoriaMarca.insert(sm);
//
//        return sm;
//    }

    @POST
    @Path("/add")
    public RespuestaDto<SubcategoriaMarcaEntity> addBrand(SubcategoriaMarcaDto subcategoriaMarcaDto) {

        RespuestaDto<SubcategoriaMarcaEntity> respuesta = new RespuestaDto<>();

        try{
            ComandoCrearBrand crearBrand = new ComandoCrearBrand(subcategoriaMarcaDto);
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

//    @PUT
//    @Path("/update/{id}")
//    public Response updateBrand(@PathParam("id") long id, SubcategoriaMarcaDto subcategoriaMarcaDto){
//        DaoSubcategoriaMarca daoSubcategoriaMarca = new DaoSubcategoriaMarca();
//        DaoMarca daoMarca = new DaoMarca();
//        SubcategoriaMarcaEntity sm = daoSubcategoriaMarca.find(id, SubcategoriaMarcaEntity.class);
//
//        if (sm != null){
//            sm.setFkSubcategoria(new SubcategoriaEntity(subcategoriaMarcaDto.getFkSubcategoria().get_id(),
//                    subcategoriaMarcaDto.getFkSubcategoria().getNombre()));
//            sm.setFkMarca(new MarcaEntity(subcategoriaMarcaDto.getFkMarca().get_id()));
//
//            MarcaEntity marca = daoMarca.find(subcategoriaMarcaDto.getFkMarca().get_id(), MarcaEntity.class);
//
//            if (marca != null){
//                marca.setNombre(subcategoriaMarcaDto.getFkMarca().getNombre());
//                marca.setDescripcion(subcategoriaMarcaDto.getFkMarca().getDescripcion());
//                daoMarca.update(marca);
//                daoSubcategoriaMarca.update(sm);
//                return Response.ok().entity(sm).build();
//            }
//            else {
//                return Response.status(Response.Status.NOT_FOUND).build();
//            }
//        }
//        else {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }

    @PUT
    @Path("/update/{id}")
    public RespuestaDto<Boolean> updateBrand(@PathParam("id") long id, SubcategoriaMarcaDto subcategoriaMarcaDto){
        RespuestaDto<Boolean> respuesta = new RespuestaDto<>();

        try {
            ComandoEditarBrand editarBrand = new ComandoEditarBrand(subcategoriaMarcaDto);
            editarBrand.execute();

            if (editarBrand.getResult()){
                respuesta.setCodigo(0);
                respuesta.setEstado( "OK" );
                respuesta.setMensaje( "Marca editada" );
            }else{
                respuesta.setCodigo(-1);
                respuesta.setEstado( "ERROR" );
                respuesta.setMensaje( "Marca no editada" );
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
//    public Response deleteBrand(@PathParam("id") long id){
//        DaoSubcategoriaMarca daoSubcategoriaMarca = new DaoSubcategoriaMarca();
//        SubcategoriaMarcaEntity sm = daoSubcategoriaMarca.find(id, SubcategoriaMarcaEntity.class);
//        if (sm != null){
//            daoSubcategoriaMarca.delete(sm);
//            return Response.ok().entity(sm).build();
//        }
//        else {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }

    @DELETE
    @Path("/delete/{id}")
    public RespuestaDto<Boolean> deleteBrand(@PathParam("id") long id){
        RespuestaDto<Boolean> respuesta = new RespuestaDto<>();

        try {
            ComandoEliminarBrand eliminarBrand = new ComandoEliminarBrand(id);
            eliminarBrand.execute();

            if (eliminarBrand.getResult()){
                respuesta.setCodigo(0);
                respuesta.setEstado( "OK" );
                respuesta.setMensaje("Marca eliminada");
            }else{
                respuesta.setCodigo(-1);
                respuesta.setEstado( "ERROR" );
                respuesta.setMensaje( "Marca no eliminada" );
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
