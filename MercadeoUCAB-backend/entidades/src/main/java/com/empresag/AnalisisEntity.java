package com.empresag;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "analisis", schema = "empresag")
public class AnalisisEntity extends BaseEntity {
    @Basic
    @Column(name = "conclusiones")
    private String conclusiones;

    public AnalisisEntity(long id) {
        super(id);
    }

    public AnalisisEntity() {

    }

    public String getConclusiones() {
        return conclusiones;
    }

    public void setConclusiones(String conclusiones) {
        this.conclusiones = conclusiones;
    }

}
