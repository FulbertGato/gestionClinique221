/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.RendezVousDTO;
import entities.Patient;
import entities.Prestation;
import java.util.List;

import entities.RendezVous;
import entities.Specialite;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author junio
 */
public class RendezVousDao implements IDao<RendezVousDTO>{
    private final String SQL_INSERT="INSERT INTO `rendez_vous` (`createAt`, `date`, `etat`, `patient_code`,  `specialite_id`, `prestation_id`) VALUES ( ?, ?, ?,  ?, ?, ?)";
    private final String  SQL_FIND_ALL = "SELECT * FROM `rendez_vous`";
    private final String  SQL_FIND_ALL_BY_ETAT_CODE = "SELECT * FROM `rendez_vous` WHERE `patient_code` LIKE  ? AND `etat` LIKE ?  ";
    private final PatientDao patientDao = new PatientDao();
    private final SpecialiteDao speDao = new SpecialiteDao();
    private final PrestationDao presDao = new PrestationDao();
    DataBase database= new DataBase();
    @Override
    public int insert(RendezVousDTO rendezVous) {
        int lastInsertId=0;
        database.openConnexion();
        database.initPrepareStatement(SQL_INSERT);
        
        try {
               
            
            
            database.getPs().setString(1, lts(rendezVous.getCreateDateTime()));
            database.getPs().setString(2,lts(rendezVous.getDateRendezVous()));
            database.getPs().setString(3, rendezVous.getEtat());
            database.getPs().setString(4, rendezVous.getPatient().getCode());
            if(rendezVous.getPrestation()==null){
            database.getPs().setInt(5, rendezVous.getSpecialite().getIdSpecialite());
            database.getPs().setInt(6, 0);
            }else{
            database.getPs().setInt(5, 0);
            database.getPs().setInt(6, rendezVous.getPrestation().getIdPrestation());
            }
            database.executeUpdate(SQL_INSERT);
            ResultSet rs=database.getPs().getGeneratedKeys();
            if(rs.next()){
            lastInsertId=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        database.closeConnexion();
        return lastInsertId;
    }

    @Override
    public int update(RendezVousDTO rendezVous ) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int delete(int id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<RendezVousDTO> findAll() {
        List<RendezVousDTO> rdvList =new ArrayList();
        
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_FIND_ALL);
            
            ResultSet rs =database.executeSelect(SQL_FIND_ALL);
            while(rs.next()){
                try {
                  LocalDate creaD = stl( rs.getString("createAt"));
                  LocalDate date = stl( rs.getString("date"));
                  Patient pat = patientDao.findByCode(rs.getString("patient_code"));
                  Specialite sp = speDao.findById(rs.getInt("specialite_id"));
                  Prestation pres = presDao.findById(rs.getInt("prestation_id"));
                  RendezVousDTO rdv = new RendezVousDTO();
                 rdv.setIdRendezVous(rs.getInt("id"));
                 rdv.setCreateDateTime(creaD);
                 rdv.setDateRendezVous(date);
                 rdv.setPatient(pat);
                 rdv.setPrestation(pres);
                 rdv.setSpecialite(sp);
                 rdv.setEtat(rs.getString("etat"));
                 rdv.setConsultOrPresta();
                 rdv.setConsultOrPrestaType();
                rdvList.add(rdv);
                } catch (SQLException ex) {
                    Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        System.out.println(rdvList);
        return rdvList;
    }

    @Override
    public RendezVousDTO findById(int id) {
        // TODO Auto-generated method stub
        return null;
    }
    
    
    public List<RendezVousDTO> findByEtatCode(String code, String etat) {
        List<RendezVousDTO> rdvList =new ArrayList();
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_FIND_ALL_BY_ETAT_CODE);
            
            database.getPs().setString(1, code);
            database.getPs().setString(2, etat);
            ResultSet rs =database.executeSelect(SQL_FIND_ALL_BY_ETAT_CODE);
            while(rs.next()){
                try {
                  LocalDate creaD = stl( rs.getString("createAt"));
                  LocalDate date = stl( rs.getString("date"));
                  Patient pat = patientDao.findByCode(rs.getString("patient_code"));
                  Specialite sp = speDao.findById(rs.getInt("specialite_id"));
                  Prestation pres = presDao.findById(rs.getInt("prestation_id"));
                  RendezVousDTO rdv = new RendezVousDTO();
                 rdv.setIdRendezVous(rs.getInt("id"));
                 rdv.setCreateDateTime(creaD);
                 rdv.setDateRendezVous(date);
                 rdv.setPatient(pat);
                 rdv.setPrestation(pres);
                 rdv.setSpecialite(sp);
                 rdv.setEtat(rs.getString("etat"));
                 rdv.setConsultOrPresta();
                 rdv.setConsultOrPrestaType();
                rdvList.add(rdv);
                } catch (SQLException ex) {
                    Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        System.out.println(rdvList);
        
        
        
       return rdvList;
    }
    
    
    public String lts(LocalDate lctdate){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-LLLL-yyyy");
      return lctdate.format(formatter);}
    
    public LocalDate stl(String date){
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-LLLL-yyyy");

     LocalDate conv = LocalDate.parse(date, formatter);
       return conv;
    }
    
    
}
