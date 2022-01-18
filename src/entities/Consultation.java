/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dto.RendezVousDTO;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author junio
 */
public class Consultation {
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
    //variable espagheti en attendant une solution
    private int idOrdonnance;

    public int getIdOrdonnance() {
        return idOrdonnance;
    }

    public void setIdOrdonnance(int idOrdonnance) {
        this.idOrdonnance = idOrdonnance;
    }
    
        public Consultation(int id,Specialite specialite, Patient patient ,  Docteur docteur,LocalDate startDate,RendezVousDTO rdv,String status,int idOrdo) {
        this.idConsultation=id;
        this.status = status;
        this.constante = constante;
        this.startDate = startDate;
        this.prestation = prestation;
        this.docteur = docteur;
        this.specialite=specialite;
        this.rdv = rdv;
        this.patient = patient;
        this.idOrdonnance = idOrdo;
        
    }
    
    //fin
    
    public Consultation(Specialite specialite, Patient patient ,  Docteur docteur,LocalDate startDate,RendezVousDTO rdv,String status) {
        
        this.status = status;
        this.constante = constante;
        this.startDate = startDate;
        this.prestation = prestation;
        this.docteur = docteur;
        this.specialite=specialite;
        this.rdv = rdv;
        
    }
    
    public Consultation(int id,Specialite specialite, Patient patient ,  Docteur docteur,LocalDate startDate,RendezVousDTO rdv,String status) {
        this.idConsultation=id;
        this.status = status;
        this.constante = constante;
        this.startDate = startDate;
        this.prestation = prestation;
        this.docteur = docteur;
        this.specialite=specialite;
        this.rdv = rdv;
        this.patient = patient;
        
    }
    
    public Consultation(int id,Specialite specialite, Patient patient ,  Docteur docteur,LocalDate startDate,RendezVousDTO rdv,String status,Ordonnance ordonnance) {
        this.idConsultation=id;
        this.status = status;
        this.constante = constante;
        this.startDate = startDate;
        this.prestation = prestation;
        this.docteur = docteur;
        this.specialite=specialite;
        this.rdv = rdv;
        this.patient = patient;
        this.ordonnance = ordonnance;
        
    }
    
    

    

    public Consultation(String status, String constante, LocalDate startDate, Ordonnance ordonnance) {
        this.status = status;
        this.constante = constante;
        this.startDate = startDate;
        this.ordonnance = ordonnance;
    }

    public Consultation(String status, String constante, LocalDate startDate, Patient patient, Docteur docteur) {
        this.status = status;
        this.constante = constante;
        this.startDate = startDate;
        this.patient = patient;
        this.docteur = docteur;
    }
    
    public Consultation(int idConsultation, String status) {
        this.idConsultation = idConsultation;
        this.status = status;
    }
    public int getIdConsultation() {
        return idConsultation;
    }
    public RendezVousDTO getPrestation() {
        return this.prestation;
    }
    public void setPrestation(RendezVousDTO prestation) {
        this.prestation = prestation;
    }
    public Ordonnance getOrdonnance() {
        return ordonnance;
    }
    public void setOrdonnance(Ordonnance ordonnance) {
        this.ordonnance = ordonnance;
    }
    public Docteur getDocteur() {
        return this.docteur;
    }
    public void setDocteur(Docteur docteur) {
        this.docteur = docteur;
    }
    public Patient getPatient() {
        return this.patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
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
    public void setIdConsultation(int idConsultation) {
        this.idConsultation = idConsultation;
    }

    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }

    public RendezVousDTO getRdv() {
        return rdv;
    }

    public void setRdv(RendezVousDTO rdv) {
        this.rdv = rdv;
    }
    
    
    
    
}
