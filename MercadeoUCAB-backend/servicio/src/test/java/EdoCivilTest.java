import com.empresag.EdoCivilDto;
import com.empresag.EdoCivilService;
import org.junit.Assert;
import org.junit.Test;

public class EdoCivilTest {

    @Test
    public void addEdoCivilSolteroTest(){

        EdoCivilService service = new EdoCivilService();

        EdoCivilDto edoCivilDto = new EdoCivilDto();
        edoCivilDto.setNombre("Soltero");

        EdoCivilDto resultado = service.addEdoCivil(edoCivilDto);
        Assert.assertNotEquals( resultado.get_id(), 0  );

    }
    @Test
    public void addEdoCivilCasadoTest(){

        EdoCivilService service = new EdoCivilService();

        EdoCivilDto edoCivilDto = new EdoCivilDto();
        edoCivilDto.setNombre("Casado");

        EdoCivilDto resultado = service.addEdoCivil(edoCivilDto);
        Assert.assertNotEquals( resultado.get_id(), 0  );

    }
    @Test
    public void addEdoCivilDivorciadoTest(){

        EdoCivilService service = new EdoCivilService();

        EdoCivilDto edoCivilDto = new EdoCivilDto();
        edoCivilDto.setNombre("Divorciado");

        EdoCivilDto resultado = service.addEdoCivil(edoCivilDto);
        Assert.assertNotEquals( resultado.get_id(), 0  );

    }
    @Test
    public void addEdoCivilConcubinatoTest(){

        EdoCivilService service = new EdoCivilService();

        EdoCivilDto edoCivilDto = new EdoCivilDto();
        edoCivilDto.setNombre("Concubinato");

        EdoCivilDto resultado = service.addEdoCivil(edoCivilDto);
        Assert.assertNotEquals( resultado.get_id(), 0  );

    }
    @Test
    public void addEdoCivilSeparadoTest(){

        EdoCivilService service = new EdoCivilService();

        EdoCivilDto edoCivilDto = new EdoCivilDto();
        edoCivilDto.setNombre("Separado");

        EdoCivilDto resultado = service.addEdoCivil(edoCivilDto);
        Assert.assertNotEquals( resultado.get_id(), 0  );

    }
    @Test
    public void addEdoCivilViudoTest(){

        EdoCivilService service = new EdoCivilService();

        EdoCivilDto edoCivilDto = new EdoCivilDto();
        edoCivilDto.setNombre("Viudo");

        EdoCivilDto resultado = service.addEdoCivil(edoCivilDto);
        Assert.assertNotEquals( resultado.get_id(), 0  );

    }
}
