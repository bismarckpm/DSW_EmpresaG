package com.empresag;

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
    public List<CategoriaSubcategoriaEntity> allSubcategories(){
        DaoCategoriaSubcategoria daoCategoriaSubcategoria = new DaoCategoriaSubcategoria();
        return daoCategoriaSubcategoria.findAll(CategoriaSubcategoriaEntity.class);
    }

    @GET
    @Path("/filtered-by-category")
    public List<CategoriaSubcategoriaEntity> findByCategoryID(){
        DaoCategoriaSubcategoria daoCategoriaSubcategoria = new DaoCategoriaSubcategoria();
        return daoCategoriaSubcategoria.findByCategoryID(id);
    }

    @POST
    @Path("/add")
    public CategoriaSubcategoriaEntity addSubcategoria(CategoriaSubcategoriaDto categoriaSubcategoriaDto){
        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
        SubcategoriaEntity subcategoria = new SubcategoriaEntity();
        subcategoria.setNombre(categoriaSubcategoriaDto.getFkSubcategoria().getNombre());
        subcategoria.setDescripcion(categoriaSubcategoriaDto.getFkSubcategoria().getDescripcion());

        daoSubcategoria.insert(subcategoria);

        DaoCategoriaSubcategoria daoCategoriaSubcategoria = new DaoCategoriaSubcategoria();
        CategoriaEntity categoria = new CategoriaEntity(categoriaSubcategoriaDto.getFkCategoria().get_id());
        categoria.setNombre(categoriaSubcategoriaDto.getFkCategoria().getNombre());
        CategoriaSubcategoriaEntity cs = new CategoriaSubcategoriaEntity();
        cs.setFkCategoria(categoria);
        cs.setFkSubcategoria(subcategoria);
        daoCategoriaSubcategoria.insert(cs);

        return cs;
    }

    @PUT
    @Path("/update/{id}")
    public Response updateSubcategoria(@PathParam("id") long id, CategoriaSubcategoriaDto categoriaSubcategoriaDto){
        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
        DaoCategoriaSubcategoria daoCategoriaSubcategoria = new DaoCategoriaSubcategoria();
        CategoriaSubcategoriaEntity cs = daoCategoriaSubcategoria.find(id, CategoriaSubcategoriaEntity.class);

        if (cs != null){
            cs.setFkCategoria(new CategoriaEntity(categoriaSubcategoriaDto.getFkCategoria().get_id(),
                    categoriaSubcategoriaDto.getFkCategoria().getNombre()));
            cs.setFkSubcategoria(new SubcategoriaEntity(categoriaSubcategoriaDto.getFkSubcategoria().get_id()));
            SubcategoriaEntity subcategoria = daoSubcategoria.find(categoriaSubcategoriaDto.getFkSubcategoria().get_id(),
                    SubcategoriaEntity.class);

            if (subcategoria != null){
                subcategoria.setNombre(categoriaSubcategoriaDto.getFkSubcategoria().getNombre());
                subcategoria.setDescripcion(categoriaSubcategoriaDto.getFkSubcategoria().getDescripcion());
                daoSubcategoria.update(subcategoria);
                daoCategoriaSubcategoria.update(cs);
                return Response.ok().entity(cs).build();
            }
            else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteSubcategoria(@PathParam("id") long id){
        DaoCategoriaSubcategoria daoCategoriaSubcategoria = new DaoCategoriaSubcategoria();
        CategoriaSubcategoriaEntity cs = daoCategoriaSubcategoria.find(id, CategoriaSubcategoriaEntity.class);

        if (cs != null){
            daoCategoriaSubcategoria.delete(cs);
            return Response.ok().entity(cs).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
