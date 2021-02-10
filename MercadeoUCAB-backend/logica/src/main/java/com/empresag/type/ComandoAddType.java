package com.empresag.type;

import com.empresag.*;

public class ComandoAddType extends ComandoBase{

    private MarcaTipoDto marcaTipoDto;
    private MarcaTipoEntity response;

    public ComandoAddType(MarcaTipoDto marcaTipoDto) {
        this.marcaTipoDto = marcaTipoDto;
    }

    @Override
    public void execute() throws Exception {

        DaoMarcaTipo marcaTipo = FabricaDao.crearDaoMarcaTipo();

        DaoTipo tipo = FabricaDao.crearDaoTipo();

        DaoMarca marca = FabricaDao.crearDaoMarca();

        MarcaEntity marcaEntity = marca.find(marcaTipoDto.getFkMarca().get_id(), MarcaEntity.class);
        marcaEntity.setNombre(marcaTipoDto.getFkMarca().getNombre());

        TipoEntity tipoEntity = new TipoEntity();
        tipoEntity.setNombre(marcaTipoDto.getFkTipo().getNombre());
        tipoEntity.setDescripcion(marcaTipoDto.getFkTipo().getDescripcion());
        tipo.insert(tipoEntity);

        MarcaTipoEntity mt = new MarcaTipoEntity();
        mt.setFkTipo(tipoEntity);
        mt.setFkMarca(marcaEntity);
        response = marcaTipo.insert(mt);
    }

    @Override
    public MarcaTipoEntity getResult() {
        return response;
    }
}
