package com.stream.functional.demo.domain;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author john - 3G software co.ltd
 */
@Entity
@Table(name = "tracks")
@Data
public class Tracks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "track_id")
    private Integer trackId;
    @Column(name = "name")
    private String name;
    @Column(name = "composer")
    private String composer;
    @Column(name = "milliseconds")
    private Integer milliseconds;
    @Column(name = "bytes")
    private Integer bytes;
    @Column(name = "unit_price")
    private String unitPrice;

    public Tracks() {
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trackId != null ? trackId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tracks)) {
            return false;
        }
        Tracks other = (Tracks) object;
        if ((this.trackId == null && other.trackId != null) || (this.trackId != null && !this.trackId.equals(other.trackId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.roockie.domain.Tracks[ trackId=" + trackId + " ]";
    }

}
