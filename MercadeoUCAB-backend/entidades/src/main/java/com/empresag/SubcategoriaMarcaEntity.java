package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "subcategoria_marca", schema = "empresag", catalog = "")
public class SubcategoriaMarcaEntity extends BaseEntity{
    private SubcategoriaEntity fkSubcategoria;
    private MarcaEntity fkMarca;

    @ManyToOne
    @JoinColumn(name = "fk_subcategoria")
    public SubcategoriaEntity getFkSubcategoria() {
        return fkSubcategoria;
    }

    public void setFkSubcategoria(SubcategoriaEntity fkSubcategoria) {
        this.fkSubcategoria = fkSubcategoria;
    }

    @ManyToOne
    @JoinColumn(name = "fk_marca")
    public MarcaEntity getFkMarca() {
        return fkMarca;
    }

    public void setFkMarca(MarcaEntity fkMarca) {
        this.fkMarca = fkMarca;
    }

}
