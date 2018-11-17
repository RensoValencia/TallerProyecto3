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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

/**
 * @author RENSO
 * @date 06-nov-2018
 */
@Entity
@Table(name = "gri_recurso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GriRecurso.findAll", query = "SELECT g FROM GriRecurso g"),
    @NamedQuery(name = "GriRecurso.findByTipoRecursoMarcaAndNombre", query = "SELECT g FROM GriRecurso g WHERE g.idGriTipoRecurso.id = :id1 AND g.idGriMarca.id = :id2 AND g.descripcion LIKE :id3"),
    @NamedQuery(name = "GriRecurso.findById", query = "SELECT g FROM GriRecurso g WHERE g.id = :id"),
    @NamedQuery(name = "GriRecurso.findByDescripcion", query = "SELECT g FROM GriRecurso g WHERE g.descripcion = :descripcion"),
    @NamedQuery(name = "GriRecurso.findByCantidad", query = "SELECT g FROM GriRecurso g WHERE g.cantidad = :cantidad"),
    @NamedQuery(name = "GriRecurso.findBySo", query = "SELECT g FROM GriRecurso g WHERE g.so = :so"),
    @NamedQuery(name = "GriRecurso.findByProcesador", query = "SELECT g FROM GriRecurso g WHERE g.procesador = :procesador"),
    @NamedQuery(name = "GriRecurso.findByMemoriaRam", query = "SELECT g FROM GriRecurso g WHERE g.memoriaRam = :memoriaRam"),
    @NamedQuery(name = "GriRecurso.findByDiscoDuro", query = "SELECT g FROM GriRecurso g WHERE g.discoDuro = :discoDuro"),
    @NamedQuery(name = "GriRecurso.findByPulgadas", query = "SELECT g FROM GriRecurso g WHERE g.pulgadas = :pulgadas"),
    @NamedQuery(name = "GriRecurso.findByCapacidad", query = "SELECT g FROM GriRecurso g WHERE g.capacidad = :capacidad"),
    @NamedQuery(name = "GriRecurso.findBySerie", query = "SELECT g FROM GriRecurso g WHERE g.serie = :serie"),
    @NamedQuery(name = "GriRecurso.findByTipoExpiracion", query = "SELECT g FROM GriRecurso g WHERE g.tipoExpiracion = :tipoExpiracion"),
    @NamedQuery(name = "GriRecurso.findByFechaExpiracion", query = "SELECT g FROM GriRecurso g WHERE g.fechaExpiracion = :fechaExpiracion")})
public class GriRecurso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "CANTIDAD")
    private Integer cantidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "SO")
    private String so;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PROCESADOR")
    private String procesador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "MEMORIA_RAM")
    private String memoriaRam;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DISCO_DURO")
    private String discoDuro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PULGADAS")
    private String pulgadas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CAPACIDAD")
    private String capacidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "SERIE")
    private String serie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TIPO_EXPIRACION")
    private String tipoExpiracion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_EXPIRACION")
    @Temporal(TemporalType.DATE)
    private Date fechaExpiracion;
    @JoinColumn(name = "ID_GRI_MARCA", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GriMarca idGriMarca;
    @JoinColumn(name = "ID_GRI_TIPO_RECURSO", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GriTipoRecurso idGriTipoRecurso;
    
    @Transient @Getter @Setter private int cantidadTempl;
    @Transient @Getter @Setter private double valor;

    public GriRecurso() {
    }

    public GriRecurso(Integer id) {
        this.id = id;
    }

    public GriRecurso(Integer id, String descripcion, String so, String procesador, String memoriaRam, String discoDuro, String pulgadas, String capacidad, String serie, String tipoExpiracion, Date fechaExpiracion) {
        this.id = id;
        this.descripcion = descripcion;
        this.so = so;
        this.procesador = procesador;
        this.memoriaRam = memoriaRam;
        this.discoDuro = discoDuro;
        this.pulgadas = pulgadas;
        this.capacidad = capacidad;
        this.serie = serie;
        this.tipoExpiracion = tipoExpiracion;
        this.fechaExpiracion = fechaExpiracion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getSo() {
        return so;
    }

    public void setSo(String so) {
        this.so = so;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public String getMemoriaRam() {
        return memoriaRam;
    }

    public void setMemoriaRam(String memoriaRam) {
        this.memoriaRam = memoriaRam;
    }

    public String getDiscoDuro() {
        return discoDuro;
    }

    public void setDiscoDuro(String discoDuro) {
        this.discoDuro = discoDuro;
    }

    public String getPulgadas() {
        return pulgadas;
    }

    public void setPulgadas(String pulgadas) {
        this.pulgadas = pulgadas;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getTipoExpiracion() {
        return tipoExpiracion;
    }

    public void setTipoExpiracion(String tipoExpiracion) {
        this.tipoExpiracion = tipoExpiracion;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public GriMarca getIdGriMarca() {
        return idGriMarca;
    }

    public void setIdGriMarca(GriMarca idGriMarca) {
        this.idGriMarca = idGriMarca;
    }

    public GriTipoRecurso getIdGriTipoRecurso() {
        return idGriTipoRecurso;
    }

    public void setIdGriTipoRecurso(GriTipoRecurso idGriTipoRecurso) {
        this.idGriTipoRecurso = idGriTipoRecurso;
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
        if (!(object instanceof GriRecurso)) {
            return false;
        }
        GriRecurso other = (GriRecurso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upc.indra.be.GriRecurso[ id=" + id + " ]";
    }

}