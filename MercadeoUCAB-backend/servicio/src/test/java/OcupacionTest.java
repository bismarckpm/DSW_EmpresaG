import com.empresag.OcupacionDto;
import com.empresag.OcupacionService;
import org.junit.Assert;
import org.junit.Test;

public class OcupacionTest {

    @Test
    public void addOcupacionMaestroTest(){

        OcupacionService service = new OcupacionService();

        OcupacionDto ocupacionDto = new OcupacionDto();
        ocupacionDto.setNombre("Maestro");

        OcupacionDto resultado = service.addOcupacion(ocupacionDto);
        Assert.assertNotEquals( resultado.get_id(), 0  );

    }

    @Test
    public void addOcupacionCarpinteroTest(){

        OcupacionService service = new OcupacionService();

        OcupacionDto ocupacionDto = new OcupacionDto();
        ocupacionDto.setNombre("Carpintero");

        OcupacionDto resultado = service.addOcupacion(ocupacionDto);
        Assert.assertNotEquals( resultado.get_id(), 0  );

    }

    @Test
    public void addOcupacionAlbanilTest(){

        OcupacionService service = new OcupacionService();

        OcupacionDto ocupacionDto = new OcupacionDto();
        ocupacionDto.setNombre("Albanil");

        OcupacionDto resultado = service.addOcupacion(ocupacionDto);
        Assert.assertNotEquals( resultado.get_id(), 0  );

    }

    @Test
    public void addOcupacionAbogadoTest(){

        OcupacionService service = new OcupacionService();

        OcupacionDto ocupacionDto = new OcupacionDto();
        ocupacionDto.setNombre("Abogado");

        OcupacionDto resultado = service.addOcupacion(ocupacionDto);
        Assert.assertNotEquals( resultado.get_id(), 0  );

    }

    @Test
    public void addOcupacionDoctorTest(){

        OcupacionService service = new OcupacionService();

        OcupacionDto ocupacionDto = new OcupacionDto();
        ocupacionDto.setNombre("Doctor");

        OcupacionDto resultado = service.addOcupacion(ocupacionDto);
        Assert.assertNotEquals( resultado.get_id(), 0  );

    }
}
