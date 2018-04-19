/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Mouhad ESSABBANE
 */
@Entity
public class BagageVoyage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Volume volume;
    @ManyToOne
    private Poids poids;
    @OneToOne
    private Voyage voyage;
    @OneToMany(mappedBy = "bagageVoyage")
    private List<BagagePersonne> bagagePersonnes;
    @OneToOne(mappedBy = "bagageVoyage")
    private BagagePricing bagagePricing;

    public BagageVoyage() {
    }

    public List<BagagePersonne> getBagagePersonnes() {
        return bagagePersonnes;
    }

    public void setBagagePersonnes(List<BagagePersonne> bagagePersonnes) {
        this.bagagePersonnes = bagagePersonnes;
    }

    public BagagePricing getBagagePricing() {
        if (bagagePricing == null) {
            bagagePricing = new BagagePricing();
        }
        return bagagePricing;
    }

    public void setBagagePricing(BagagePricing bagagePricing) {
        this.bagagePricing = bagagePricing;
    }

    public Volume getVolume() {
        if (volume == null) {
            volume = new Volume();
        }
        return volume;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    public Poids getPoids() {
        if (poids == null) {
            poids = new Poids();
        }
        return poids;
    }

    public void setPoids(Poids poids) {
        this.poids = poids;
    }

    public Voyage getVoyage() {
        return voyage;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
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
        if (!(object instanceof BagageVoyage)) {
            return false;
        }
        BagageVoyage other = (BagageVoyage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.bagageVoyage[ id=" + id + " ]";
    }

}
