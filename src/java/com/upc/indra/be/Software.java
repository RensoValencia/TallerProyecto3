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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author RENSO
 * @date 06-nov-2018
 */
@Entity
@Table(name = "software")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Software.findAll", query = "SELECT s FROM Software s"),
    @NamedQuery(name = "Software.findById", query = "SELECT s FROM Software s WHERE s.id = :id"),
    @NamedQuery(name = "Software.findByDescripcion", query = "SELECT s FROM Software s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "Software.findByCodLicencia", query = "SELECT s FROM Software s WHERE s.codLicencia = :codLicencia"),
    @NamedQuery(name = "Software.findByFechaExpiracion", query = "SELECT s FROM Software s WHERE s.fechaExpiracion = :fechaExpiracion"),
    @NamedQuery(name = "Software.findByTipoExpiracion", query = "SELECT s FROM Software s WHERE s.tipoExpiracion = :tipoExpiracion"),
    @NamedQuery(name = "Software.findByCantidad", query = "SELECT s FROM Software s WHERE s.cantidad = :cantidad"),
    @NamedQuery(name = "Software.findByEstado", query = "SELECT s FROM Software s WHERE s.estado = :estado"),
    @NamedQuery(name = "Software.findByStock", query = "SELECT s FROM Software s WHERE s.stock = :stock")})
public class Software implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "COD_LICENCIA")
    private String codLicencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_EXPIRACION")
    @Temporal(TemporalType.DATE)
    private Date fechaExpiracion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TIPO_EXPIRACION")
    private String tipoExpiracion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private double cantidad;
    @Column(name = "ESTADO")
    private Character estado;
    @Column(name = "STOCK")
    private Integer stock;
    @JoinColumn(name = "ID_MARCA_SW", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MarcaSoftware idMarcaSw;
    @JoinColumn(name = "ID_TIPO_SW", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoSoftware idTipoSw;

    public Software() {
    }

    public Software(Integer id) {
        this.id = id;
    }

    public Software(Integer id, String descripcion, String codLicencia, Date fechaExpiracion, String tipoExpiracion, double cantidad) {
        this.id = id;
        this.descripcion = descripcion;
        this.codLicencia = codLicencia;
        this.fechaExpiracion = fechaExpiracion;
        this.tipoExpiracion = tipoExpiracion;
        this.cantidad = cantidad;
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

    public String getCodLicencia() {
        return codLicencia;
    }

    public void setCodLicencia(String codLicencia) {
        this.codLicencia = codLicencia;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getTipoExpiracion() {
        return tipoExpiracion;
    }

    public void setTipoExpiracion(String tipoExpiracion) {
        this.tipoExpiracion = tipoExpiracion;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public MarcaSoftware getIdMarcaSw() {
        return idMarcaSw;
    }

    public void setIdMarcaSw(MarcaSoftware idMarcaSw) {
        this.idMarcaSw = idMarcaSw;
    }

    public TipoSoftware getIdTipoSw() {
        return idTipoSw;
    }

    public void setIdTipoSw(TipoSoftware idTipoSw) {
        this.idTipoSw = idTipoSw;
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
        if (!(object instanceof Software)) {
            return false;
        }
        Software other = (Software) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upc.indra.be.Software[ id=" + id + " ]";
    }

}