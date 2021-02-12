package com.empresag.brand;

import com.empresag.*;
import org.eclipse.persistence.exceptions.DatabaseException;

public class ComandoCrearBrand extends ComandoBase {

    private SubcategoriaMarcaEntity subcategoriaMarcaEntity;
    private SubcategoriaMarcaDto subcategoriaMarcaDto;

    public ComandoCrearBrand(SubcategoriaMarcaDto _subcategoriaMarca) {
        subcategoriaMarcaDto = _subcategoriaMarca;
    }

    @Override
    public void execute() throws Exception {

        DaoMarca daoMarca = FabricaDao.crearDaoMarca();
        DaoSubcategoriaMarca dao = FabricaDao.crearDaoSubcategoriaMarca();

        MarcaEntity marca = new MarcaEntity();
        marca.setNombre(subcategoriaMarcaDto.getFkMarca().getNombre());
        marca.setDescripcion(subcategoriaMarcaDto.getFkMarca().getDescripcion());

        try {
            marca = daoMarca.insert(marca);

            subcategoriaMarcaDto.getFkMarca().set_id(marca.get_id());
            subcategoriaMarcaEntity = SubcategoriaMarcaMapper.mapDtoToEntity( subcategoriaMarcaDto );
            subcategoriaMarcaEntity = dao.insert(subcategoriaMarcaEntity);
        }
        catch (DatabaseException e){

            e.printStackTrace();
            daoMarca.delete(marca);
            dao.delete(subcategoriaMarcaEntity);

        }
        catch (NullPointerException e){

            e.printStackTrace();
            daoMarca.delete(marca);
            dao.delete(subcategoriaMarcaEntity);
            throw new Exception("Error en la data recibida.");
        }
    }

    @Override
    public SubcategoriaMarcaEntity getResult() {
        return subcategoriaMarcaEntity;
    }

}