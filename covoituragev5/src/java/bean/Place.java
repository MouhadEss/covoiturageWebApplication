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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Mouhad ESSABBANE
 */
@Entity
public class Place implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean av;
    private boolean arrGauche;
    private boolean arrMilieu;
    private boolean arrDroite;
    private boolean arrArrDroite;
    private boolean arrArrGauche;
    @OneToMany(mappedBy = "place")
    private List<CircuitVoyage> circuitVoyages;
    

   
    public Place() {
    }

    public boolean isAv() {
        return av;
    }

    public void setAv(boolean av) {
        this.av = av;
    }

    public boolean isArrGauche() {
        return arrGauche;
    }

    public void setArrGauche(boolean arrGauche) {
        this.arrGauche = arrGauche;
    }

    public boolean isArrMilieu() {
        return arrMilieu;
    }

    public void setArrMilieu(boolean arrMilieu) {
        this.arrMilieu = arrMilieu;
    }

    public boolean isArrDroite() {
        return arrDroite;
    }

    public void setArrDroite(boolean arrDroite) {
        this.arrDroite = arrDroite;
    }

    public boolean isArrArrDroite() {
        return arrArrDroite;
    }

    public void setArrArrDroite(boolean arrArrDroite) {
        this.arrArrDroite = arrArrDroite;
    }

    public boolean isArrArrGauche() {
        return arrArrGauche;
    }

    public void setArrArrGauche(boolean arrArrGauche) {
        this.arrArrGauche = arrArrGauche;
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
        if (!(object instanceof Place)) {
            return false;
        }
        Place other = (Place) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Place[ id=" + id + " ]";
    }
    
}
