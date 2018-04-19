/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Mouhad ESSABBANE
 */
@Entity
public class BagagePersonne implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Volume volume;
    @ManyToOne
    private Poids poids;
    @OneToOne
    private Personne personne;
    @ManyToOne
    private BagageVoyage bagageVoyage;

    public BagagePersonne() {
    }

    public Volume getVolume() {
        return volume;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    public Poids getPoids() {
        return poids;
    }

    public void setPoids(Poids poids) {
        this.poids = poids;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public BagageVoyage getBagageVoyage() {
        return bagageVoyage;
    }

    public void setBagageVoyage(BagageVoyage bagageVoyage) {
        this.bagageVoyage = bagageVoyage;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof BagagePersonne)) {
            return false;
        }
        BagagePersonne other = (BagagePersonne) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.BagagePersonne[ id=" + id + " ]";
    }
    
}
