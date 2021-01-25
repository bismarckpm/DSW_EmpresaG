package com.empresag;


import javax.ws.rs.ApplicationPath;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Response;

@ApplicationPath("/")
public class MyApplication extends Application {

    void validateCredentials(String credential, String userId )
    {
        try
        {
            JWT.verifyToken( credential, userId );
        }
        catch ( Exception e )
        {
            throwException( Response.Status.UNAUTHORIZED, e );
        }
    }


    void verifyParams( Object object )
    {
        if ( object == null )
            throwException( Response.Status.BAD_REQUEST );
    }




    void throwException( Response.Status status, Exception e )
    {
        throw new WebApplicationException(Response.status( status ).entity( e ).build() );
    }


    void throwException( Response.Status status )
    {
        throw new WebApplicationException( Response.status( status ).build() );
    }

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
        h.add( BrandService.class );
        h.add( TypeService.class );
        h.add( PresentationService.class );
        h.add( SesionService.class );
        h.add( GeneroService.class );
        h.add( EdoCivilService.class );
        h.add( LugarService.class );
        h.add( QuestionService.class );
        h.add( OptionService.class );
        h.add( UserService.class );
        h.add( StudyService.class );
        h.add( OcupacionService.class );
        h.add( NivelAcademicoService.class );
        h.add( DisponibilidadService.class );
        h.add( RolService.class );
        h.add( LoginService.class );
        h.add( RecoveryService.class );
        h.add( StudyRequestService.class );
        h.add( AnalyticsService.class );
        h.add( SurveyService.class );
        return h;
    }
}
