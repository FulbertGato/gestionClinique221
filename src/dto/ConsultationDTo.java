/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Consultation;
import entities.Docteur;
import entities.Ordonnance;
import entities.Patient;
import entities.Prestation;
import entities.Specialite;
import java.time.LocalDate;

/**
 *
 * @author junio
 */
public class ConsultationDTo {
    private int idConsultation;
    private String status;
    private String constante;
    private LocalDate startDate;
    private Patient patient;
    private Docteur docteur;
    private Specialite specialite;
    private Ordonnance ordonnance;
    private RendezVousDTO prestation;
    private RendezVousDTO rdv;
    private String nomPatient;
    private String antPatient;
    private String telPtaient;
    private int idOrdonnance;

   
    public ConsultationDTo(){}
    public void toDto(Consultation cons) {
        this.constante=cons.getConstante();
        this.docteur=cons.getDocteur();
        this.idConsultation=cons.getIdConsultation();
        this.idOrdonnance=cons.getIdOrdonnance();
        this.ordonnance=cons.getOrdonnance();
        this.patient=cons.getPatient();
        this.prestation=cons.getPrestation();
        this.specialite=cons.getSpecialite();
        this.rdv=cons.getRdv();
        this.startDate=cons.getStartDate();
        this.status=cons.getStatus();
        

    }

    public int getIdConsultation() {
        return idConsultation;
    }

    public void setIdConsultation(int idConsultation) {
        this.idConsultation = idConsultation;
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

    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getConstante() {
        return constante;
    }

    public void setConstante(String constante) {
        this.constante = constante;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
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

    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }

    public Ordonnance getOrdonnance() {
        return ordonnance;
    }

    public void setOrdonnance(Ordonnance ordonnance) {
        this.ordonnance = ordonnance;
    }

    public RendezVousDTO getPrestation() {
        return prestation;
    }

    public void setPrestation(RendezVousDTO prestation) {
        this.prestation = prestation;
    }

    public RendezVousDTO getRdv() {
        return rdv;
    }

    public void setRdv(RendezVousDTO rdv) {
        this.rdv = rdv;
    }
    
     public int getIdOrdonnance() {
        return idOrdonnance;
    }

    public void setIdOrdonnance(int idOrdonnance) {
        this.idOrdonnance = idOrdonnance;
    }
}
