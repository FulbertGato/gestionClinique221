/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

import entities.Consultation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.daoService;

/**
 *
 * @author junio
 */
public class ConsultationDao implements IDao<Consultation> {
    
    private final String SQL_INSERT="INSERT INTO `consultation` (`statut`, `date`, `patient_code` , `medecin_id`, `rdv_id`, `specialite_id`) VALUES ( ?, ?, ?,?,?,?)";
    private final DataBase database= new DataBase();
    daoService daoService = new daoService();
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
            database.getPs().setInt(6, ogj.getSpecialite().getIdSpecialite());
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
        // TODO Auto-generated method stub
        return null;
    }
    
}
