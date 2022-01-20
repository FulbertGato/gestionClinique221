/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.RendezVousDTO;
import entities.Consultation;
import entities.DetailPrestation;
import entities.Docteur;
import entities.Patient;
import java.util.List;

import entities.Prestation;
import entities.Specialite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author junio
 */
public class PrestationDao implements IDao<Prestation> {
DataBase database= new DataBase();
    
    private final String  SQL_FIND_ALL = "SELECT * FROM `prestation`";
    private final String SQL_SELECT_PRESTATION_BY_ID =" SELECT * FROM prestation  WHERE id_prestation =? ";
     
    @Override
    public int insert(Prestation ogj) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int update(Prestation ogj) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int delete(int id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Prestation> findAll() {
       List<Prestation> prestations =new ArrayList<Prestation>();
        database.openConnexion();
        try {
            
            database.initPrepareStatement(SQL_FIND_ALL);
            ResultSet rs =database.executeSelect(SQL_FIND_ALL);
            while(rs.next()){
                
                Prestation p = new Prestation(
                        rs.getInt("id_prestation"),
                        rs.getString("libelle")
                );
               prestations.add(p);
               
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Prestation.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
     
         return prestations;
    }

    @Override
    public Prestation findById(int id) {
       Prestation p = null;
       if(id==0){
       
       
       }else{
       
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_SELECT_PRESTATION_BY_ID);
            database.getPs().setInt(1, id);
            ResultSet rs = database.executeSelect(SQL_SELECT_PRESTATION_BY_ID);
            if(rs.next())
            {
                
                p = new Prestation(
                    rs.getInt("id_prestation"),
                    rs.getString("libelle")
                );
               
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }
       }
        return p;
    }
    
    
    
}
