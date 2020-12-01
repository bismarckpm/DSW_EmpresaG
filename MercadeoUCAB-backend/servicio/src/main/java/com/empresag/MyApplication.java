package com.empresag;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class MyApplication extends Application {

    /**
     *
     * TODO - Aca debemos agregar a la aplicacion las clases que queremos utilizar. Con el formato "h.add( Name.class );"
     *
     * */

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> h = new HashSet<>();
        h.add( HelloWorld.class );
        h.add( DispositivoService.class );
        h.add( CategoryService.class );
        h.add( SubcategoryService.class );
        return h;
    }
}
