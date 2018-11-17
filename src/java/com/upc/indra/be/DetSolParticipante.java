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
import javax.xml.bind.annotation.XmlRootElement;
/**
 * @author RENSO
 * @date 06-nov-2018
 */
@Entity
@Table(name = "ca_det_sol_participante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetSolParticipante.findAll", query = "SELECT d FROM DetSolParticipante d"),
    @NamedQuery(name = "DetSolParticipante.findByDetSol", query = "SELECT d FROM DetSolParticipante d WHERE d.idDetSolicitud = :detSolicitud"),
    @NamedQuery(name = "DetSolParticipante.findById", query = "SELECT d FROM DetSolParticipante d WHERE d.id = :id")})
public class DetSolParticipante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "ID_DET_SOLICITUD", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DetalleSolicitud idDetSolicitud;
    @JoinColumn(name = "ID_EMPLEADO", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Empleado idEmpleado;

    public DetSolParticipante() {
    }

    public DetSolParticipante(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DetalleSolicitud getIdDetSolicitud() {
        return idDetSolicitud;
    }

    public void setIdDetSolicitud(DetalleSolicitud idDetSolicitud) {
        this.idDetSolicitud = idDetSolicitud;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
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
        if (!(object instanceof DetSolParticipante)) {
            return false;
        }
        DetSolParticipante other = (DetSolParticipante) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upc.indra.be.DetSolParticipante[ id=" + id + " ]";
    }

}