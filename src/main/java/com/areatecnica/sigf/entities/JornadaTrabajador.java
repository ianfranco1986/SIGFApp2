/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "jornada_trabajador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JornadaTrabajador.findAll", query = "SELECT j FROM JornadaTrabajador j")
    , @NamedQuery(name = "JornadaTrabajador.findByJornadaTrabajadorId", query = "SELECT j FROM JornadaTrabajador j WHERE j.jornadaTrabajadorId = :jornadaTrabajadorId")})
public class JornadaTrabajador extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "jornada_trabajador_id", nullable = false)
    private Integer jornadaTrabajadorId;
    @JoinColumn(name = "jornada_trabajador_id_jornada_laboral", referencedColumnName = "jornada_laboral_id", nullable = false)
    @ManyToOne(optional = false)
    private JornadaLaboral jornadaTrabajadorIdJornadaLaboral;
    @JoinColumn(name = "jornada_trabajador_id_trabajador", referencedColumnName = "trabajador_id", nullable = false)
    @ManyToOne(optional = false)
    private Trabajador jornadaTrabajadorIdTrabajador;

    public JornadaTrabajador() {
    }

    public JornadaTrabajador(Integer jornadaTrabajadorId) {
        this.jornadaTrabajadorId = jornadaTrabajadorId;
    }

    public Integer getJornadaTrabajadorId() {
        return jornadaTrabajadorId;
    }

    public void setJornadaTrabajadorId(Integer jornadaTrabajadorId) {
        this.jornadaTrabajadorId = jornadaTrabajadorId;
    }

    public JornadaLaboral getJornadaTrabajadorIdJornadaLaboral() {
        return jornadaTrabajadorIdJornadaLaboral;
    }

    public void setJornadaTrabajadorIdJornadaLaboral(JornadaLaboral jornadaTrabajadorIdJornadaLaboral) {
        this.jornadaTrabajadorIdJornadaLaboral = jornadaTrabajadorIdJornadaLaboral;
    }

    public Trabajador getJornadaTrabajadorIdTrabajador() {
        return jornadaTrabajadorIdTrabajador;
    }

    public void setJornadaTrabajadorIdTrabajador(Trabajador jornadaTrabajadorIdTrabajador) {
        this.jornadaTrabajadorIdTrabajador = jornadaTrabajadorIdTrabajador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jornadaTrabajadorId != null ? jornadaTrabajadorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JornadaTrabajador)) {
            return false;
        }
        JornadaTrabajador other = (JornadaTrabajador) object;
        return (this.jornadaTrabajadorId != null || other.jornadaTrabajadorId == null) && (this.jornadaTrabajadorId == null || this.jornadaTrabajadorId.equals(other.jornadaTrabajadorId));
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.JornadaTrabajador[ jornadaTrabajadorId=" + jornadaTrabajadorId + " ]";
    }
    
}
