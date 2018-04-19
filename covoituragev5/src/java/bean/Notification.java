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

/**
 *
 * @author IlyassElfouih
 */
@Entity
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int vu ;//0 n'est pas vu ,1 vu ,2 vu et acccepter ,3 vu par le passager
    //envoyer par ?
    private String qui;
    private Long  idCircuitVoyage;
    @ManyToOne
    private Personne personne ;//emmeteur
    @ManyToOne
    private Voyage voyage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVu() {
        return vu;
    }

    public void setVu(int vu) {
        this.vu = vu;
    }

    public String getQui() {
        return qui;
    }

    public void setQui(String qui) {
        this.qui = qui;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Voyage getVoyage() {
        return voyage;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
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
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Notification[ id=" + id + " ]";
    }

    public Long getIdCircuitVoyage() {
        return idCircuitVoyage;
    }

    public void setIdCircuitVoyage(Long idCircuitVoyage) {
        this.idCircuitVoyage = idCircuitVoyage;
    }

    
    
    
}
