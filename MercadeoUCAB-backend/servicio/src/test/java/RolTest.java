import com.empresag.RolDto;
import com.empresag.RolService;
import org.junit.Assert;
import org.junit.Test;

public class RolTest {

    @Test
    public void addRolEncuestadoTest(){

        RolService service = new RolService();

        RolDto rolDto = new RolDto();
        rolDto.setNombre("Encuestado");

        RolDto resultado = service.addRol(rolDto);
        Assert.assertNotEquals( resultado.get_id(), 0  );

    }

    @Test
    public void addRolClienteTest(){

        RolService service = new RolService();

        RolDto rolDto = new RolDto();
        rolDto.setNombre("Cliente");

        RolDto resultado = service.addRol(rolDto);
        Assert.assertNotEquals( resultado.get_id(), 0  );

    }

    @Test
    public void addRolAnalistaTest(){

        RolService service = new RolService();

        RolDto rolDto = new RolDto();
        rolDto.setNombre("Analista");

        RolDto resultado = service.addRol(rolDto);
        Assert.assertNotEquals( resultado.get_id(), 0  );

    }

    @Test
    public void addRolAdministradorTest(){

        RolService service = new RolService();

        RolDto rolDto = new RolDto();
        rolDto.setNombre("Administrador");

        RolDto resultado = service.addRol(rolDto);
        Assert.assertNotEquals( resultado.get_id(), 0  );

    }
}
