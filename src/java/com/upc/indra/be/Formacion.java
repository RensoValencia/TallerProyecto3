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
@Table(name = "ca_formacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formacion.findAll", query = "SELECT f FROM Formacion f ORDER BY f.fechaCreacion DESC"),
    @NamedQuery(name = "Formacion.findById", query = "SELECT f FROM Formacion f WHERE f.id = :id"),
    @NamedQuery(name = "Formacion.findByIdArea", query = "SELECT f FROM Formacion f WHERE f.idArea.id = :idArea"),
    @NamedQuery(name = "Formacion.findByIdAreaAndIdTipoFormacion", query = "SELECT f FROM Formacion f WHERE f.idArea.id = :idArea AND f.idTipoFormacion.id = :idTipoFormacion"),
    @NamedQuery(name = "Formacion.findByIdAreaAndIdTipoModalidad", query = "SELECT f FROM Formacion f WHERE f.idArea.id = :idArea AND f.idTipoModalidad.id = :idTipoModalidad"),
    @NamedQuery(name = "Formacion.findByIdAreaIdTipoFormacionAndIdTipoModalidad", query = "SELECT f FROM Formacion f WHERE f.idArea.id = :idArea AND f.idTipoFormacion.id = :idTipoFormacion AND f.idTipoModalidad.id = :idTipoModalidad"),
    @NamedQuery(name = "Formacion.findByNombre", query = "SELECT f FROM Formacion f WHERE f.nombre LIKE :nombre"),
    @NamedQuery(name = "Formacion.findByMaximoParticipantes", query = "SELECT f FROM Formacion f WHERE f.maximoParticipantes = :maximoParticipantes"),
    @NamedQuery(name = "Formacion.findByNumeroHoras", query = "SELECT f FROM Formacion f WHERE f.numeroHoras = :numeroHoras"),
    @NamedQuery(name = "Formacion.findByProposito", query = "SELECT f FROM Formacion f WHERE f.proposito = :proposito"),
    @NamedQuery(name = "Formacion.findByIdTipoFormacion", query = "SELECT f FROM Formacion f WHERE f.idTipoFormacion = :idTipoFormacion"),
    @NamedQuery(name = "Formacion.findByNombreAndIdTipoFormacion", query = "SELECT f FROM Formacion f WHERE f.nombre LIKE :nombre AND f.idTipoFormacion = :idTipoFormacion"),
    @NamedQuery(name = "Formacion.findByTemario", query = "SELECT f FROM Formacion f WHERE f.temario = :temario")})
public class Formacion implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Column(name = "MAXIMO_PARTICIPANTES")
    private int maximoParticipantes;
    @Column(name = "NUMERO_HORAS")
    private Integer numeroHoras;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PROPOSITO")
    private String proposito;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TEMARIO")
    private String temario;
    @JoinColumn(name = "ID_TIPO_FORMACION", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Parametros idTipoFormacion;
    @JoinColumn(name = "TIPO_SALA", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Parametros tipoSala;
    @JoinColumn(name = "ID_AREA", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Area idArea;
    @JoinColumn(name = "ID_TIPO_MODALIDAD", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Parametros idTipoModalidad;
    
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.DATE)
    @Getter @Setter private Date fechaCreacion;
    
    @JoinColumn(name = "ID_PERFIL_CAPACITADOR", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @Getter @Setter private PerfilCapacitador idPerfilCapacitador;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormacion", fetch = FetchType.LAZY)
    private Set<DetalleSolicitud> detalleSolicitudSet;

    public Formacion() {
    }

    public Formacion(Integer id) {
        this.id = id;
    }

    public Formacion(Integer id, String nombre, int maximoParticipantes, String proposito, String temario) {
        this.id = id;
        this.nombre = nombre;
        this.maximoParticipantes = maximoParticipantes;
        this.proposito = proposito;
        this.temario = temario;
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

    public int getMaximoParticipantes() {
        return maximoParticipantes;
    }

    public void setMaximoParticipantes(int maximoParticipantes) {
        this.maximoParticipantes = maximoParticipantes;
    }

    public Integer getNumeroHoras() {
        return numeroHoras;
    }

    public void setNumeroHoras(Integer numeroHoras) {
        this.numeroHoras = numeroHoras;
    }

    public String getProposito() {
        return proposito;
    }

    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    public String getTemario() {
        return temario;
    }

    public void setTemario(String temario) {
        this.temario = temario;
    }

    public Parametros getIdTipoFormacion() {
        return idTipoFormacion;
    }

    public void setIdTipoFormacion(Parametros idTipoFormacion) {
        this.idTipoFormacion = idTipoFormacion;
    }

    public Parametros getTipoSala() {
        return tipoSala;
    }

    public void setTipoSala(Parametros tipoSala) {
        this.tipoSala = tipoSala;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    public Parametros getIdTipoModalidad() {
        return idTipoModalidad;
    }

    public void setIdTipoModalidad(Parametros idTipoModalidad) {
        this.idTipoModalidad = idTipoModalidad;
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
        if (!(object instanceof Formacion)) {
            return false;
        }
        Formacion other = (Formacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upc.indra.be.Formacion[ id=" + id + " ]";
    }

}