import com.empresag.*;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Response;
import java.util.List;

public class RequestTest {
    @Test
    public void getAllRequests(){
        DaoFiltro daoFiltro = new DaoFiltro();
        List<FiltroEntity> solicitudes = daoFiltro.getAllRequests();

        for (FiltroEntity solicitud: solicitudes) {
            System.out.println(solicitud.getFkSolicitud());
            System.out.println(solicitud.getFkSolicitud().getFkUsuario());
        }
    }

    @Test
    public void getUserRequests(){
        DaoFiltro daoFiltro = new DaoFiltro();
        List<FiltroEntity> solicitudes = daoFiltro.getUserRequests(94);

        for (FiltroEntity solicitud: solicitudes) {
            System.out.println(solicitud.getFkSolicitud());
        }
    }

    @Test
    public void getUserRequest(){
        DaoFiltro daoFiltro = new DaoFiltro();
        FiltroEntity solicitud = null;
        solicitud = daoFiltro.getUserRequest(94L, 4L);

        System.out.println(solicitud);
    }

    @Test
    public void getRequest(){
        DaoFiltro daoFiltro = new DaoFiltro();
        FiltroEntity solicitud = daoFiltro.getCurrentRequest(1L);
        System.out.println(solicitud.getFkSolicitud());
    }

    @Test
    public void modifyStudyStatus(){
        DaoSolicitud daoSolicitud = new DaoSolicitud();
        SolicitudEntity solicitud = daoSolicitud.find(1L, SolicitudEntity.class);
        solicitud.setEstado(1);
        daoSolicitud.update(solicitud);

        Assert.assertNotEquals(0, solicitud.get_id());
    }

    @Test
    public void deleteRequest(){
        DaoSolicitud daoSolicitud = new DaoSolicitud();
        SolicitudEntity solicitud = daoSolicitud.find(1L, SolicitudEntity.class);
        daoSolicitud.delete(solicitud);

        Assert.assertNotEquals(0, solicitud.get_id());
    }
}
