import com.empresag.UsuarioDto;
import com.empresag.UsuarioEntity;
import com.empresag.DaoUsuario;
import com.empresag.LoginService;
import org.junit.Assert;
import org.junit.Test;

public class LoginTest {
    @Test
    public void getUsuario(){
        LoginService login = new LoginService();
        DaoUsuario usuarioDao = new DaoUsuario();

        String userString = "";
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setEmail("hyperschnell11@outlook.sk");
        usuarioDto.setPassword("123456789");
        UsuarioEntity user = login.currentUser(usuarioDto);
        if (user != null)
            userString = user.toString();

        UsuarioEntity userPrueba = usuarioDao.find((long)92, UsuarioEntity.class);

        Assert.assertEquals( userPrueba.toString(), userString );
    }
}
