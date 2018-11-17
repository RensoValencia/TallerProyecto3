package com.upc.indra.be;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author RENSO
 * @date 06-nov-2018
 */
@Entity
@Table(name = "tipo_software")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoSoftware.findAll", query = "SELECT t FROM TipoSoftware t"),
    @NamedQuery(name = "TipoSoftware.findById", query = "SELECT t FROM TipoSoftware t WHERE t.id = :id"),
    @NamedQuery(name = "TipoSoftware.findByNombre", query = "SELECT t FROM TipoSoftware t WHERE t.nombre = :nombre")})
public class TipoSoftware implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoSw", fetch = FetchType.LAZY)
    private Set<Software> softwareSet;

    public TipoSoftware() {
    }

    public TipoSoftware(Integer id) {
        this.id = id;
    }

    public TipoSoftware(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Set<Software> getSoftwareSet() {
        return softwareSet;
    }

    public void setSoftwareSet(Set<Software> softwareSet) {
        this.softwareSet = softwareSet;
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
        if (!(object instanceof TipoSoftware)) {
            return false;
        }
        TipoSoftware other = (TipoSoftware) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upc.indra.be.TipoSoftware[ id=" + id + " ]";
    }

}