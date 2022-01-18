/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.Service;
import dto.ConsultationDTo;
import dto.RendezVousDTO;
import java.util.List;

import entities.Consultation;
import entities.Docteur;
import entities.Ordonnance;
import entities.Patient;
import entities.Specialite;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DaoService;

/**
 *
 * @author junio
 */
public class ConsultationDao implements IDao<Consultation> {
    
    private final String SQL_INSERT="INSERT INTO `consultation` (`statut`, `date`, `patient_code` , `medecin_id`, `rdv_id`, `specialite_id`) VALUES ( ?, ?, ?,?,?,?)";
    private final String  SQL_FIND_RDV_BY_ETAT_DATE_BY_DOCTOR= "SELECT * FROM consultation WHERE  `statut` like ? OR `date` like ? AND`medecin_id` = ?  "; 
    private final String SQL_UPDATE = "UPDATE `consultation` SET  `ordonnance_id` = ? , `statut` = ?, `prestation_id` = ? , `constante` = ? WHERE `consultation`.`id_consultation` = ? ";
    private final String SQL_SELECT_BY_ID ="SELECT * FROM `consultation`  WHERE id_consultation = ? ";
    private final DataBase database= new DataBase();
    private final PatientDao patientDao = new PatientDao();
    private final SpecialiteDao speDao = new SpecialiteDao();
    private final DocteurDao docDao = new DocteurDao();
    private final RendezVousDao rdvDao= new RendezVousDao();
    //private final OrdonnanceDao ordDao = new OrdonnanceDao();
    DaoService daoService = new DaoService();
    @Override
    public int insert(Consultation ogj) {
        int lastInsertId=0;
        database.openConnexion();
        database.initPrepareStatement(SQL_INSERT);
        try {
            database.getPs().setString(1, ogj.getStatus());
            database.getPs().setString(2, daoService.lts(ogj.getStartDate()));
            database.getPs().setString(3, ogj.getRdv().getPatient().getCode());
            System.out.println(ogj.getDocteur().getIdUser());
            database.getPs().setInt(4, ogj.getDocteur().getIdUser());
            database.getPs().setInt(5, ogj.getRdv().getIdRendezVous());
            database.getPs().setString(6, ogj.getConstante());
            database.getPs().setInt(7, ogj.getSpecialite().getIdSpecialite());
            database.executeUpdate(SQL_INSERT);
            ResultSet rs=database.getPs().getGeneratedKeys();
            if(rs.next()){
            lastInsertId=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        database.closeConnexion();
        return lastInsertId;
    }

    @Override
    public int update(Consultation ogj) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int update(ConsultationDTo consultation) {
       int id=0;
       
       
        try {
             database.openConnexion();
             database.initPrepareStatement(SQL_UPDATE);
            
            database.getPs().setInt(1, consultation.getOrdonnance().getIdOrdonnance());
            database.getPs().setString(2, consultation.getStatus());
            database.getPs().setInt(3, consultation.getRdv().getIdRendezVous());
            database.getPs().setInt(4, consultation.getIdConsultation());
            id = database.executeUpdate(SQL_UPDATE);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    } 
    @Override
    public int delete(int id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Consultation> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Consultation findById(int id) {
        Consultation c = null;
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_SELECT_BY_ID);
            database.getPs().setInt(1, id);
            
            ResultSet rs = database.executeSelect(SQL_SELECT_BY_ID);
            
            if(rs.next())
            {
                Specialite sp = speDao.findById(rs.getInt("specialite_id"));
                Patient pat = patientDao.findByCode(rs.getString("patient_code"));
                System.out.print(pat);
                Docteur doc = docDao.findById(rs.getInt("medecin_id"));
                RendezVousDTO rdvDto = rdvDao.findById(rs.getInt("rdv_id"));
               // Ordonnance ordonnance = ordDao.findById(rs.getInt("ordonnance_id"));
                c = new Consultation(
                rs.getInt("id_consultation"),
                        sp,
                        pat,
                        doc,
                        daoService.stl(rs.getString("date")),
                        rdvDto,
                        rs.getString("statut"),
                        rs.getInt("ordonnance_id")
                );
                c.setConstante(rs.getString("constante"));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

  

    public List<Consultation> findByEtatDate(String etat, String date,int id) {
        List<Consultation> consList =new ArrayList();
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_FIND_RDV_BY_ETAT_DATE_BY_DOCTOR);
            database.getPs().setString(1, etat);
            database.getPs().setString(2, date);
            database.getPs().setInt(3, id);
            ResultSet rs =database.executeSelect(SQL_FIND_RDV_BY_ETAT_DATE_BY_DOCTOR);
            while(rs.next()){
                Specialite sp = speDao.findById(rs.getInt("specialite_id"));
                Patient pat = patientDao.findByCode(rs.getString("patient_code"));
                System.out.print(pat);
                Docteur doc = docDao.findById(rs.getInt("medecin_id"));
                RendezVousDTO rdvDto = rdvDao.findById(rs.getInt("rdv_id"));
                RendezVousDTO prestation = rdvDao.findById(rs.getInt("prestation_id"));
                
               // Ordonnance ordonnance = ordDao.findById(rs.getInt("ordonnance_id"));
                Consultation  consultation = new Consultation(
                rs.getInt("id_consultation"),
                        sp,
                        pat,
                        doc,
                        daoService.stl(rs.getString("date")),
                        rdvDto,
                        rs.getString("statut"),
                        rs.getInt("ordonnance_id")
                );
                consultation.setConstante(rs.getString("constante"));
                consultation.setPrestation(prestation);
                consList.add(consultation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.print(consList);
        return consList;
    }
    
}
