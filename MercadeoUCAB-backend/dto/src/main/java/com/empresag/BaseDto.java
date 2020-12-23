package com.empresag;

public class BaseDto {
    private long _id;

    public BaseDto() {}

    public BaseDto(long id) throws IndexDatabaseException {
        set_id(id);
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long id) throws IndexDatabaseException {

//        if ( id > 0 )
//        {
            _id = id;
//        }
//        else
//        {
//            throw new IndexDatabaseException();
//        }
    }
}
