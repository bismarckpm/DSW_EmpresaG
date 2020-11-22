package com.empresag;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class MyApplication extends Application {

    /**
     *
     * Aca debemos agregar a la aplicacion las clases que queremos utilizar.
     *
     * */

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> h = new HashSet<>();
        h.add( HelloWorld.class );
        h.add( DispositivoService.class );
        /* h.add( Name.class ); */
        return h;
    }
}