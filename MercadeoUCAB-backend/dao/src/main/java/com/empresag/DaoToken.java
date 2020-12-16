package com.empresag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Random;

public class DaoToken extends Dao<TokenEntity> {
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    String JPQL = null;
    Query q = null;

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

        return token;
    }

    public void deleteTokenReset(long id_usuario){
        TokenEntity token = findToken(id_usuario);
        token.setToken_reset(null);
        update(token);
    }

    public TokenEntity getTokenByHASH(String token_reset){
        TokenEntity token = new TokenEntity();
        DaoUsuario usuarioDao = new DaoUsuario();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        //UsuarioEntity user = usuarioDao.find(fkUsuario,UsuarioEntity.class);

        JPQL = "SELECT t FROM TokenEntity t WHERE t.token_reset = :token_reset";
        q = em.createQuery(JPQL);
        q.setParameter("token_reset", token_reset);

        List<TokenEntity> tokens = q.getResultList();

        if (!tokens.isEmpty())
            token = tokens.get(0);

        return token;
    }
}
