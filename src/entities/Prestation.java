/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dto.RendezVousDTO;

/**
 *
 * @author junio
 */
public class Prestation {

    private int idPrestation;
    private String libelle;

    
    public Prestation(String libelle) {
        this.libelle = libelle;
    }
    public Prestation(int idPrestation, String libelle) {
        this.idPrestation = idPrestation;
        this.libelle = libelle;
    }

   
    public int getIdPrestation() {
        return idPrestation;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public void setIdPrestation(int idPrestation) {
        this.idPrestation = idPrestation;
    }

    @Override
    public String toString() {
        return libelle;
    }
    
}
