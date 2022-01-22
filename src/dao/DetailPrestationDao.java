/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.DetailPrestationDto;
import dto.RendezVousDTO;
import entities.DetailPrestation;
import entities.Docteur;
import entities.Patient;
import entities.Prestation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author junio
 */
public class DetailPrestationDao implements IDao<DetailPrestation> {
    private final DataBase database= new DataBase();
    private final PatientDao patientDao = new PatientDao();
    private final PrestationDao speDao = new PrestationDao();
    private final DocteurDao docDao = new DocteurDao();
    private final RendezVousDao rdvDao= new RendezVousDao();
    
    private final String SQL_INSERT="INSERT INTO `details_prestations` (`prestation_id`,`date`, `statut`, `patient_code` , `medecin_id`,`rdv_id`) VALUES ( ?, ?, ?,?,?,?)";
    private final String  SQL_FIND_RDV_BY_ETAT_DATE_BY_DOCTOR= "SELECT * FROM details_prestations WHERE  `statut` like ? OR `date` like ? AND`medecin_id` = ?  ";
    private final String  SQL_UPDATE_DETAIL_PRRESTATION="UPDATE `details_prestations` SET `statut` = ?,`resultat` = ?  WHERE `details_prestations`.`id_detail` = ?";

    @Override
    public int insert(DetailPrestation ogj) {
        int lastInsertId=0;
        database.openConnexion();
        database.initPrepareStatement(SQL_INSERT);
        try {
            database.getPs().setInt(1, ogj.getPrestation().getIdPrestation());
            database.getPs().setString(2, ogj.getStatus() /*ogj.getRdv().getCreateDateTime()*/);
            database.getPs().setString(3, ogj.getStatus());
            database.getPs().setString(4, ogj.getPatient().getCode());
            database.getPs().setInt(5, ogj.getResponsable().getIdUser());
            database.getPs().setInt(6, ogj.getRdv().getIdRendezVous());
            database.executeUpdate(SQL_INSERT);
            ResultSet rs=database.getPs().getGeneratedKeys();
            if(rs.next()){
            lastInsertId=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetailPrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        database.closeConnexion();
        return lastInsertId;
    }

    @Override
    public int update(DetailPrestation ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DetailPrestation> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DetailPrestation findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public List<DetailPrestation> findByEtatDate(String etat, String date, int id){
        List<DetailPrestation> presList =new ArrayList();
        try {


            database.openConnexion();
            database.initPrepareStatement(SQL_FIND_RDV_BY_ETAT_DATE_BY_DOCTOR);
            database.getPs().setString(1, etat);
            database.getPs().setString(2, date);
            database.getPs().setInt(3, id);
            ResultSet rs =database.executeSelect(SQL_FIND_RDV_BY_ETAT_DATE_BY_DOCTOR);
            while(rs.next()){
                Prestation sp = speDao.findById(rs.getInt("prestation_id"));
                Patient pat = patientDao.findByCode(rs.getString("patient_code"));
                System.out.print(pat);
                Docteur doc = docDao.findById(rs.getInt("medecin_id"));
                RendezVousDTO rdvDto = rdvDao.findById(rs.getInt("rdv_id"));
                RendezVousDTO prestation = rdvDao.findById(rs.getInt("prestation_id"));
                
               // Ordonnance ordonnance = ordDao.findById(rs.getInt("ordonnance_id"));
                DetailPrestation  presta = new DetailPrestation(
                        rs.getInt("id_detail"),
                        sp,
                        pat,
                        doc,
                        rs.getString("date"),
                        rdvDto,
                        rs.getString("statut")
                );
                presta.setResultats(rs.getString("resultat"));
                presList.add(presta);
            }


        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
         return presList;
    }

    public int update(DetailPrestationDto pres) {
        int idUpdate=0;
        try {
            
            database.openConnexion();
            database.initPrepareStatement(SQL_UPDATE_DETAIL_PRRESTATION);
            database.getPs().setString(1, pres.getStatus());
            database.getPs().setString(2, pres.getResultats());
            database.getPs().setInt(3, pres.getIdDetails());
            idUpdate=database.executeUpdate(SQL_UPDATE_DETAIL_PRRESTATION);
                
        } catch (SQLException ex) {
            Logger.getLogger(DetailPrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idUpdate;
    }
    
    
    
    
}
