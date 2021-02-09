package com.empresag.lugar;

import com.empresag.*;

import java.util.ArrayList;
import java.util.List;

public class ComandoConsultarLugar extends ComandoBase {

    private long id = 0;
    private List<LugarDto> listaLugar;

    public ComandoConsultarLugar(long id) {
        this.id = id;
        this.listaLugar = new ArrayList<>();
    }

    @Override
    public void execute() throws Exception {


        try {
            DaoLugar daoLugar = FabricaDao.crearDaoLugar();
            List<LugarEntity> lugares = daoLugar.findTop(id);

            for (int i = 0; i < lugares.size(); i++){
                listaLugar.add(LugarMapper.mapEntityToDto(lugares.get(i)));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public List<LugarDto> getResult() {
        return listaLugar;
    }
}
