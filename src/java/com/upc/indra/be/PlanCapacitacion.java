package com.upc.indra.be;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Getter;
import lombok.Setter;

/**
 * @author RENSO
 * @date 06-nov-2018
 */
@Entity
@Table(name = "ca_plan_capacitacion")
@XmlRootElement
@NamedQueries({
@NamedQuery(name = "PlanCapacitacion.findAll", query = "SELECT p FROM PlanCapacitacion p"),
    @NamedQuery(name = "PlanCapacitacion.findById", query = "SELECT p FROM PlanCapacitacion p WHERE p.id = :id"),
    @NamedQuery(name = "PlanCapacitacion.findByPeriodo", query = "SELECT p FROM PlanCapacitacion p WHERE p.periodo = :periodo"),
    @NamedQuery(name = "PlanCapacitacion.findByFechaElaboracion", query = "SELECT p FROM PlanCapacitacion p WHERE p.fechaElaboracion = :fechaElaboracion"),
    @NamedQuery(name = "PlanCapacitacion.findByFechaEjecucion", query = "SELECT p FROM PlanCapacitacion p WHERE p.fechaEjecucion = :fechaEjecucion"),
    @NamedQuery(name = "PlanCapacitacion.findByFechaAprobacion", query = "SELECT p FROM PlanCapacitacion p WHERE p.fechaAprobacion = :fechaAprobacion"),
    @NamedQuery(name = "PlanCapacitacion.findByTipoPeriodoAndEstado", 
            query = "SELECT p FROM PlanCapacitacion p WHERE p.periodo = :perido AND p.estado.id = :estado ORDER BY p.id DESC")})
public class PlanCapacitacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERIODO")
    private int periodo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ELABORACION")
    @Temporal(TemporalType.DATE)
    private Date fechaElaboracion;
    @Column(name = "FECHA_EJECUCION")
    @Temporal(TemporalType.DATE)
    private Date fechaEjecucion;
    @Column(name = "FECHA_APROBACION")
    @Temporal(TemporalType.DATE)
    private Date fechaAprobacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlanCapacitacion", fetch = FetchType.LAZY)
    private Set<Capacitacion> capacitacionSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlanificacion", fetch = FetchType.LAZY)
    private Set<RecursoCapacitacion> recursoCapacitacionSet;
    
    
    @JoinColumn(name = "ESTADO", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Parametros estado;
    
    @Column(name = "OBSERVACION")
    @Getter @Setter private String observacion;

    public PlanCapacitacion() {
    }

    public PlanCapacitacion(Integer id) {
        this.id = id;
    }

    public PlanCapacitacion(Integer id, int periodo, Date fechaElaboracion) {
        this.id = id;
        this.periodo = periodo;
        this.fechaElaboracion = fechaElaboracion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public Date getFechaElaboracion() {
        return fechaElaboracion;
    }

    public void setFechaElaboracion(Date fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
    }

    public Date getFechaEjecucion() {
        return fechaEjecucion;
    }

    public void setFechaEjecucion(Date fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    @XmlTransient
    public Set<Capacitacion> getCapacitacionSet() {
        return capacitacionSet;
    }

    public void setCapacitacionSet(Set<Capacitacion> capacitacionSet) {
        this.capacitacionSet = capacitacionSet;
    }

    @XmlTransient
    public Set<RecursoCapacitacion> getRecursoCapacitacionSet() {
        return recursoCapacitacionSet;
    }

    public void setRecursoCapacitacionSet(Set<RecursoCapacitacion> recursoCapacitacionSet) {
        this.recursoCapacitacionSet = recursoCapacitacionSet;
    }

    public Parametros getEstado() {
        return estado;
    }

    public void setEstado(Parametros estado) {
        this.estado = estado;
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
        if (!(object instanceof PlanCapacitacion)) {
            return false;
        }
        PlanCapacitacion other = (PlanCapacitacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upc.indra.be.PlanCapacitacion[ id=" + id + " ]";
    }

}