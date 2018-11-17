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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author RENSO
 * @date 06-nov-2018
 */
@Entity
@Table(name = "hardware")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hardware.findAll", query = "SELECT h FROM Hardware h"),
    @NamedQuery(name = "Hardware.findById", query = "SELECT h FROM Hardware h WHERE h.id = :id"),
    @NamedQuery(name = "Hardware.findByModelo", query = "SELECT h FROM Hardware h WHERE h.modelo = :modelo"),
    @NamedQuery(name = "Hardware.findByTamanio", query = "SELECT h FROM Hardware h WHERE h.tamanio = :tamanio"),
    @NamedQuery(name = "Hardware.findByColor", query = "SELECT h FROM Hardware h WHERE h.color = :color"),
    @NamedQuery(name = "Hardware.findByMaterial", query = "SELECT h FROM Hardware h WHERE h.material = :material"),
    @NamedQuery(name = "Hardware.findByPeso", query = "SELECT h FROM Hardware h WHERE h.peso = :peso"),
    @NamedQuery(name = "Hardware.findByCantidad", query = "SELECT h FROM Hardware h WHERE h.cantidad = :cantidad"),
    @NamedQuery(name = "Hardware.findByEstado", query = "SELECT h FROM Hardware h WHERE h.estado = :estado"),
    @NamedQuery(name = "Hardware.findByStock", query = "SELECT h FROM Hardware h WHERE h.stock = :stock")})
public class Hardware implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "MODELO")
    private String modelo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TAMANIO")
    private double tamanio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "COLOR")
    private String color;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "MATERIAL")
    private String material;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PESO")
    private double peso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private int cantidad;
    @Column(name = "ESTADO")
    private Character estado;
    @Column(name = "STOCK")
    private Integer stock;
    @JoinColumn(name = "ID_MARCA_HW", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MarcaHardware idMarcaHw;
    @JoinColumn(name = "ID_TIPO_HW", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoHardware idTipoHw;

    public Hardware() {
    }

    public Hardware(Integer id) {
        this.id = id;
    }

    public Hardware(Integer id, String modelo, double tamanio, String color, String material, double peso, int cantidad) {
        this.id = id;
        this.modelo = modelo;
        this.tamanio = tamanio;
        this.color = color;
        this.material = material;
        this.peso = peso;
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getTamanio() {
        return tamanio;
    }

    public void setTamanio(double tamanio) {
        this.tamanio = tamanio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
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

    public MarcaHardware getIdMarcaHw() {
        return idMarcaHw;
    }

    public void setIdMarcaHw(MarcaHardware idMarcaHw) {
        this.idMarcaHw = idMarcaHw;
    }

    public TipoHardware getIdTipoHw() {
        return idTipoHw;
    }

    public void setIdTipoHw(TipoHardware idTipoHw) {
        this.idTipoHw = idTipoHw;
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
        if (!(object instanceof Hardware)) {
            return false;
        }
        Hardware other = (Hardware) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upc.indra.be.Hardware[ id=" + id + " ]";
    }

}