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
        UsuarioEntity user = login.currentUser("angel99castillo@gmail.com", "Am101199.");
        if (user != null)
            userString = user.toString();

        UsuarioEntity userPrueba = usuarioDao.find((long)1,UsuarioEntity.class);

        Assert.assertEquals( userPrueba.toString(), userString );
    }
}
