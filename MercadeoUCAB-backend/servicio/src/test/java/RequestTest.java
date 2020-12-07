import com.empresag.DaoFiltro;
import com.empresag.FiltroEntity;
import org.junit.Test;

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
}
