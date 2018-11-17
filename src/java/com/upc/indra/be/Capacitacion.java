package com.upc.indra.be;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

/**
 * @author RENSO
 * @date 06-nov-2018
 */
@Entity
@Table(name = "ca_capacitacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Capacitacion.findAll", query = "SELECT c FROM Capacitacion c"),
    @NamedQuery(name = "Capacitacion.findById", query = "SELECT c FROM Capacitacion c WHERE c.id = :id"),
    @NamedQuery(name = "Capacitacion.findByIdEsp", query = "SELECT c FROM Capacitacion c WHERE c.idDetSol.idFormacion.idTipoFormacion = :tipoFormacion"),
    @NamedQuery(name = "Capacitacion.findByIdEsp1", query = "SELECT c FROM Capacitacion c WHERE c.idDetSol.idFormacion.idTipoModalidad = :tipoFormacion AND c.idPlanCapacitacion = :idPlan"),
    @NamedQuery(name = "Capacitacion.findByIdPlanCapacitacion", query = "SELECT c FROM Capacitacion c WHERE c.idPlanCapacitacion = :planCapacitacion"),
    @NamedQuery(name = "Capacitacion.findByFechaInicio", query = "SELECT c FROM Capacitacion c WHERE c.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Capacitacion.findByFechaFin", query = "SELECT c FROM Capacitacion c WHERE c.fechaFin = :fechaFin")})
public class Capacitacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @JoinColumn(name = "ESTADO", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Parametros estado;
    @JoinColumn(name = "ID_DET_SOL", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DetalleSolicitud idDetSol;
    @JoinColumn(name = "ID_PLAN_CAPACITACION", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PlanCapacitacion idPlanCapacitacion;
    
    @Transient @Getter @Setter private PerfilCapacitador perfilCapacitador;
    @Transient @Getter @Setter private int cantidad;
    @Transient @Getter @Setter private double valor;
    @Transient @Getter @Setter private Integer idRecurso;
    @Transient @Getter @Setter private Integer idObjecto;

    public Capacitacion() {
    }

    public Capacitacion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Parametros getEstado() {
        return estado;
    }

    public void setEstado(Parametros estado) {
        this.estado = estado;
    }

    public DetalleSolicitud getIdDetSol() {
        return idDetSol;
    }

    public void setIdDetSol(DetalleSolicitud idDetSol) {
        this.idDetSol = idDetSol;
    }

    public PlanCapacitacion getIdPlanCapacitacion() {
        return idPlanCapacitacion;
    }

    public void setIdPlanCapacitacion(PlanCapacitacion idPlanCapacitacion) {
        this.idPlanCapacitacion = idPlanCapacitacion;
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
        if (!(object instanceof Capacitacion)) {
            return false;
        }
        Capacitacion other = (Capacitacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upc.indra.be.Capacitacion[ id=" + id + " ]";
    }

}