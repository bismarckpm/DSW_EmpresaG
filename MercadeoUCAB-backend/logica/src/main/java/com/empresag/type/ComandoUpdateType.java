package com.empresag.type;

import com.empresag.*;

import javax.ws.rs.core.Response;

public class ComandoUpdateType extends ComandoBase {

    private long id;
    private MarcaTipoDto marcaTipo;
    private String res;

    public ComandoUpdateType(long id, MarcaTipoDto marcaTipo) {
        this.id = id;
        this.marcaTipo = marcaTipo;
    }

    @Override
    public void execute() throws Exception {

        DaoMarcaTipo daoMarcaTipo = FabricaDao.crearDaoMarcaTipo();

        DaoTipo daoTipo = FabricaDao.crearDaoTipo();

        MarcaTipoEntity mt = daoMarcaTipo.find(id, MarcaTipoEntity.class);

        if (mt != null){
            mt.setFkMarca(new MarcaEntity(marcaTipo.getFkMarca().get_id(),
                    marcaTipo.getFkMarca().getNombre()));

            mt.setFkTipo(new TipoEntity(marcaTipo.getFkTipo().get_id()));

            TipoEntity tipo = daoTipo.find(marcaTipo.getFkTipo().get_id(), TipoEntity.class);

            if (tipo != null){
                tipo.setNombre(marcaTipo.getFkTipo().getNombre());
                tipo.setDescripcion(marcaTipo.getFkTipo().getDescripcion());
                daoTipo.update(tipo);
                daoMarcaTipo.update(mt);
                res = "Tipo actualizado";
//                return Response.ok().entity(mt).build();
            }
            else {
//                return Response.status(Response.Status.NOT_FOUND).build();
                res = "Tipo no actualizado";
            }
        }
        else {
//            return Response.status(Response.Status.NOT_FOUND).build();
            res = "Tipo no actualizado";

        }

    }

    @Override
    public String getResult() {
        return res;
    }
}
