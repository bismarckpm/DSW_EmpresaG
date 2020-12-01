package com.empresag;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class BaseEntity implements Serializable
{

    @Id
    @Column( name = "id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long _id;


    public BaseEntity(long id )
    {
        _id = id;
    }

    public BaseEntity()
    {
    }

    public long get_id()
    {
        return _id;
    }


}
