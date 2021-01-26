package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "analisis", schema = "empresag")
public class AnalisisEntity extends BaseEntity {
    @Basic
    @Column(name = "conclusiones")
    private String conclusiones;

    public String getConclusiones() {
        return conclusiones;
    }

    public void setConclusiones(String conclusiones) {
        this.conclusiones = conclusiones;
    }

}
