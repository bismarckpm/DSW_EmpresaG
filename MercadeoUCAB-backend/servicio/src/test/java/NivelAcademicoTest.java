
import com.empresag.NivelAcademicoDto;
import com.empresag.NivelAcademicoService;
import org.junit.Assert;
import org.junit.Test;

public class  NivelAcademicoTest {

    @Test
    public void addOcupacionPrimariaTest(){

        NivelAcademicoService service = new NivelAcademicoService();

        NivelAcademicoDto nivelAcademicoDto = new NivelAcademicoDto();
        nivelAcademicoDto.setNombre("Primaria");

        NivelAcademicoDto resultado = service.addNivelAcademico(nivelAcademicoDto);
        Assert.assertNotEquals( resultado.get_id(), 0  );

    }

    @Test
    public void addOcupacionSecundariaTest(){

        NivelAcademicoService service = new NivelAcademicoService();

        NivelAcademicoDto nivelAcademicoDto = new NivelAcademicoDto();
        nivelAcademicoDto.setNombre("Secundaria");

        NivelAcademicoDto resultado = service.addNivelAcademico(nivelAcademicoDto);
        Assert.assertNotEquals( resultado.get_id(), 0  );

    }

    @Test
    public void addOcupacionTecnicoSuperiorTest(){

        NivelAcademicoService service = new NivelAcademicoService();

        NivelAcademicoDto nivelAcademicoDto = new NivelAcademicoDto();
        nivelAcademicoDto.setNombre("Técnico Superior");

        NivelAcademicoDto resultado = service.addNivelAcademico(nivelAcademicoDto);
        Assert.assertNotEquals( resultado.get_id(), 0  );

    }

    @Test
    public void addOcupacionUniversitarioTest(){

        NivelAcademicoService service = new NivelAcademicoService();

        NivelAcademicoDto nivelAcademicoDto = new NivelAcademicoDto();
        nivelAcademicoDto.setNombre("Universitario");

        NivelAcademicoDto resultado = service.addNivelAcademico(nivelAcademicoDto);
        Assert.assertNotEquals( resultado.get_id(), 0  );

    }

    @Test
    public void addOcupacionMaestriaTest(){

        NivelAcademicoService service = new NivelAcademicoService();

        NivelAcademicoDto nivelAcademicoDto = new NivelAcademicoDto();
        nivelAcademicoDto.setNombre("Maestría");

        NivelAcademicoDto resultado = service.addNivelAcademico(nivelAcademicoDto);
        Assert.assertNotEquals( resultado.get_id(), 0  );

    }

    @Test
    public void addOcupacionDoctoradoTest(){

        NivelAcademicoService service = new NivelAcademicoService();

        NivelAcademicoDto nivelAcademicoDto = new NivelAcademicoDto();
        nivelAcademicoDto.setNombre("Doctorado");

        NivelAcademicoDto resultado = service.addNivelAcademico(nivelAcademicoDto);
        Assert.assertNotEquals( resultado.get_id(), 0  );

    }
}
