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
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author RENSO
 * @date 06-nov-2018
 */
@Entity
@Table(name = "ca_indicador_area")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IndicadorArea.findAll", query = "SELECT i FROM IndicadorArea i"),
    @NamedQuery(name = "IndicadorArea.findById", query = "SELECT i FROM IndicadorArea i WHERE i.id = :id"),
    @NamedQuery(name = "IndicadorArea.findByPeriodo", query = "SELECT i FROM IndicadorArea i WHERE i.periodo = :periodo"),
    @NamedQuery(name = "IndicadorArea.findBySolicitudes", query = "SELECT i FROM IndicadorArea i WHERE i.solicitudes = :solicitudes"),
    @NamedQuery(name = "IndicadorArea.findByAtendidos", query = "SELECT i FROM IndicadorArea i WHERE i.atendidos = :atendidos"),
    @NamedQuery(name = "IndicadorArea.findByPrioridad", query = "SELECT i FROM IndicadorArea i WHERE i.prioridad = :prioridad")})
public class IndicadorArea implements Serializable {

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
    @Column(name = "SOLICITUDES")
    private int solicitudes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ATENDIDOS")
    private int atendidos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRIORIDAD")
    private Character prioridad;
    @JoinColumn(name = "ID_AREA", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Area idArea;

    public IndicadorArea() {
    }

    public IndicadorArea(Integer id) {
        this.id = id;
    }

    public IndicadorArea(Integer id, int periodo, int solicitudes, int atendidos, Character prioridad) {
        this.id = id;
        this.periodo = periodo;
        this.solicitudes = solicitudes;
        this.atendidos = atendidos;
        this.prioridad = prioridad;
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

    public int getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(int solicitudes) {
        this.solicitudes = solicitudes;
    }

    public int getAtendidos() {
        return atendidos;
    }

    public void setAtendidos(int atendidos) {
        this.atendidos = atendidos;
    }

    public Character getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Character prioridad) {
        this.prioridad = prioridad;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
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
        if (!(object instanceof IndicadorArea)) {
            return false;
        }
        IndicadorArea other = (IndicadorArea) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upc.indra.be.IndicadorArea[ id=" + id + " ]";
    }

}