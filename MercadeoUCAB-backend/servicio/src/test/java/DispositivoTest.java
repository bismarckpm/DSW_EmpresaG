import com.empresag.DispositivoDto;
import com.empresag.DispositivoService;
import org.junit.Assert;
import org.junit.Test;

public class DispositivoTest {

    @Test
    public void addDispositivoTabletTest(){

        DispositivoService service = new DispositivoService();

        DispositivoDto dispositivoDto = new DispositivoDto();
        dispositivoDto.setNombre("Tablet");

        DispositivoDto resultado = service.addDispositivo(dispositivoDto);
        Assert.assertNotEquals( resultado.get_id(), 0  );

    }

    @Test
    public void addDispositivoPCTest(){

        DispositivoService service = new DispositivoService();

        DispositivoDto dispositivoDto = new DispositivoDto();
        dispositivoDto.setNombre("PC");

        DispositivoDto resultado = service.addDispositivo(dispositivoDto);
        Assert.assertNotEquals( resultado.get_id(), 0  );

    }

    @Test
    public void addDispositivoLaptopTest(){

        DispositivoService service = new DispositivoService();

        DispositivoDto dispositivoDto = new DispositivoDto();
        dispositivoDto.setNombre("Laptop");

        DispositivoDto resultado = service.addDispositivo(dispositivoDto);
        Assert.assertNotEquals( resultado.get_id(), 0  );

    }

    @Test
    public void addDispositivoTelefonoTest(){

        DispositivoService service = new DispositivoService();

        DispositivoDto dispositivoDto = new DispositivoDto();
        dispositivoDto.setNombre("Teléfono");

        DispositivoDto resultado = service.addDispositivo(dispositivoDto);
        Assert.assertNotEquals( resultado.get_id(), 0  );

    }
}
