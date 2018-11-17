package com.upc.indra.be;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

/**
 * @author RENSO
 * @date 06-nov-2018
 */
@Entity
@Table(name = "ca_materiales_escritorio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaterialesEscritorio.findAll", query = "SELECT m FROM MaterialesEscritorio m"),
    @NamedQuery(name = "MaterialesEscritorio.findById", query = "SELECT m FROM MaterialesEscritorio m WHERE m.id = :id"),
    @NamedQuery(name = "MaterialesEscritorio.findByCodigo", query = "SELECT m FROM MaterialesEscritorio m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "MaterialesEscritorio.findByTipMatAndMarEsc", query = "SELECT m FROM MaterialesEscritorio m WHERE m.idTipoMat = :tipoMat AND m.idMarca = :marca AND m.descripcion LIKE :descripcion"),
    @NamedQuery(name = "MaterialesEscritorio.findByDescripcion", query = "SELECT m FROM MaterialesEscritorio m WHERE m.descripcion = :descripcion")})
public class MaterialesEscritorio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CODIGO")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinColumn(name = "ID_MARCA", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MarcaMaterial idMarca;
    @JoinColumn(name = "ID_TIPO_MAT", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoMaterial idTipoMat;
    
    @Transient @Getter @Setter private int cantidad;
    @Transient @Getter @Setter private double valor;

    public MaterialesEscritorio() {
    }

    public MaterialesEscritorio(Integer id) {
        this.id = id;
    }

    public MaterialesEscritorio(Integer id, String codigo, String descripcion) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public MarcaMaterial getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(MarcaMaterial idMarca) {
        this.idMarca = idMarca;
    }

    public TipoMaterial getIdTipoMat() {
        return idTipoMat;
    }

    public void setIdTipoMat(TipoMaterial idTipoMat) {
        this.idTipoMat = idTipoMat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaterialesEscritorio)) {
            return false;
        }
        MaterialesEscritorio other = (MaterialesEscritorio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upc.indra.be.MaterialesEscritorio[ id=" + id + " ]";
    }

}