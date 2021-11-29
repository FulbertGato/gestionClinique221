/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import dto.RendezVousDTO;
import java.sql.Date;
import java.util.List;

import entities.Consultation;
import entities.DetailPrestation;
import entities.Docteur;
import entities.Patient;
import entities.Prestation;
import entities.RendezVous;
import entities.Role;
import entities.Specialite;
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
    public List<Docteur> searchDoctorBySpecialiter(String consultOrPrestaType);
    public int addDoctor(Docteur docteur);
    public List<Patient> showAllPatients();
    public List<Role> showAllRole();
    public int addRole(Role role);


    public int addRendezVous(RendezVousDTO rendezVous);
    //public void cancelRendezVous(int id);
    public int etatRendezVousSet(int id, String action);
    public List<RendezVousDTO> showAllRendezVous();
    public List<RendezVousDTO> showAllRendezVous(String etat, String code);
    public List<RendezVousDTO> showAllRendezVous(String etat);
    public RendezVousDTO showRendezVousById(int id);

    
    
    public int addConsultation(Consultation consultation);
    public List<Consultation> showAllConsultation();
    public List<Consultation> showAllConsultation(String etat);
    public List<Consultation> showAllConsultation(String etat, Date date);
    public int etatConsultation(int id, String action);
    

    public List<Prestation> showAllPrestation();
    public List<Prestation> showAllPrestation(String etat);
    public List<Prestation> showAllPrestation(String etat, Date date);
    public DetailPrestation detail(int id);
    public int addDetailPrestation(DetailPrestation pres);
    public void resultatImport (int id);

    public Patient searchPatientByCode(String code);
    public Patient searchPatientById(int id);
    public Patient searchPatientByEmail(String email);
    public Consultation searchConsultation(int id);
    public Prestation searchPrestationn(int id);

    public Role getRoleUserById(int id);
    public List<Docteur> getListDoctorByType(int id);
    public User getUserByLogin(String login);
    public List<Specialite> showAllSpecialite();
    






    
}
