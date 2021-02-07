package com.empresag.categoria;

import com.empresag.*;
import org.eclipse.persistence.exceptions.DatabaseException;

public class ComandoCrearCategoria extends ComandoBase {

    private CategoriaEntity categoriaEntity;
    private CategoriaDto categoriaDto;

    public ComandoCrearCategoria(CategoriaDto _categoria) {
        categoriaDto = _categoria;
        categoriaEntity = CategoriaMapper.mapDtoToEntity( _categoria );
    }

    @Override
    public void execute() throws Exception {

        DaoCategoria daoCategoria = FabricaDao.crearDaoCategoria();

        try {
            categoriaEntity = daoCategoria.insert(categoriaEntity);
        }
        catch (DatabaseException e){

            e.printStackTrace();
            daoCategoria.delete(categoriaEntity);

        }
        catch (NullPointerException e){

            e.printStackTrace();
            daoCategoria.delete(categoriaEntity);
            throw new Exception("Error en la data recibida.");

        }
    }

    @Override
    public CategoriaEntity getResult() {
        return categoriaEntity;
    }

}
