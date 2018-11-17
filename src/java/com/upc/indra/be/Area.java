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
@Table(name = "ss_area")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Area.findAll", query = "SELECT a FROM Area a"),
    @NamedQuery(name = "Area.findById", query = "SELECT a FROM Area a WHERE a.id = :id"),
    @NamedQuery(name = "Area.findByNombre", query = "SELECT a FROM Area a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Area.findByEstado", query = "SELECT a FROM Area a WHERE a.estado = :estado")})
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "ESTADO")
    private Character estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArea", fetch = FetchType.LAZY)
    private Set<IndicadorArea> indicadorAreaSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArea", fetch = FetchType.LAZY)
    private Set<SolicitudCapacitacion> solicitudCapacitacionSet;
    @OneToMany(mappedBy = "idArea", fetch = FetchType.LAZY)
    private Set<Rol> rolSet;
    @OneToMany(mappedBy = "idArea", fetch = FetchType.LAZY)
    private Set<Formacion> formacionSet;

    public Area() {
    }

    public Area(Integer id) {
        this.id = id;
    }

    public Area(Integer id, String nombre) {
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

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Set<IndicadorArea> getIndicadorAreaSet() {
        return indicadorAreaSet;
    }

    public void setIndicadorAreaSet(Set<IndicadorArea> indicadorAreaSet) {
        this.indicadorAreaSet = indicadorAreaSet;
    }

    @XmlTransient
    public Set<SolicitudCapacitacion> getSolicitudCapacitacionSet() {
        return solicitudCapacitacionSet;
    }

    public void setSolicitudCapacitacionSet(Set<SolicitudCapacitacion> solicitudCapacitacionSet) {
        this.solicitudCapacitacionSet = solicitudCapacitacionSet;
    }

    @XmlTransient
    public Set<Rol> getRolSet() {
        return rolSet;
    }

    public void setRolSet(Set<Rol> rolSet) {
        this.rolSet = rolSet;
    }

    @XmlTransient
    public Set<Formacion> getFormacionSet() {
        return formacionSet;
    }

    public void setFormacionSet(Set<Formacion> formacionSet) {
        this.formacionSet = formacionSet;
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
        if (!(object instanceof Area)) {
            return false;
        }
        Area other = (Area) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upc.indra.be.Area[ id=" + id + " ]";
    }

}