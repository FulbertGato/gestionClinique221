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
public class DetailPrestation {
    
    private int idDetails;
    private Prestation prestation;
    private String resultats;
    private String date;
    private String status;
    private Patient patient;
    private Docteur docteur;
    private  RendezVousDTO rdv;
    
    public DetailPrestation(Prestation prestation, String resultats, String status, Patient patient, Docteur responsable) {
        this.prestation = prestation;
        this.resultats = resultats;
        this.status = status;
        this.patient = patient;
        this.docteur = responsable;
    }
    public DetailPrestation(Prestation prestation, Patient patient, Docteur responsable,String status) {
        this.prestation = prestation;
        this.status = status;
        this.patient = patient;
        this.docteur = responsable;
    }
    public DetailPrestation(int idDetails, Prestation prestation, String resultats, String status, Patient patient,
           Docteur responsable) {
        this.idDetails = idDetails;
        this.prestation = prestation;
        this.resultats = resultats;
        this.status = status;
        this.patient = patient;
        this.docteur = responsable;
    }

    public DetailPrestation(int idDetails, Prestation prestation, Patient pat, Docteur doc, String date, RendezVousDTO rdvDto, String status) {
        
         this.idDetails = idDetails;
        this.prestation = prestation;
        this.date=date;
        this.rdv = rdvDto;
        this.status = status;
        this.patient = pat;
        this.docteur = doc;
        
    }
    public int getIdDetails() {
        return idDetails;
    }
    public User getResponsable() {
        return docteur;
    }
    public void setResponsable(Docteur responsable) {
        this.docteur = responsable;
    }
    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getResultats() {
        return resultats;
    }
    public void setResultats(String resultats) {
        this.resultats = resultats;
    }
    public Prestation getPrestation() {
        return prestation;
    }
    public void setPrestation(Prestation prestation) {
        this.prestation = prestation;
    }
    public void setIdDetails(int idDetails) {
        this.idDetails = idDetails;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Docteur getDocteur() {
        return docteur;
    }

    public void setDocteur(Docteur docteur) {
        this.docteur = docteur;
    }

    public RendezVousDTO getRdv() {
        return rdv;
    }

    public void setRdv(RendezVousDTO rdv) {
        this.rdv = rdv;
    }

    
    
}
