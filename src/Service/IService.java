/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.sql.Date;
import java.util.List;

import entities.Consultation;
import entities.DetailPrestation;
import entities.Docteur;
import entities.Patient;
import entities.Prestation;
import entities.RendezVous;
import entities.Role;
import entities.User;

/**
 *
 * @author junio
 */
public interface IService {

    public User login(String email, String password);
    public int addPatient(Patient patient);
    public List<User> showAllUsers();
    public int addUser(User user);
    public List<Docteur> showAllDocteurs();
    public int addDoctor(Docteur docteur);
    public List<Patient> showAllPatients();
    public List<Role> showAllRole();
    public int addRole(Role role);


    public int addRendezVous(RendezVous rendezVous);
    public int etatRendezVousSet(int id, String action);
    public List<RendezVous> showAllRendezVous();
    public List<RendezVous> showAllRendezVous(String etat);

    public List<Consultation> showAllConsultation();
    public List<Consultation> showAllConsultation(String etat);
    public List<Consultation> showAllConsultation(String etat, Date date);
    public int etatConsultation(int id, String action);
    

    public List<Prestation> showAllPrestation();
    public List<Prestation> showAllPrestation(String etat);
    public List<Prestation> showAllPrestation(String etat, Date date);
    public DetailPrestation detail(int id);
    public void resultatImport (int id);

    public Patient searchPatientByCode(String code);
    public Consultation searchConsultation(int id);
    public Prestation searchPrestationn(int id);

    public Role getRoleUserById(int id);
    public List<Docteur> getListDoctorByType(int id);
    







    
}
