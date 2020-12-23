package com.empresag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import javax.persistence.*;
import java.nio.charset.Charset;
import javax.persistence.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

public class DaoToken extends Dao<TokenEntity> {
    String JPQL = null;
    Query q = null;
    static DaoHandler _handler = new DaoHandler();

    public DaoToken(){
        super(_handler);
    }

    public String getHASH(){
        char[] chars = "abcdefghijklmnopqrstuvwxyz123456789".toCharArray();
        StringBuilder sb = new StringBuilder(10);
        Random random = new Random();
        for (int i = 0; i < 25; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String hash = sb.toString();
        return hash;
    }

    public TokenEntity findToken(long fkUsuario){
        TokenEntity token = new TokenEntity();
        DaoUsuario usuarioDao = new DaoUsuario();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        UsuarioEntity user = usuarioDao.find(fkUsuario,UsuarioEntity.class);

        JPQL = "SELECT t FROM TokenEntity t WHERE t.fkUsuario._id = :fkUsuario";
        q = em.createQuery(JPQL);
        q.setParameter("fkUsuario", fkUsuario);

        List<TokenEntity> tokens = q.getResultList();

        if (!tokens.isEmpty())
            token = tokens.get(0);
        else
            token = null;

        return token;
    }

    public void deleteTokenReset(long id_usuario){
        TokenEntity token = findToken(id_usuario);
        token.setToken_reset(null);
        update(token);
    }

    public void deleteTokenLogin(long id_usuario){
        TokenEntity token = findToken(id_usuario);
        token.setToken_login(null);
        update(token);
    }

    public TokenEntity getTokenByHASH(String hash, boolean isLogin){
        TokenEntity token = new TokenEntity();
        DaoUsuario usuarioDao = new DaoUsuario();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        //UsuarioEntity user = usuarioDao.find(fkUsuario,UsuarioEntity.class);

        if (!isLogin){
            JPQL = "SELECT t FROM TokenEntity t WHERE t.token_reset = :token_reset";
            q = em.createQuery(JPQL);
            q.setParameter("token_reset", hash);
        }
        else{
            JPQL = "SELECT t FROM TokenEntity t WHERE t.token_login = :token_login";
            q = em.createQuery(JPQL);
            q.setParameter("token_login", hash);
        }

        try {
            return (TokenEntity) q.getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    }

    public TokenEntity getUserToken(long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoUsuario daoUsuario = new DaoUsuario();
        UsuarioEntity currentUser = daoUsuario.find(id, UsuarioEntity.class);

        JPQL = "SELECT t FROM TokenEntity t WHERE t.fkUsuario = :usuario";
        q = em.createQuery(JPQL);
        q.setParameter("usuario", currentUser);
        try {
            return (TokenEntity) q.getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    }

    public String getAlphaNumericString(int n)
    {

        // length is bounded by 256 Character
        byte[] array = new byte[256];
        new Random().nextBytes(array);

        String randomString
                = new String(array, Charset.forName("UTF-8"));

        // Create a StringBuffer to store the result
        StringBuilder r = new StringBuilder();

        // remove all spacial char
        String  AlphaNumericString
                = randomString
                .replaceAll("[^A-Za-z0-9]", "");

        // Append first 20 alphanumeric characters
        // from the generated random String into the result
        for (int k = 0; k < AlphaNumericString.length(); k++) {

            if (Character.isLetter(AlphaNumericString.charAt(k))
                    && (n > 0)
                    || Character.isDigit(AlphaNumericString.charAt(k))
                    && (n > 0)) {

                r.append(AlphaNumericString.charAt(k));
                n--;
            }
        }

        // return the resultant string
        return r.toString();
    }
}
