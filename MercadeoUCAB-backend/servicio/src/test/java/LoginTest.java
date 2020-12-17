import com.empresag.*;
import org.junit.Assert;
import org.junit.Test;

public class LoginTest {
    @Test
    public void getUsuario() throws IndexDatabaseException {
        LoginService login = new LoginService();
        DaoUsuario usuarioDao = new DaoUsuario();

        String userString = "";
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setEmail("hyperschnell11@outlook.sk");
        usuarioDto.setPassword("123456789");
        UsuarioDto user = login.currentUser(usuarioDto);
        if (user != null)
            userString = user.toString();

        UsuarioEntity userPrueba = usuarioDao.find((long)92, UsuarioEntity.class);

    @Test
    public void changeTest(){
        RecoveryService rec = new RecoveryService();
        rec.changePassword("angel99castillo@gmail.com","Caracas1.");
    }
}
