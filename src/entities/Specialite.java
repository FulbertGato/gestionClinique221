/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author junio
 */
public class Specialite {
    private int idSpecialite;
    private String libelle;
    
    public Specialite() {
    }
    
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getIdSpecialite() {
        return idSpecialite;
    }

    public void setIdSpecialite(int idSpecialite) {
        this.idSpecialite = idSpecialite;
    }

    public Specialite(int idSpecialite, String libelle) {
        this.setIdSpecialite(idSpecialite);
        this.setLibelle(libelle);
    }

    public Specialite(String libelle) {
        this.setLibelle(libelle);
    }
    
}
