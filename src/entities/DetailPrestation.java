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
public class DetailPrestation {
    
    private int idDetails;
    private Prestation prestation;
    private String resultats;
    private String status;
    private Patient patient;
    private User responsable;
    
    public DetailPrestation(Prestation prestation, String resultats, String status, Patient patient, User responsable) {
        this.prestation = prestation;
        this.resultats = resultats;
        this.status = status;
        this.patient = patient;
        this.responsable = responsable;
    }
    public DetailPrestation(int idDetails, Prestation prestation, String resultats, String status, Patient patient,
            User responsable) {
        this.idDetails = idDetails;
        this.prestation = prestation;
        this.resultats = resultats;
        this.status = status;
        this.patient = patient;
        this.responsable = responsable;
    }
    public int getIdDetails() {
        return idDetails;
    }
    public User getResponsable() {
        return responsable;
    }
    public void setResponsable(User responsable) {
        this.responsable = responsable;
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

}
