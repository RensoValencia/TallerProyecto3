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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

/**
 * @author RENSO
 * @date 06-nov-2018
 */
@Entity
@Table(name = "ca_recurso_capacitacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RecursoCapacitacion.findAll", query = "SELECT r FROM RecursoCapacitacion r"),
    @NamedQuery(name = "RecursoCapacitacion.findById", query = "SELECT r FROM RecursoCapacitacion r WHERE r.id = :id"),
    @NamedQuery(name = "RecursoCapacitacion.findIdPlanCapa", query = "SELECT r FROM RecursoCapacitacion r WHERE r.idPlanificacion = :idPlani"),
    @NamedQuery(name = "RecursoCapacitacion.findByCantidad", query = "SELECT r FROM RecursoCapacitacion r WHERE r.cantidad = :cantidad"),
    @NamedQuery(name = "RecursoCapacitacion.findByValor", query = "SELECT r FROM RecursoCapacitacion r WHERE r.valor = :valor"),
    @NamedQuery(name = "RecursoCapacitacion.findByIdPlanAndIdTipoRecurso", query = "SELECT r FROM RecursoCapacitacion r WHERE r.idPlanificacion = :idPlan AND r.idTipoRecurso = :idTipoRecurso"),
    @NamedQuery(name = "RecursoCapacitacion.findByIdObjeto", query = "SELECT r FROM RecursoCapacitacion r WHERE r.idObjeto = :idObjeto")})
public class RecursoCapacitacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "valor")
    
    private double valor;
    @Column(name = "ID_OBJETO")
    private Integer idObjeto;
    
    @JoinColumn(name = "ID_PLANIFICACION", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PlanCapacitacion idPlanificacion;
    @JoinColumn(name = "ID_TIPO_RECURSO", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Parametros idTipoRecurso;
    
    @Transient @Getter @Setter private String marca;
    @Transient @Getter @Setter private String tipo;
    @Transient @Getter @Setter private String nombre;
    @Transient @Getter @Setter private String descripcion;
    @Transient @Getter @Setter private PerfilCapacitador perfilCapacitador;
    @Transient @Getter @Setter private Object tipoMaterial;

    public RecursoCapacitacion() {
    }

    public RecursoCapacitacion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Integer getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(Integer idObjeto) {
        this.idObjeto = idObjeto;
    }

    public PlanCapacitacion getIdPlanificacion() {
        return idPlanificacion;
    }

    public void setIdPlanificacion(PlanCapacitacion idPlanificacion) {
        this.idPlanificacion = idPlanificacion;
    }

    public Parametros getIdTipoRecurso() {
        return idTipoRecurso;
    }

    public void setIdTipoRecurso(Parametros idTipoRecurso) {
        this.idTipoRecurso = idTipoRecurso;
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
        if (!(object instanceof RecursoCapacitacion)) {
            return false;
        }
        RecursoCapacitacion other = (RecursoCapacitacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upc.indra.be.RecursoCapacitacion[ id=" + id + " ]";
    }

}