/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;

import entities.Patient;
import entities.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author junio
 */
public class PatientDao implements IDao<Patient> {
    private final String SQL_INSERT="INSERT INTO `user` (`nomComplet`, `login`, `password`, `code`,  `antecedants`,`telephone`, `role_id`) VALUES ( ?,?, ?, ?,  ?, ?, ?)";
    private final String  SQL_FIND_ALL = "SELECT * FROM user WHERE code > 0";
    private final String  SQL_FIND_BY_EMAIL = "SELECT * FROM user WHERE email like ?";
     private final String  SQL_FIND_BY_CODE= "SELECT * FROM user WHERE code like ?";
    private final String SQL_SELECT_PATIENT_BY_ID =" SELECT * FROM user  WHERE id_user =? ";
    private final RoleDao roleDao=new RoleDao();
    DataBase database= new DataBase();

    @Override
    public int insert(Patient patient) {
        int lastInsertId=0;
        database.openConnexion();
        database.initPrepareStatement(SQL_INSERT);
        try {
            database.getPs().setString(1, patient.getNomComplet());
            database.getPs().setString(2, patient.getLogin());
            database.getPs().setString(3, patient.getPassword());
            database.getPs().setString(4, patient.getCode());
            database.getPs().setString(5, patient.getAntecedants());
            database.getPs().setString(6, patient.getTelephone());
            database.getPs().setInt(7, patient.getRoleId());
            database.executeUpdate(SQL_INSERT);
            ResultSet rs=database.getPs().getGeneratedKeys();
            if(rs.next()){
            lastInsertId=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        database.closeConnexion();
        return lastInsertId;
    }

    @Override
    public int update(Patient ogj) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int delete(int id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> patients=new ArrayList<>();
        
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_FIND_ALL);
            ResultSet rs =database.executeSelect(SQL_FIND_ALL);
            while(rs.next()){
                try {
                    
                Role role = new Role(rs.getInt("role_id"),rs.getString("libelle"));

                Patient patient = new Patient(
                rs.getInt("id_user"),
                rs.getString("nomComplet"),
                rs.getString("login"),
                rs.getString("password"), 
                role,
                rs.getString("code"), 
                rs.getString("antecedants"),rs.getString("telephone")
                );
                patients.add(patient);
                } catch (SQLException ex) {
                    Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return patients;
    }

    @Override
    public Patient findById(int id) {
        Patient p = null;
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_SELECT_PATIENT_BY_ID);
            database.getPs().setInt(1, id);
            ResultSet rs = database.executeSelect(SQL_SELECT_PATIENT_BY_ID);
            if(rs.next())
            {
                Role role = roleDao.findById(rs.getInt("role_id"));
                p = new Patient(
                    rs.getInt("id_user"),
                    rs.getString("nomComplet"),
                    rs.getString("login"),
                    rs.getString("password"),
                    role,
                    rs.getString("code"), 
                    rs.getString("antecedants"),
                    rs.getString("telephone")
                );
               
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }
        return p;
    }

    public Patient findByEmail(String email) {

      
       
        return null;  
    }

    Patient findByCode(String code) {
        
        Patient p = null;
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_FIND_BY_CODE);
            database.getPs().setString(1, code);
            ResultSet rs = database.executeSelect(SQL_FIND_BY_CODE);
            if(rs.next())
            {
                Role role = roleDao.findById(rs.getInt("role_id"));
                p = new Patient(
                    rs.getInt("id_user"),
                    rs.getString("nomComplet"),
                    rs.getString("login"),
                    rs.getString("password"),
                    role,
                    rs.getString("code"), 
                    rs.getString("antecedants"),
                    rs.getString("telephone")
                );
               
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }
        return p;
        
        
    }

    
  
    
}
