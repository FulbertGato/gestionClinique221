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
public class Medicament {
    
    private int idMedicament;
    private String libelle;


    public Medicament(String libelle) {
        this.libelle = libelle;
    }

    public Medicament(int idMedicament, String libelle) {
        this.idMedicament = idMedicament;
        this.libelle = libelle;
    }
    
    public String getLibelle() {
        return libelle;
    }
    public int getIdMedicament() {
        return idMedicament;
    }
    public void setIdMedicament(int idMedicament) {
        this.idMedicament = idMedicament;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
