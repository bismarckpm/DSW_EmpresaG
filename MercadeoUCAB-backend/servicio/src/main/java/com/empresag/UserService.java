package com.empresag;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class UserService {

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

}
