package com.upc.indra.be;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author RENSO
 * @date 06-nov-2018
 */
@Entity
@Table(name = "ss_rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r"),
    @NamedQuery(name = "Rol.findById", query = "SELECT r FROM Rol r WHERE r.id = :id"),
    @NamedQuery(name = "Rol.findByNombre", query = "SELECT r FROM Rol r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Rol.findByNivel", query = "SELECT r FROM Rol r WHERE r.nivel = :nivel"),
    @NamedQuery(name = "Rol.findByIdUsuarioCreacion", query = "SELECT r FROM Rol r WHERE r.idUsuarioCreacion = :idUsuarioCreacion"),
    @NamedQuery(name = "Rol.findByIdUsuarioModificacion", query = "SELECT r FROM Rol r WHERE r.idUsuarioModificacion = :idUsuarioModificacion"),
    @NamedQuery(name = "Rol.findByFechaCreacion", query = "SELECT r FROM Rol r WHERE r.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Rol.findByFechaCreacionHora", query = "SELECT r FROM Rol r WHERE r.fechaCreacionHora = :fechaCreacionHora"),
    @NamedQuery(name = "Rol.findByFechaModificacion", query = "SELECT r FROM Rol r WHERE r.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "Rol.findByFechaModificacionHora", query = "SELECT r FROM Rol r WHERE r.fechaModificacionHora = :fechaModificacionHora"),
    @NamedQuery(name = "Rol.findByPcCreacion", query = "SELECT r FROM Rol r WHERE r.pcCreacion = :pcCreacion"),
    @NamedQuery(name = "Rol.findByPcModificacion", query = "SELECT r FROM Rol r WHERE r.pcModificacion = :pcModificacion"),
    @NamedQuery(name = "Rol.findByIdSede", query = "SELECT r FROM Rol r WHERE r.idSede = :idSede"),
    @NamedQuery(name = "Rol.findByEstado", query = "SELECT r FROM Rol r WHERE r.estado = :estado")})
public class Rol implements Serializable {

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
    @Column(name = "NIVEL")
    private Integer nivel;
    @Column(name = "ID_USUARIO_CREACION")
    private Integer idUsuarioCreacion;
    @Column(name = "ID_USUARIO_MODIFICACION")
    private Integer idUsuarioModificacion;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Column(name = "FECHA_CREACION_HORA")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacionHora;
    @Column(name = "FECHA_MODIFICACION")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    @Column(name = "FECHA_MODIFICACION_HORA")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacionHora;
    @Size(max = 100)
    @Column(name = "PC_CREACION")
    private String pcCreacion;
    @Size(max = 100)
    @Column(name = "PC_MODIFICACION")
    private String pcModificacion;
    @Column(name = "ID_SEDE")
    private Integer idSede;
    @Column(name = "ESTADO")
    private Character estado;
    @JoinColumn(name = "ID_AREA", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Area idArea;
    @OneToMany(mappedBy = "idRol", fetch = FetchType.LAZY)
    private Set<Empleado> empleadoSet;

    public Rol() {
    }

    public Rol(Integer id) {
        this.id = id;
    }

    public Rol(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getIdUsuarioCreacion() {
        return idUsuarioCreacion;
    }

    public void setIdUsuarioCreacion(Integer idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public Integer getIdUsuarioModificacion() {
        return idUsuarioModificacion;
    }

    public void setIdUsuarioModificacion(Integer idUsuarioModificacion) {
        this.idUsuarioModificacion = idUsuarioModificacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaCreacionHora() {
        return fechaCreacionHora;
    }

    public void setFechaCreacionHora(Date fechaCreacionHora) {
        this.fechaCreacionHora = fechaCreacionHora;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Date getFechaModificacionHora() {
        return fechaModificacionHora;
    }

    public void setFechaModificacionHora(Date fechaModificacionHora) {
        this.fechaModificacionHora = fechaModificacionHora;
    }

    public String getPcCreacion() {
        return pcCreacion;
    }

    public void setPcCreacion(String pcCreacion) {
        this.pcCreacion = pcCreacion;
    }

    public String getPcModificacion() {
        return pcModificacion;
    }

    public void setPcModificacion(String pcModificacion) {
        this.pcModificacion = pcModificacion;
    }

    public Integer getIdSede() {
        return idSede;
    }

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    @XmlTransient
    public Set<Empleado> getEmpleadoSet() {
        return empleadoSet;
    }

    public void setEmpleadoSet(Set<Empleado> empleadoSet) {
        this.empleadoSet = empleadoSet;
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
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upc.indra.be.Rol[ id=" + id + " ]";
    }

}