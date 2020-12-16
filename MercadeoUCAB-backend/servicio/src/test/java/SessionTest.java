import com.empresag.DirectorioActivo;
import com.empresag.IndexDatabaseException;
import com.empresag.RolDto;
import com.empresag.UsuarioDto;
import org.junit.Assert;
import org.junit.Test;

public class SessionTest {

    @Test
    public void createUserLDAP()
    {
        try {
            UsuarioDto user = new UsuarioDto();
            user.setEmail("angel99castillo@gmail.com");
            user.setPassword("Am101199.");
            RolDto rol = new RolDto();
            rol.set_id(1);

            user.setFkRol(rol);
            DirectorioActivo ldap = new DirectorioActivo();
            ldap.addEntryToLdap(user);
        }
        catch (IndexDatabaseException e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void deleteUserLDAP()
//    {
//        UsuarioDto user = new UsuarioDto();
//        user.setEmail( "cverde22@gmail.com" );
//        DirectorioActivo ldap = new DirectorioActivo();
//        ldap.deleteEntry( user );
//    }
//
//    @Test
//    public void getUserLDAP()
//    {
//        UsuarioDto user = new UsuarioDto();
//        user.setEmail( "cverde22@gmail.com" );
//        DirectorioActivo ldap = new DirectorioActivo();
//        ldap.getEntry( user );
//    }
//
//    @Test
//    public void changePassword()
//    {
//        UsuarioDto user = new UsuarioDto();
//        user.setEmail( "cverde22@gmail.com" );
//        user.setPassword( "Caracas2." );
//        DirectorioActivo ldap = new DirectorioActivo();
//        ldap.changePassword( user );
//    }
//
////    @Test
////    public void userAuthentication()
////    {
////        UsuarioDto user = new UsuarioDto();
//////        user.setEmail( "cverde22@gmail.com" );
//////        user.setPassword( "Caracas2." );
////        user.setEmail( "admin" );
////        user.setPassword( "secret" );
////        DirectorioActivo ldap = new DirectorioActivo();
////        ldap.userAuthentication( user );
////    }
//
//    @Test
//    public void userAuthentication()
//    {
//        UsuarioDto user = new UsuarioDto();
//        user.setEmail("cverde22@gmail.com");
//        user.setPassword("Caracas1.");
//
////        user.setEmail( "admin" );
////        user.setPassword( "secret" );
//        DirectorioActivo ldap = new DirectorioActivo();
//        ldap.userAuthentication( user );
//    }
}
