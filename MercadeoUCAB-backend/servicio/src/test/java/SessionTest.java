import com.empresag.*;
import org.junit.Assert;
import org.junit.Test;

public class SessionTest {

    @Test
    public void createUserLDAP()
    {
        try {
            UsuarioDto user = new UsuarioDto();
            DirectorioActivo ldap = new DirectorioActivo();
            RolDto rol = new RolDto();

            user.setEmail("hyperschnell11@outlook.sk");
            user.setPassword("123456789a!");
            rol.set_id(1);

            user.setFkRol(rol);
            ldap.addEntryToLdap(user);

            user.setEmail("eternalwaltz@gmail.ca");
            user.setPassword("123456789a!");
            rol.set_id(3);

            user.setFkRol(rol);
            ldap.addEntryToLdap(user);

            user.setEmail("iamnopsycho@protonmail.com");
            user.setPassword("123456789a!");
            rol.set_id(2);

            user.setFkRol(rol);
            ldap.addEntryToLdap(user);

            user.setEmail("skthereaper@gmail.ca");
            user.setPassword("123456789a!");
            rol.set_id(4);

            user.setFkRol(rol);
            ldap.addEntryToLdap(user);

            user.setEmail("bumpsindanight@gmail.ca");
            user.setPassword("123456789a!");
            rol.set_id(4);

            user.setFkRol(rol);
            ldap.addEntryToLdap(user);

            user.setEmail("itsmeyerz@gmail.com");
            user.setPassword("123456789a!");
            rol.set_id(4);

            user.setFkRol(rol);
            ldap.addEntryToLdap(user);

            user.setEmail("angel99castillo@gmail.com");
            user.setPassword("123456789a!");
            rol.set_id(4);

            user.setFkRol(rol);
            ldap.addEntryToLdap(user);

            user.setEmail("jhwatzke@gmail.ca");
            user.setPassword("123456789a!");
            rol.set_id(3);

            user.setFkRol(rol);
            ldap.addEntryToLdap(user);

            user.setEmail("yungtricky@gmail.ca");
            user.setPassword("123456789a!");
            rol.set_id(4);

            user.setFkRol(rol);
            ldap.addEntryToLdap(user);

            user.setEmail("saintfaline@gmail.ca");
            user.setPassword("123456789a!");
            rol.set_id(4);

            user.setFkRol(rol);
            ldap.addEntryToLdap(user);

            user.setEmail("mbrumbye2i@webnode.com");
            user.setPassword("123456789a!");
            rol.set_id(4);

            user.setFkRol(rol);
            ldap.addEntryToLdap(user);

            user.setEmail("hthorne2h@amazon.de");
            user.setPassword("123456789a!");
            rol.set_id(4);

            user.setFkRol(rol);
            ldap.addEntryToLdap(user);

            user.setEmail("ascriver2f@amazon.co.jp");
            user.setPassword("123456789a!");
            rol.set_id(4);

            user.setFkRol(rol);
            ldap.addEntryToLdap(user);

            user.setEmail("hsamet29@ovh.net");
            user.setPassword("123456789a!");
            rol.set_id(4);

            user.setFkRol(rol);
            ldap.addEntryToLdap(user);

            user.setEmail("cverde22@gmail.com");
            user.setPassword("Caracas1.");
            rol.set_id(2);

            user.setFkRol(rol);
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
        user.setEmail( "cverde22@gmail.com" );
        user.setPassword( "Caracas1." );
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
        tokenEntity = daoToken.getUserToken(94);
        UsuarioEntity usuarioEntity = daoUsuario.find(94L, UsuarioEntity.class);
        user.setEmail("hyperschnell11@outlook.sk");
        user.setPassword("123456789");
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
