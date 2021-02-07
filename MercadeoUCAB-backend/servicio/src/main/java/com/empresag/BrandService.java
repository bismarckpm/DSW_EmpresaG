package com.empresag;

import com.empresag.brand.ComandoCrearBrand;
import com.empresag.brand.ComandoEditarBrand;
import com.empresag.brand.ComandoEliminarBrand;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/brands")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class BrandService {
    @QueryParam("subcategory_id") long id;

    @GET
    @Path("/all")
    public List<SubcategoriaMarcaEntity> allBrands(){
        DaoSubcategoriaMarca daoSubcategoriaMarca = FabricaDao.crearDaoSubcategoriaMarca();
        return daoSubcategoriaMarca.findAll(SubcategoriaMarcaEntity.class);
    }

    @GET
    @Path("/filtered-by-subcategory")
    public List<SubcategoriaMarcaEntity> findBySubcategoryID(){
        DaoSubcategoriaMarca daoSubcategoriaMarca = FabricaDao.crearDaoSubcategoriaMarca();
        return daoSubcategoriaMarca.findBySubcategoryID(id);
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
    public RespuestaDto<SubcategoriaMarcaDto> addBrand(SubcategoriaMarcaDto subcategoriaMarcaDto) {
        DaoSubcategoriaMarca daoSubcategoriaMarca = FabricaDao.crearDaoSubcategoriaMarca();
        DaoMarca daoMarca = FabricaDao.crearDaoMarca();

        RespuestaDto<SubcategoriaMarcaDto> respuesta = new RespuestaDto<>();

        try{
            ComandoCrearBrand crearBrand = new ComandoCrearBrand(subcategoriaMarcaDto);
            crearBrand.execute();

            respuesta.setCodigo(0);
            respuesta.setEstado( "OK" );
            respuesta.setObjeto( subcategoriaMarcaDto );
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
    public RespuestaDto<SubcategoriaMarcaDto> updateBrand(@PathParam("id") long id, SubcategoriaMarcaDto subcategoriaMarcaDto){
        RespuestaDto<SubcategoriaMarcaDto> respuesta = new RespuestaDto<>();

        try {
            ComandoEditarBrand editarBrand = new ComandoEditarBrand(subcategoriaMarcaDto);
            editarBrand.execute();

            respuesta.setCodigo(0);
            respuesta.setEstado( "OK" );
            respuesta.setObjeto( subcategoriaMarcaDto );
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
    public RespuestaDto<SubcategoriaMarcaDto> deleteBrand(@PathParam("id") long id){
        RespuestaDto<SubcategoriaMarcaDto> respuesta = new RespuestaDto<>();

        try {
            ComandoEliminarBrand eliminarBrand = new ComandoEliminarBrand(id);
            eliminarBrand.execute();

            respuesta.setCodigo(0);
            respuesta.setEstado( "OK" );
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
