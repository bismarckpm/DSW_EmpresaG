package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "analisis", schema = "empresag", catalog = "")
public class AnalisisEntity extends BaseEntity {
    private String conclusiones;

    @Basic
    @Column(name = "conclusiones")
    public String getConclusiones() {
        return conclusiones;
    }

    public void setConclusiones(String conclusiones) {
        this.conclusiones = conclusiones;
    }

}
