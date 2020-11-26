package com.empresag;

public class IndexDatabaseException extends Exception{

    public IndexDatabaseException()
    {
        super("El ID no posee un valor válido");
    }
}
