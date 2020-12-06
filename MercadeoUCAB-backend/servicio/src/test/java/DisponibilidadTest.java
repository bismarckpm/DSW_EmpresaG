import com.empresag.DisponibilidadDto;
import com.empresag.DisponibilidadService;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DisponibilidadTest {

    @Test
    public void addDisponibilidadTest(){

        try {
            DisponibilidadService service = new DisponibilidadService();

            String[] str = {"12:00:00 am", "01:00:00 am", "02:00:00 am", "03:00:00 am", "04:00:00 am", "05:00:00 am", "06:00:00 am", "07:00:00 am", "08:00:00 am", "09:00:00 am"
                    , "10:00:00 am", "11:00:00 am", "12:00:00 pm", "01:00:00 pm", "02:00:00 pm", "03:00:00 pm", "04:00:00 pm", "05:00:00 pm", "06:00:00 pm", "07:00:00 pm"
                    , "08:00:00 pm", "09:00:00 pm", "10:00:00 pm", "11:00:00 pm"};

            for (String fecha: str) {

                DateFormat formatter = new SimpleDateFormat("hh:mm:ss a");
                Date date = (Date)formatter.parse(fecha);

                DisponibilidadDto disponibilidadDto = new DisponibilidadDto();
                disponibilidadDto.setHora(new Time(date.getTime()));

                service.addDisponibilidad(disponibilidadDto);
            }

            List<DisponibilidadDto> lista = service.consulta();
            Assert.assertNotEquals( lista, new ArrayList<DisponibilidadDto>());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
