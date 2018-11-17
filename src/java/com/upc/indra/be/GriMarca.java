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
@Table(name = "gri_marca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GriMarca.findAll", query = "SELECT g FROM GriMarca g"),
    @NamedQuery(name = "GriMarca.findById", query = "SELECT g FROM GriMarca g WHERE g.id = :id"),
    @NamedQuery(name = "GriMarca.findByNombre", query = "SELECT g FROM GriMarca g WHERE g.nombre = :nombre")})
public class GriMarca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGriMarca", fetch = FetchType.LAZY)
    private Set<GriRecurso> griRecursoSet;

    public GriMarca() {
    }

    public GriMarca(Integer id) {
        this.id = id;
    }

    public GriMarca(Integer id, String nombre) {
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
    public Set<GriRecurso> getGriRecursoSet() {
        return griRecursoSet;
    }

    public void setGriRecursoSet(Set<GriRecurso> griRecursoSet) {
        this.griRecursoSet = griRecursoSet;
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
        if (!(object instanceof GriMarca)) {
            return false;
        }
        GriMarca other = (GriMarca) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upc.indra.be.GriMarca[ id=" + id + " ]";
    }

}