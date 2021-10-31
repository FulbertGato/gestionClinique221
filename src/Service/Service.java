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
import dao.ConsultationDao;
import dao.DocteurDao;
import dao.PatientDao;
import dao.PrestationDao;
import dao.RendezVousDao;
import dao.RoleDao;
import dao.UserDao;
/**
 *
 * @author junio
 */
public class Service implements IService{
    UserDao daoUser=new UserDao();
    PatientDao daoPatient = new PatientDao();
    DocteurDao daoDocteur = new DocteurDao();
    RoleDao daoRole = new RoleDao();
    RendezVousDao daoRdv = new RendezVousDao();
    ConsultationDao daoConsul = new ConsultationDao();
    PrestationDao daoPresta = new PrestationDao();

    @Override
    public User login(String email, String password) {
        return daoUser.findUserByLoginPassword(email,password);
    }

    @Override
    public int addPatient(Patient patient) {
        return daoPatient.insert(patient);
    }

    @Override
    public List<User> showAllUsers() {
       return daoUser.findAll();
    }

    @Override
    public int addUser(User user) {
        return daoUser.insert(user);
    }

    @Override
    public List<Docteur> showAllDocteurs() {
        return daoDocteur.findAll();
    }

    @Override
    public int addDoctor(Docteur docteur) {
        
         return daoDocteur.insert(docteur);
    }

    @Override
    public List<Patient> showAllPatients() {
        return daoPatient.findAll();
    }

    @Override
    public List<Role> showAllRole() {
        return daoRole.findAll();
    }

    @Override
    public int addRole(Role role) {
        return daoRole.insert(role);
    }

    @Override
    public int addRendezVous(RendezVous rendezVous) {
        return daoRdv.insert(rendezVous);
    }

    @Override
    public int etatRendezVousSet(int id, String action) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<RendezVous> showAllRendezVous() {
        
       return daoRdv.findAll();
    }

    @Override
    public List<RendezVous> showAllRendezVous(String etat) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Consultation> showAllConsultation() {
        
        return daoConsul.findAll();
    }

    @Override
    public List<Consultation> showAllConsultation(String etat) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Consultation> showAllConsultation(String etat, Date date) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int etatConsultation(int id, String action) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Prestation> showAllPrestation() {
        
        return daoPresta.findAll();
    }

    @Override
    public List<Prestation> showAllPrestation(String etat) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Prestation> showAllPrestation(String etat, Date date) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DetailPrestation detail(int id) {
        //TODO Auto-generated method stub
        return null;
    }

    @Override
    public void resultatImport(int id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Patient searchPatientByCode(String code) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Consultation searchConsultation(int id) {
        
        return daoConsul.findById(id);
    }

    @Override
    public Prestation searchPrestationn(int id) {
        
        return daoPresta.findById(id);
    }

    @Override
    public Role getRoleUserById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Docteur> getListDoctorByType(int id) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
