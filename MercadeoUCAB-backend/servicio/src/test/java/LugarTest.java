
import com.empresag.LugarDto;
import com.empresag.LugarService;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class LugarTest {


    @Test
    public void getPaises(){

        LugarService service = new LugarService();

        List<LugarDto> resultado = service.consulta(0);

        System.out.println(resultado.toString());

        Assert.assertNotNull( resultado );

    }
}
