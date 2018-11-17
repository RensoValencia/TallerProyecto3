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
@Table(name = "ca_parametros")
@XmlRootElement
@NamedQueries({
@NamedQuery(name = "Parametros.findAll", query = "SELECT p FROM Parametros p"),
    @NamedQuery(name = "Parametros.findById", query = "SELECT p FROM Parametros p WHERE p.id = :id"),
    @NamedQuery(name = "Parametros.findByGrupo", query = "SELECT p FROM Parametros p WHERE p.grupo = :grupo AND p.codigo <> 0"),
    @NamedQuery(name = "Parametros.findByGrupoCodigo", query = "SELECT p FROM Parametros p WHERE p.grupo = :grupo AND p.codigo = :codigo"),
    @NamedQuery(name = "Parametros.findByCodigo", query = "SELECT p FROM Parametros p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Parametros.findByDescripcion", query = "SELECT p FROM Parametros p WHERE p.descripcion = :descripcion")})
public class Parametros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GRUPO")
    private int grupo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private int codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado", fetch = FetchType.LAZY)
    private Set<Capacitacion> capacitacionSet;
    @OneToMany(mappedBy = "idTipoModalidad", fetch = FetchType.LAZY)
    private Set<SolicitudCapacitacion> solicitudCapacitacionSet;
    @OneToMany(mappedBy = "idEstado", fetch = FetchType.LAZY)
    private Set<SolicitudCapacitacion> solicitudCapacitacionSet1;
    
    @OneToMany(mappedBy = "idTipoFormacion", fetch = FetchType.LAZY)
    private Set<Formacion> formacionSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoSala", fetch = FetchType.LAZY)
    private Set<Formacion> formacionSet1;
    @OneToMany(mappedBy = "idTipoModalidad", fetch = FetchType.LAZY)
    private Set<Formacion> formacionSet2;
    @OneToMany(mappedBy = "idTipoRecurso", fetch = FetchType.LAZY)
    private Set<RecursoCapacitacion> recursoCapacitacionSet;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado", fetch = FetchType.LAZY)
    private Set<PlanCapacitacion> planCapacitacionSet1;

    public Parametros() {
    }

    public Parametros(Integer id) {
        this.id = id;
    }

    public Parametros(Integer id, int grupo, int codigo, String descripcion) {
        this.id = id;
        this.grupo = grupo;
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Set<Capacitacion> getCapacitacionSet() {
        return capacitacionSet;
    }

    public void setCapacitacionSet(Set<Capacitacion> capacitacionSet) {
        this.capacitacionSet = capacitacionSet;
    }

    @XmlTransient
    public Set<SolicitudCapacitacion> getSolicitudCapacitacionSet() {
        return solicitudCapacitacionSet;
    }

    public void setSolicitudCapacitacionSet(Set<SolicitudCapacitacion> solicitudCapacitacionSet) {
        this.solicitudCapacitacionSet = solicitudCapacitacionSet;
    }

    @XmlTransient
    public Set<SolicitudCapacitacion> getSolicitudCapacitacionSet1() {
        return solicitudCapacitacionSet1;
    }

    public void setSolicitudCapacitacionSet1(Set<SolicitudCapacitacion> solicitudCapacitacionSet1) {
        this.solicitudCapacitacionSet1 = solicitudCapacitacionSet1;
    }

    @XmlTransient
    public Set<Formacion> getFormacionSet() {
        return formacionSet;
    }

    public void setFormacionSet(Set<Formacion> formacionSet) {
        this.formacionSet = formacionSet;
    }

    @XmlTransient
    public Set<Formacion> getFormacionSet1() {
        return formacionSet1;
    }

    public void setFormacionSet1(Set<Formacion> formacionSet1) {
        this.formacionSet1 = formacionSet1;
    }

    @XmlTransient
    public Set<Formacion> getFormacionSet2() {
        return formacionSet2;
    }

    public void setFormacionSet2(Set<Formacion> formacionSet2) {
        this.formacionSet2 = formacionSet2;
    }

    @XmlTransient
    public Set<RecursoCapacitacion> getRecursoCapacitacionSet() {
        return recursoCapacitacionSet;
    }

    public void setRecursoCapacitacionSet(Set<RecursoCapacitacion> recursoCapacitacionSet) {
        this.recursoCapacitacionSet = recursoCapacitacionSet;
    }

    @XmlTransient
    public Set<PlanCapacitacion> getPlanCapacitacionSet1() {
        return planCapacitacionSet1;
    }

    public void setPlanCapacitacionSet1(Set<PlanCapacitacion> planCapacitacionSet1) {
        this.planCapacitacionSet1 = planCapacitacionSet1;
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
        if (!(object instanceof Parametros)) {
            return false;
        }
        Parametros other = (Parametros) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upc.indra.be.Parametros[ id=" + id + " ]";
    }

}