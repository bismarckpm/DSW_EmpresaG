package com.empresag;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBHelper {

    private static Connection connection = null;

    /**
    *
    * Esta es una clase estatica o mejor dicho un singleton que nos permitira manejar una sola instancia
    * al DataSource.
    *
    * Ejemplo de llamada:
    * Connection connection = DBHelper.getConnection();
    *
    * */

    public static Connection getConnection(){

        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:/MySqlDS");
            /*
             *
             * En este caso se creo un DataSource en JBoss con el nombre "java:/MySqlDS"
             *
             */
            connection = ds.getConnection();

        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return connection;
    }

}
