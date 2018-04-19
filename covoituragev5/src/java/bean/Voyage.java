/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author IlyassElfouih
 */
@Entity
public class Voyage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Model model;
    private int nbrPlaceMax;
    private double prix;
    private double nbrPlaceOuccuper;
    @ManyToOne
    private Personne personne;
    @ManyToOne
    private Ville villeDepart;
    @ManyToOne
    private Ville villeArriver;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateVoyage;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateAnnonce;
    @OneToMany(mappedBy = "voyage")
    private List<CircuitVoyage> circuitVoyages;
    @OneToOne(mappedBy = "voyage")
    private BagageVoyage bagageVoyage;

    public Voyage() {
    }

    public Voyage(Long id) {
        this.id = id;
    }

    public BagageVoyage getBagageVoyage() {
        if (bagageVoyage == null) {
            bagageVoyage = new BagageVoyage();
        }
        return bagageVoyage;
    }

    public void setBagageVoyage(BagageVoyage bagageVoyage) {
        this.bagageVoyage = bagageVoyage;
    }

    public Date getDateAnnonce() {
        return dateAnnonce;
    }

    public void setDateAnnonce(Date dateAnnonce) {
        this.dateAnnonce = dateAnnonce;
    }

    public double getPrix() {
        return prix;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Ville getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(Ville villeDepart) {
        this.villeDepart = villeDepart;
    }

    public Ville getVilleArriver() {
        return villeArriver;
    }

    public void setVilleArriver(Ville villeArriver) {
        this.villeArriver = villeArriver;
    }

    public Date getDateVoyage() {
        return dateVoyage;
    }

    public void setDateVoyage(Date dateVoyage) {
        this.dateVoyage = dateVoyage;
    }

    public List<CircuitVoyage> getCircuitVoyages() {
        return circuitVoyages;
    }

    public void setCircuitVoyages(List<CircuitVoyage> circuitVoyages) {
        this.circuitVoyages = circuitVoyages;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getNbrPlaceOuccuper() {
        return nbrPlaceOuccuper;
    }

    public void setNbrPlaceOuccuper(double nbrPlaceOuccuper) {
        this.nbrPlaceOuccuper = nbrPlaceOuccuper;
    }

    public int getNbrPlaceMax() {
        return nbrPlaceMax;
    }

    public void setNbrPlaceMax(int nbrPlaceMax) {
        this.nbrPlaceMax = nbrPlaceMax;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
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
        if (!(object instanceof Voyage)) {
            return false;
        }
        Voyage other = (Voyage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Voyage{" + "id=" + id + ", model=" + model + ", nbrPlaceMax=" + nbrPlaceMax + ", prix=" + prix + ", nbrPlaceOuccuper=" + nbrPlaceOuccuper + ", personne=" + personne + ", villeDepart=" + villeDepart + ", villeArriver=" + villeArriver + ", dateVoyage=" + dateVoyage + ", dateAnnonce=" + dateAnnonce + ", circuitVoyages=" + circuitVoyages + '}';
    }

}
