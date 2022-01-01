/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.HashMap;

import entities.Medicament;

public class MedicamentDTO{

    private String posologie;
    private Medicament medicament;
   
    
    public Medicament getMedicament() {
        return medicament;
    }
    public String getPosologie() {
        return posologie;
    }
    public void setPosologie(String posologie) {
        this.posologie = posologie;
    }
    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }
}