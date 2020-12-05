
import com.empresag.GeneroDto;
import com.empresag.GeneroService;
import org.junit.Assert;
import org.junit.Test;

public class GeneroTest {
    
    @Test
    public void addGeneroMujerTest(){

        GeneroService service = new GeneroService();

        GeneroDto generoDto = new GeneroDto();
        generoDto.setNombre("Mujer");

        GeneroDto resultado = service.addGenero(generoDto);
        Assert.assertNotEquals( resultado.get_id(), 0  );

    }

    @Test
    public void addGeneroHombreTest(){

        GeneroService service = new GeneroService();

        GeneroDto generoDto = new GeneroDto();
        generoDto.setNombre("Hombre");

        GeneroDto resultado = service.addGenero(generoDto);
        Assert.assertNotEquals( resultado.get_id(), 0  );

    }

    @Test
    public void addGeneroOtroTest(){

        GeneroService service = new GeneroService();

        GeneroDto generoDto = new GeneroDto();
        generoDto.setNombre("Otro");

        GeneroDto resultado = service.addGenero(generoDto);
        Assert.assertNotEquals( resultado.get_id(), 0  );

    }
}
