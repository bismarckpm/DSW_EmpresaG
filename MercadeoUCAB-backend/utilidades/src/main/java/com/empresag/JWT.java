package com.empresag;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class JWT
{
    private static SecretKey _secretKey;

    private static String _issuer = "empresag.com";
    private static String _algorithm = "HS512";
    private static int _expiration = 8640000;

    static
    {
        setKey();
    }

    private static void setKey()
    {

        try
        {
            _secretKey = Keys.secretKeyFor( SignatureAlgorithm.forName( _algorithm ) );
        }
        catch( Exception e )
        {
            throw e;
        }

    }

    /**
     * Name: createToken
     * Description: Metodo que crea un token JWT en base al subject (see RFC 7519)
     *
     * @param subject Subject del JWT (User ID)
     * @return Token JWT
     */
    public static String createToken( String subject )
    {
        String result;

        try
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime( new Date(  ) );
            calendar.add( Calendar.MILLISECOND, _expiration );

            result = Jwts.builder()
                    .setIssuer( _issuer )
                    .setSubject( subject )
                    .setExpiration( calendar.getTime() )
                    .setNotBefore( new Date(  ) )
                    .setIssuedAt( new Date(  ) )
                    .setId( UUID.randomUUID().toString() )
                    .signWith( _secretKey, SignatureAlgorithm.forName( _algorithm ) )
                    .compact();
        }
        catch ( Exception e )
        {
            throw e;
        }


        return result;
    }

    /**
     * Name: verifyToken
     * Description: Metodo que valida si el token JWT es valido
     *
     * @param token Token JWT
     * @param subject Subject del JWT (User ID)
     */
    public static void verifyToken( String token, String subject )
    {
        try
        {
            Jwts.parser()
                    .requireSubject( subject )
                    .requireIssuer( _issuer )
                    .setSigningKey( _secretKey )
                    .parseClaimsJws( token );
        }
        catch ( Exception e )
        {
            throw e;
        }

    }

    public static String verifyToken(String token )
    {
        String result = "";
        try
        {
            Jws<Claims> claims = Jwts.parser()
                    .requireIssuer( _issuer )
                    .setSigningKey( _secretKey )
                    .parseClaimsJws( token );

            result = claims.getBody().getSubject();
        }
        catch ( Exception e )
        {
            throw e;
        }

        return result;
    }
}
