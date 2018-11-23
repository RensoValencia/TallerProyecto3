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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Getter;
import lombok.Setter;
/**
 * @author RENSO
 * @date 06-nov-2018
 */
@Entity
@Table(name = "ca_detalle_solicitud")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleSolicitud.findAll", query = "SELECT d FROM DetalleSolicitud d"),
    @NamedQuery(name = "DetalleSolicitud.findBySolCap", query = "SELECT d FROM DetalleSolicitud d WHERE d.idSolCap = :solCap"),
    @NamedQuery(name = "DetalleSolicitud.findByEstadoTipoCapaAnioYCurso", query = "SELECT DISTINCT new com.upc.indra.be.DetalleSolicitud(d.idSolCap.id, d.idSolCap.fechaDocumento, d.idSolCap.idEstado, d.idSolCap.fechaAtencion, d.idSolCap.observacion, d.idSolCap.idArea, d.idSolCap.periodo, d.idSolCap.idTipoModalidad) FROM DetalleSolicitud d WHERE d.idSolCap.idEstado = :estado AND d.idFormacion.idTipoModalidad = :idTipCapa AND d.idSolCap.periodo = :periodo AND d.idFormacion.idTipoFormacion = :idTipForm AND d.idSolCap.idArea = :idArea"),
    @NamedQuery(name = "DetalleSolicitud.findById", query = "SELECT d FROM DetalleSolicitud d WHERE d.id = :id"),
    @NamedQuery(name = "DetalleSolicitud.findByNumeroParticipantes", query = "SELECT d FROM DetalleSolicitud d WHERE d.numeroParticipantes = :numeroParticipantes")})
public class DetalleSolicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NUMERO_PARTICIPANTES")
    private int numeroParticipantes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDetSol", fetch = FetchType.LAZY)
    private Set<Capacitacion> capacitacionSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDetSolicitud", fetch = FetchType.LAZY)
    private Set<DetSolParticipante> detSolParticipanteSet;
    @JoinColumn(name = "ID_FORMACION", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Formacion idFormacion;
    @JoinColumn(name = "ID_SOL_CAP", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SolicitudCapacitacion idSolCap;
    
    @Transient @Getter @Setter private Date fechaInicio;
    @Transient @Getter @Setter private Date fechaFin;
    
    @Transient @Getter @Setter private PerfilCapacitador perfilCapacitador;
    @Transient @Getter @Setter private double honorarios;
    
    @Transient @Getter @Setter private int numeroParticipantesInput;

    public DetalleSolicitud() {
    }

    public DetalleSolicitud(Integer id) {
        this.id = id;
    }
    
    public DetalleSolicitud(Integer id, Date fechaDocumento, Parametros idEstado, Date fechaAtencion, 
            String observacion, Area idArea, Parametros idTipoCap, Integer periodo, Parametros idTipoMod) {
        this.idSolCap = new SolicitudCapacitacion(id);
        this.idSolCap.setFechaDocumento(fechaDocumento);
        this.idSolCap.setIdEstado(idEstado);
        this.idSolCap.setFechaAtencion(fechaAtencion);
        this.idSolCap.setObservacion(observacion);
        this.idSolCap.setIdArea(idArea);
        this.idSolCap.setPeriodo(periodo);
        this.idSolCap.setIdTipoModalidad(idTipoMod);
    }
    
    public DetalleSolicitud(Integer id, String idFormato, Date fechaDocumento, Integer idEstado, String idEstadoDescripcion, Date fechaAtencion, 
            String observacion, Integer idArea, Integer periodo, Integer idTipoMod, String idTipModDescripcion) {
        this.idSolCap = new SolicitudCapacitacion(id);
        this.idSolCap.setIdFormato(idFormato);
        this.idSolCap.setFechaDocumento(fechaDocumento);
        this.idSolCap.setIdEstado(new Parametros(idEstado));
        this.idSolCap.getIdEstado().setDescripcion(idEstadoDescripcion);
        this.idSolCap.setFechaAtencion(fechaAtencion);
        this.idSolCap.setObservacion(observacion);
        this.idSolCap.setIdArea(new Area(idArea));
        this.idSolCap.setPeriodo(periodo);
        this.idSolCap.setIdTipoModalidad(new Parametros(idTipoMod));
        this.idSolCap.getIdTipoModalidad().setDescripcion(idTipModDescripcion);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumeroParticipantes() {
        return numeroParticipantes;
    }

    public void setNumeroParticipantes(int numeroParticipantes) {
        this.numeroParticipantes = numeroParticipantes;
    }

    @XmlTransient
    public Set<Capacitacion> getCapacitacionSet() {
        return capacitacionSet;
    }

    public void setCapacitacionSet(Set<Capacitacion> capacitacionSet) {
        this.capacitacionSet = capacitacionSet;
    }

    @XmlTransient
    public Set<DetSolParticipante> getDetSolParticipanteSet() {
        return detSolParticipanteSet;
    }

    public void setDetSolParticipanteSet(Set<DetSolParticipante> detSolParticipanteSet) {
        this.detSolParticipanteSet = detSolParticipanteSet;
    }

    public Formacion getIdFormacion() {
        return idFormacion;
    }

    public void setIdFormacion(Formacion idFormacion) {
        this.idFormacion = idFormacion;
    }

    public SolicitudCapacitacion getIdSolCap() {
        return idSolCap;
    }

    public void setIdSolCap(SolicitudCapacitacion idSolCap) {
        this.idSolCap = idSolCap;
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
        if (!(object instanceof DetalleSolicitud)) {
            return false;
        }
        DetalleSolicitud other = (DetalleSolicitud) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upc.indra.be.DetalleSolicitud[ id=" + id + " ]";
    }

}