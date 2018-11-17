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
@Table(name = "tipo_hardware")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoHardware.findAll", query = "SELECT t FROM TipoHardware t"),
    @NamedQuery(name = "TipoHardware.findById", query = "SELECT t FROM TipoHardware t WHERE t.id = :id"),
    @NamedQuery(name = "TipoHardware.findByNombre", query = "SELECT t FROM TipoHardware t WHERE t.nombre = :nombre")})
public class TipoHardware implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoHw", fetch = FetchType.LAZY)
    private Set<Hardware> hardwareSet;

    public TipoHardware() {
    }

    public TipoHardware(Integer id) {
        this.id = id;
    }

    public TipoHardware(Integer id, String nombre) {
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
    public Set<Hardware> getHardwareSet() {
        return hardwareSet;
    }

    public void setHardwareSet(Set<Hardware> hardwareSet) {
        this.hardwareSet = hardwareSet;
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
        if (!(object instanceof TipoHardware)) {
            return false;
        }
        TipoHardware other = (TipoHardware) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upc.indra.be.TipoHardware[ id=" + id + " ]";
    }

}