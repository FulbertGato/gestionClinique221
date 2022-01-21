/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.DetailPrestation;
import entities.Docteur;
import entities.Patient;
import entities.Prestation;
import entities.Specialite;

/**
 *
 * @author gatoj
 */
public class DetailPrestationDto {
    private int idDetails;
    private Prestation prestation;
    private String resultats;
    private String date;
    private String status;
    private Patient patient;
    private Docteur docteur;
    //private Specialite specialite;
    private  RendezVousDTO rdv;
    private String nomPatient;
    private String antPatient;
    private String telPtaient;
    
    
    public void toDto(DetailPrestation detail) {
        this.idDetails = detail.getIdDetails();
        this.prestation = detail.getPrestation();
        this.resultats = detail.getResultats();
        this.patient= detail.getPatient();
        this.status= detail.getStatus();
        this.date = detail.getDate();
        this.docteur = detail.getDocteur();
        this.rdv=detail.getRdv();
       // this.specialite= detail.getPrestation();
    }

    public int getIdDetails() {
        return idDetails;
    }

    public void setIdDetails(int idDetails) {
        this.idDetails = idDetails;
    }

    public Prestation getPrestation() {
        return prestation;
    }

    public void setPrestation(Prestation prestation) {
        this.prestation = prestation;
    }

    public String getResultats() {
        return resultats;
    }

    public void setResultats(String resultats) {
        this.resultats = resultats;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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
    
    
    
     public String getNomPatient() {
        return this.getPatient().getNomComplet();
    }

    

    public String getAntPatient() {
        return this.getPatient().getAntecedants();
    }

    

    public String getTelPtaient() {
        return this.getPatient().getTelephone();
    }
    
}
