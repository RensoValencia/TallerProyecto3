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
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Getter;
import lombok.Setter;

/**
 * @author RENSO
 * @date 06-nov-2018
 */
@Entity
@Table(name = "ca_solicitud_capacitacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudCapacitacion.findAll", query = "SELECT s FROM SolicitudCapacitacion s"),
    @NamedQuery(name = "SolicitudCapacitacion.findListSolCapByEstado", query = "SELECT s FROM SolicitudCapacitacion s WHERE s.idEstado = :estado ORDER BY s.id DESC"),
    @NamedQuery(name = "SolicitudCapacitacion.findListByArea", query = "SELECT s FROM SolicitudCapacitacion s WHERE s.idArea = :area ORDER BY s.fechaDocumento DESC"),
    @NamedQuery(name = "SolicitudCapacitacion.findById", query = "SELECT s FROM SolicitudCapacitacion s WHERE s.id = :id"),
    @NamedQuery(name = "SolicitudCapacitacion.findByIdTipoPlanCapacitacionAndEstado", query = "SELECT s FROM SolicitudCapacitacion s WHERE s.idEstado = :estado AND s.periodo = :periodo"),
    @NamedQuery(name = "SolicitudCapacitacion.findByAreaIdTipModAndPeriodo", query = "SELECT s FROM SolicitudCapacitacion s WHERE s.idArea = :idArea AND s.idTipoModalidad = :idTipMod AND s.periodo = :periodo"),
    @NamedQuery(name = "SolicitudCapacitacion.findByFechaSolicitud", query = "SELECT s FROM SolicitudCapacitacion s WHERE s.fechaDocumento = :fechaDocumento"),})
public class SolicitudCapacitacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "FECHA_DOCUMENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaDocumento;
    @Column(name = "FECHA_ATENCION")
    @Temporal(TemporalType.DATE)
    private Date fechaAtencion;
    @Size(max = 100)
    @Column(name = "OBSERVACION")
    private String observacion;
    @Column(name = "PERIODO")
    private Integer periodo;
    @Column(name = "CANT_DET_SOL")
    private Integer cantDetSol;
    @JoinColumn(name = "ID_TIPO_MODALIDAD", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Parametros idTipoModalidad;
    @JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Parametros idEstado;
    
    @Transient @Getter @Setter private String idFormato;
    
    @JoinColumn(name = "ID_AREA", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Area idArea;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSolCap", fetch = FetchType.LAZY)
    private Set<DetalleSolicitud> detalleSolicitudSet;

    public SolicitudCapacitacion() {
    }

    public SolicitudCapacitacion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(Date fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public Date getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(Date fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    public Integer getCantDetSol() {
        return cantDetSol;
    }

    public void setCantDetSol(Integer cantDetSol) {
        this.cantDetSol = cantDetSol;
    }

    public Parametros getIdTipoModalidad() {
        return idTipoModalidad;
    }

    public void setIdTipoModalidad(Parametros idTipoModalidad) {
        this.idTipoModalidad = idTipoModalidad;
    }

    public Parametros getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Parametros idEstado) {
        this.idEstado = idEstado;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    @XmlTransient
    public Set<DetalleSolicitud> getDetalleSolicitudSet() {
        return detalleSolicitudSet;
    }

    public void setDetalleSolicitudSet(Set<DetalleSolicitud> detalleSolicitudSet) {
        this.detalleSolicitudSet = detalleSolicitudSet;
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
        if (!(object instanceof SolicitudCapacitacion)) {
            return false;
        }
        SolicitudCapacitacion other = (SolicitudCapacitacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upc.indra.be.SolicitudCapacitacion[ id=" + id + " ]";
    }

}