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

/**
 *
 * @author Mouhad ESSABBANE
 */
@Entity
public class Pleinte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String pleinte;
    private String objet;
    @ManyToOne
    private Personne personne;
    @OneToMany(mappedBy = "pleinte")
    private List<PleinteDetail> pleinteDetails;

    public Pleinte() {
    }

    public List<PleinteDetail> getPleinteDetails() {
        return pleinteDetails;
    }

    public void setPleinteDetails(List<PleinteDetail> pleinteDetails) {
        this.pleinteDetails = pleinteDetails;
    }

    
    public String getPleinte() {
        return pleinte;
    }

    public void setPleinte(String pleinte) {
        this.pleinte = pleinte;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
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
        if (!(object instanceof Pleinte)) {
            return false;
        }
        Pleinte other = (Pleinte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Pleinte[ id=" + id + " ]";
    }
    
}
