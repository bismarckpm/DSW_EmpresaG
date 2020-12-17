package com.empresag;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;

public class DirectorioActivo
{

    private DirContext _ldapContext;
    private String _url = "ldap://127.0.0.1:10389";
    private String _connType =  "simple";
    private String _directory =  "ou=users,o=mercadeoucab";
    private String _userDirectory =  "cn=%s";
    private String _user =  "admin";
    private String _password =  "secret";

    /*
    Method to connect with the ldap
     */
    public DirectorioActivo()
    {}

    private void connectLDAP(String user, String password)
    {
        try
        {
            Hashtable<String, String> environment = new Hashtable<String, String>();
            environment.put( Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory" );
            environment.put( Context.PROVIDER_URL, _url );
            environment.put( Context.SECURITY_AUTHENTICATION, _connType );
            environment.put( Context.SECURITY_PRINCIPAL, String.format( "uid=%s,ou=system", user ) );
            environment.put( Context.SECURITY_CREDENTIALS, password );
            _ldapContext = new InitialDirContext( environment );
        }
        catch ( Exception e )
        {

        }
    }

    private void disconnectLDAP()
    {
        try
        {
            _ldapContext.close();
        }
        catch ( Exception e )
        {

        }
    }

    /*
      Method that adds users to ldap
     */
    public void addEntryToLdap(UsuarioDto user)
    {

        try
        {
            connectLDAP( _user, _password );
            Attribute oc = new BasicAttribute( "objectClass" );
            oc.add( "top" );
            oc.add( "person" );
            SimpleDateFormat format = new SimpleDateFormat( "yyyyMMddHHmm" );
            BasicAttributes entry = new BasicAttributes();
            entry.put( oc );
            entry.put( new BasicAttribute( "cn", user.getEmail() ) );
            entry.put( new BasicAttribute( "sn", user.getEmail() ) );
//            entry.put( new BasicAttribute( "description", String.valueOf(user.getFkRol().get_id()) ) );
            entry.put( new BasicAttribute( "userpassword", user.getPassword() ) );
            entry.put( new BasicAttribute( "pwdLastSuccess", format.format( new Date() ) + "Z" ) );
            _ldapContext.createSubcontext( String.format( _userDirectory + "," + _directory, user.getEmail()), entry );

        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    /*
     Method that remove users to ldap
    */
    public void deleteEntry(UsuarioDto user)
    {
        try
        {
            connectLDAP( _user, _password );
            _ldapContext.destroySubcontext( String.format(_userDirectory + "," + _directory, user.getEmail()));
        }
        catch ( Exception exception )
        {
            exception.printStackTrace();
        }
        finally
        {
            disconnectLDAP();
        }
    }

    /*
     Method that obtains user data from ldap
    */
    public void getEntry(UsuarioDto user)
    {
        try
        {
            connectLDAP( _user, _password );
            SearchControls searcCon = new SearchControls();
            searcCon.setSearchScope( SearchControls.SUBTREE_SCOPE );
            NamingEnumeration results =
                    _ldapContext.search( _directory, String.format(_userDirectory, user.getEmail()), searcCon );
            if ( results != null )
            {
                while ( results.hasMore() )
                {
                    SearchResult res = ( SearchResult ) results.next();
                    Attributes attrs = res.getAttributes ();
                    System.out.println(attrs.get("cn"));
                    System.out.println(attrs.get("sn"));
                    Attribute userPassword = attrs.get("userpassword");
                    String pwd = new String((byte[]) userPassword.get());
                    System.out.println(pwd);
                    System.out.println(attrs.get("pwdLastSuccess"));
                    System.out.println(attrs.get("description"));
                }
            }
            else
            {
                System.out.println( "fail" );
            }
        }
        catch ( Exception exception )
        {
            exception.printStackTrace();
        }
        finally
        {
            disconnectLDAP();
        }
    }

    public void changePassword(UsuarioDto user)
    {
        try
        {
            connectLDAP( _user, _password );
            ModificationItem[] modificationItems = new ModificationItem[ 1 ];
            modificationItems[ 0 ] = new ModificationItem( DirContext.REPLACE_ATTRIBUTE,
                                                           new BasicAttribute( "userpassword", user.getPassword()
                                                           ) );
//            modificationItems[ 1 ] = new ModificationItem( DirContext.REPLACE_ATTRIBUTE,
//                                                           new BasicAttribute( "description", "NUEVO"
//                                                           ) );
            _ldapContext.modifyAttributes(String.format(_userDirectory + "," + _directory, user.getEmail
                    ()), modificationItems );
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            disconnectLDAP();
        }
    }

    public boolean userAuthentication(UsuarioDto user) {

        String identifyingAttribute = "cn";
        connectLDAP(_user,_password);

        try {
            String[] attributeFilter = { identifyingAttribute };
            SearchControls sc = new SearchControls();
            sc.setReturningAttributes(attributeFilter);
            sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

            String searchFilter = "(" + identifyingAttribute + "=" + user.getEmail() + ")";
            NamingEnumeration<SearchResult> results = _ldapContext.search(_directory, searchFilter, sc);

            if (results.hasMore()) {
                SearchResult result = results.next();
                String distinguishedName = result.getNameInNamespace();

                Properties authEnv = new Properties();
                authEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
                authEnv.put(Context.PROVIDER_URL, _url);
                authEnv.put(Context.SECURITY_PRINCIPAL, distinguishedName);
                authEnv.put(Context.SECURITY_CREDENTIALS, user.getPassword());
                new InitialDirContext(authEnv);

                System.out.println("Authentication successful");
                return true;
            }
        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            disconnectLDAP();
        }
        System.err.println("Authentication failed");
        return false;
    }

}
