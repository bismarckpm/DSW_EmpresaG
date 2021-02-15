import com.empresag.*;
import org.junit.Assert;
import org.junit.Test;

public class SessionTest {

    @Test
    public void createUserLDAP()
    {
        try {
            UsuarioDto user = new UsuarioDto();
            user.setEmail("hsamet29@ovh.net");
            user.setPassword("123456789a!");
            RolDto rol = new RolDto();
            rol.set_id(4);

            user.setFkRol(rol);
            DirectorioActivo ldap = new DirectorioActivo();
            ldap.addEntryToLdap(user);
        }
        catch (IndexDatabaseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteUserLDAP()
    {
        UsuarioDto user = new UsuarioDto();
        user.setEmail( "cverde22@gmail.com" );
        DirectorioActivo ldap = new DirectorioActivo();
        ldap.deleteEntry( user );
    }

    @Test
    public void getUserLDAP()
    {
        UsuarioDto user = new UsuarioDto();
        user.setEmail( "cverde22@gmail.com" );
        DirectorioActivo ldap = new DirectorioActivo();
        ldap.getEntry( user );
    }

    @Test
    public void changePassword()
    {
        UsuarioDto user = new UsuarioDto();
        user.setEmail( "hyperschnell11@outlook.sk" );
        user.setPassword( "123456789a!" );
        DirectorioActivo ldap = new DirectorioActivo();
        ldap.changePassword( user );
    }

    @Test
    public void userAuthentication()
    {
        UsuarioDto user = new UsuarioDto();

        DaoUsuario daoUsuario = new DaoUsuario();
        DaoToken daoToken = new DaoToken();

        String token = null;

        TokenEntity tokenEntity = null;
        tokenEntity = daoToken.getUserToken(1);
        UsuarioEntity usuarioEntity = daoUsuario.find(1L, UsuarioEntity.class);
        user.setEmail("hyperschnell11@outlook.sk");
        user.setPassword("123456789a!");
        DirectorioActivo ldap = new DirectorioActivo();

        if (ldap.userAuthentication( user )){
            token = daoToken.getAlphaNumericString(25);
            if (tokenEntity != null){
                tokenEntity.setToken_login(token);
                daoToken.update(tokenEntity);
            }
            else {
                tokenEntity = new TokenEntity();
                tokenEntity.setFkUsuario(usuarioEntity);
                tokenEntity.setToken_login(token);
                daoToken.insert(tokenEntity);
            }
        }

        Assert.assertNotEquals(null, token);
    }
}
