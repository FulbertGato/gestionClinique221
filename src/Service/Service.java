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
import entities.Specialite;
import entities.User;
import dao.ConsultationDao;
import dao.DetailPrestationDao;
import dao.DocteurDao;
import dao.PatientDao;
import dao.PrestationDao;
import dao.RendezVousDao;
import dao.RoleDao;
import dao.SpecialiteDao;
import dao.UserDao;
import dto.RendezVousDTO;
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
    SpecialiteDao daoSpe = new SpecialiteDao();
    DetailPrestationDao daoPrestaDetail = new DetailPrestationDao ();

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
    public List<Docteur> searchDoctorBySpecialiter(String consultOrPrestaType) {
        
        return daoDocteur.findBySpecialite(consultOrPrestaType);
    }
    
    @Override
    public int nombrerdvDoc(int id) {
        return daoDocteur.nbreRdv(id);
    }
    
    @Override
    public int nombrerdvDoc(int idUser, String d) {
        return daoDocteur.nbreRdv(idUser,d);
        
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
    public int addRendezVous(RendezVousDTO rendezVous) {
        return daoRdv.insert(rendezVous);
    }

    @Override
    public int etatRendezVousSet(int id, String action) {
        
        return daoRdv.setEtatRendezVous(id,action);
    }

    @Override
    public List<RendezVousDTO> showAllRendezVous() {
        
       return daoRdv.findAll();
    }

    @Override
    public List<RendezVousDTO> showAllRendezVous(String etat, String code) {
       
        return daoRdv.findByEtatCode(code, etat);
    }
    
    @Override
    public List<RendezVousDTO> showAllRendezVous(String etat) {
       
        return daoRdv.findByEtat(etat);
    }
     


    
    
       @Override
    public int addConsultation(Consultation consultation) {
        return daoConsul.insert(consultation);
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
    public List<Consultation> showAllConsultation(String etat, String date,int id) {
         return daoConsul.findByEtatDate(etat,date,id);
    }

    @Override
    public int etatConsultation(int id, String action) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    
     @Override
    public int addDetailPrestation(DetailPrestation pres) {
       return daoPrestaDetail.insert(pres);
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
     public RendezVousDTO showRendezVousById(int id) {
       return daoRdv.findById(id);
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

    @Override
    public User getUserByLogin(String login) {
       
        return daoUser.getUserByLogin(login);
    }

    @Override
    public List<Specialite> showAllSpecialite() {
       return daoSpe.findAll();
    }
    @Override
    public Patient searchPatientByEmail(String email) {
        
        return daoPatient.findByEmail(email);
    }

    @Override
    public Patient searchPatientById(int id) {
        return daoPatient.findById(id);
    }

    @Override
    public Docteur docteurById(int id) {
        return daoDocteur.findById(id);
    }

    

 

   

    


   

     
}




