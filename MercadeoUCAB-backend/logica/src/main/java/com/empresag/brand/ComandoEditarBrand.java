package com.empresag.brand;

import com.empresag.*;

public class ComandoEditarBrand extends ComandoBase {

    private SubcategoriaMarcaDto subcategoriaMarcaDto;
    private boolean editado = false;

    public ComandoEditarBrand(SubcategoriaMarcaDto _subcategoriaMarcaDto) {
        this.subcategoriaMarcaDto = _subcategoriaMarcaDto;
    }

    @Override
    public void execute() throws Exception {

        DaoMarca daoMarca =  FabricaDao.crearDaoMarca();
        MarcaEntity marcaOld = daoMarca.find(subcategoriaMarcaDto.getFkMarca().get_id(), MarcaEntity.class);

        if (marcaOld != null){

            marcaOld.setDescripcion(subcategoriaMarcaDto.getFkMarca().getDescripcion());
            marcaOld.setNombre(subcategoriaMarcaDto.getFkMarca().getNombre());

            daoMarca.update(marcaOld);

            editado = true;

        }
    }

    @Override
    public Boolean getResult() {
        return editado;
    }

}
