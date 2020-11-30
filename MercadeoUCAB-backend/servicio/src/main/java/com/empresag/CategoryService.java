package com.empresag;

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
    public List<CategoriaEntity> allCategories(){
        DaoCategoria categoriaDao = new DaoCategoria();
        return categoriaDao.findAll(CategoriaEntity.class);
    }

    @POST
    @Path("/add")
    public CategoriaEntity addCategory(CategoriaDto categoriaDto){
        DaoCategoria categoriaDao = new DaoCategoria();

        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setNombre(categoriaDto.getNombre());
        categoria.setDescripcion(categoriaDto.getDescripcion());

        categoriaDao.insert(categoria);
        return categoria;
    }

    @PUT
    @Path("/update/{id}")
    public Response updateCategory(@PathParam("id") long id, CategoriaDto categoriaDto){
        DaoCategoria categoriaDao = new DaoCategoria();
        CategoriaEntity categoriaOld = categoriaDao.find(id, CategoriaEntity.class);

        if (categoriaOld != null){
            categoriaOld.setNombre(categoriaDto.getNombre());
            categoriaOld.setDescripcion(categoriaDto.getDescripcion());
            categoriaDao.update(categoriaOld);
            return Response.ok().entity(categoriaOld).build();
        }

        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteCategory(@PathParam("id") long id){
        DaoCategoria categoriaDao = new DaoCategoria();
        CategoriaEntity categoriaOld = categoriaDao.find(id, CategoriaEntity.class);

        if (categoriaOld != null){
            categoriaDao.delete(categoriaOld);
            return Response.ok().entity(categoriaOld).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
