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
public class Ordonnance {
    
  private int idOrdonnance;
  private String posologie;
  private Medicament medicament;
  private Consultation consultation;






    
    public Ordonnance(String posologie, Medicament medicament, Consultation consultation) {
    this.posologie = posologie;
    this.medicament = medicament;
    this.consultation = consultation;
    }
    public Ordonnance(int idOrdonnance, String posologie, Medicament medicament, Consultation consultation) {
    this.idOrdonnance = idOrdonnance;
    this.posologie = posologie;
    this.medicament = medicament;
    this.consultation = consultation;
    }
    public Consultation getConsultation() {
        return consultation;
    }
    public int getIdOrdonnance() {
        return idOrdonnance;
    }
    public void setIdOrdonnance(int idOrdonnance) {
        this.idOrdonnance = idOrdonnance;
    }
    public String getPosologie() {
        return posologie;
    }
    public void setPosologie(String posologie) {
        this.posologie = posologie;
    }
    public Medicament getMedicament() {
        return medicament;
    }
    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }
    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

}
