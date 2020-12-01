package com.empresag;


import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * Esta clase "HelloWorld" es definida como un endpoint la cual tiene la posee configurada
 * los metodos GET y POST con la ruta "/hello-world" dependiendo del metodo que se utilice
 * se ejecutara uno u otro metodo.
 *
 * */

@Path("/hello-world")
public class HelloWorld {

    // Especificamos el tipo de metodo a manejar
    @GET
    // Indicamos los tipos de recursos a consumir/producir
    @Produces("text/plain")
    public String getClichedMessage() {
        // Programamos en JAVA
        String fecha = "";
        try(
                Connection con = DBHelper.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement("SELECT now() FROM dual;")
        ){
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                fecha = rs.getString(1);
            }
            con.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "DB:" + fecha;
    }


//    @POST
//    @Consumes("application/json")
//    @Produces("application/json")
//    /*
//    *
//    * En este caso POST manejaremos un tipo de respuesta JSON y a su vez, modificaremos de una mejor forma
//    * la respuesta enviada, indicando codigo de respuesta y contenido.
//    *
//    * */
//    public Response postHolaMundo(JSONObject info){
//        JSONObject respuesta = new JSONObject();
//
//        String fecha = "";
//        try(
//                Connection con = DBHelper.getConnection();
//                PreparedStatement preparedStatement = con.prepareStatement("SELECT now() FROM dual;")
//        ){
//            ResultSet rs = preparedStatement.executeQuery();
//            if (rs.next()){
//                fecha = rs.getString(1);
//            }
//            con.close();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            respuesta.put("msg",e.getMessage());
//            return Response
//                    .status(500)
//                    .entity(respuesta.toString())
//                    .build();
//        }
//
//        respuesta.put("msg",fecha);
//        return Response
//                .status(200)
//                .entity(respuesta.toString())
//                .build();
//    }
}
