package com.empresag.type;

import com.empresag.ComandoBase;
import com.empresag.DaoMarcaTipo;
import com.empresag.FabricaDao;
import com.empresag.MarcaTipoEntity;

import javax.ws.rs.core.Response;

public class ComandoDeleteType extends ComandoBase {

    private long id;
    private String response;

    public ComandoDeleteType(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws Exception {

        DaoMarcaTipo daoMarcaTipo = FabricaDao.crearDaoMarcaTipo();
        MarcaTipoEntity mt = daoMarcaTipo.find(id, MarcaTipoEntity.class);

        if (mt != null){
            daoMarcaTipo.delete(mt);
            response = "Tipo borrado";
        }
        else {
            response = "Tipo no borrado";
        }
    }

    @Override
    public String getResult() {
        return response;
    }
}
