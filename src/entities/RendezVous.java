/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author junio
 */
public class RendezVous {
    private int idRendezVous;
    private LocalDate createDateTime;
    private LocalDate dateRendezVous;
    private String etat;
    private Patient patient ;
    private Specialite specialite;
    private Prestation prestation;
    
 //prestation
    public RendezVous(LocalDate createDateTime, LocalDate dateRendezVous, String etat, Patient patient, Prestation prestation) {
        this.createDateTime = createDateTime;
        this.dateRendezVous = dateRendezVous;
        this.etat = etat;
        this.patient = patient;
        this.prestation = prestation;
    }

    public RendezVous(LocalDate createDateTime, LocalDate dateRendezVous, String etat, Patient patient, Prestation prestation,Specialite specialite) {
        this.createDateTime = createDateTime;
        this.dateRendezVous = dateRendezVous;
        this.etat = etat;
        this.patient = patient;
        this.prestation = prestation;
        this.specialite = specialite;
    }




//consulation
    public RendezVous(LocalDate createDateTime, LocalDate dateRendezVous, String etat, Patient patient, Specialite specialite) {
        this.createDateTime = createDateTime;
        this.dateRendezVous = dateRendezVous;
        this.etat = etat;
        this.patient = patient;
        this.specialite = specialite;
    }

    


    public RendezVous(int idRendezVous, String etat) {
        this.idRendezVous = idRendezVous;
        this.etat = etat;
    }
    

    public int getIdRendezVous() {
        return idRendezVous;
    }

    public Prestation getPrestation() {
        return prestation;
    }

    public void setPrestation(Prestation prestation) {
        this.prestation = prestation;
    }

    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public LocalDate getDateRendezVous() {
        return dateRendezVous;
    }

    public void setDateRendezVous(LocalDate dateRendezVous) {
        this.dateRendezVous = dateRendezVous;
    }

    public LocalDate getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDate createDateTime) {
        this.createDateTime = createDateTime;
    }
   



}
