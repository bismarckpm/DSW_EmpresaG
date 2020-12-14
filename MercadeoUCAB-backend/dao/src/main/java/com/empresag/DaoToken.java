package com.empresag;

import javax.persistence.*;
import java.nio.charset.Charset;
import java.util.Random;

public class DaoToken extends Dao<TokenEntity> {
    String JPQL = null;
    Query q = null;
    static DaoHandler _handler = new DaoHandler();
    public DaoToken(){
        super(_handler);
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
