/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Patient;
import entities.Prestation;
import entities.RendezVous;
import entities.Specialite;
import java.time.LocalDate;

/**
 *
 * @author junio
 */
public class RendezVousDTO {

    private int idRendezVous;
    private LocalDate createDateTime;
    private LocalDate dateRendezVous;
    private String etat;
    private Patient patient;
    private Specialite specialite;
    private Prestation prestation;
    private String consultOrPresta;
    private String consultOrPrestaType;

    public String getConsultOrPrestaType() {
        return consultOrPrestaType;
    }

    public void setConsultOrPresta() {
        if(this.prestation== null){
        
            this.consultOrPresta = "CONSULTATION";
        
        }else{
        
            this.consultOrPresta = "PRESTATION";
        }
    }

    public String getConsultOrPresta() {
               
        return consultOrPresta;
    }

    public void setConsultOrPrestaType() {
        if(this.prestation== null){
        
            this.consultOrPrestaType = specialite.getLibelle();
        
        }else{
        
            this.consultOrPrestaType= prestation.getLibelle();
        }
        
    }

    public RendezVousDTO() {
    }

    public void toDto(RendezVous rdv) {
        this.idRendezVous = rdv.getIdRendezVous();
        this.createDateTime = rdv.getCreateDateTime();
        this.dateRendezVous = rdv.getDateRendezVous();
        this.patient = rdv.getPatient();
        this.prestation = rdv.getPrestation();
        this.specialite = rdv.getSpecialite();

    }

    public int getIdRendezVous() {
        return idRendezVous;
    }

    public void setIdRendezVous(int idRendezVous) {
        this.idRendezVous = idRendezVous;
    }

    public LocalDate getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDate createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDate getDateRendezVous() {
        return dateRendezVous;
    }

    public void setDateRendezVous(LocalDate dateRendezVous) {
        this.dateRendezVous = dateRendezVous;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }

    public Prestation getPrestation() {
        return prestation;
    }

    public void setPrestation(Prestation prestation) {
        this.prestation = prestation;
    }

}
