/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Specialite;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author junio
 */
public class SpecialiteDao implements IDao<Specialite> {

    DataBase database= new DataBase();
    private final String  SQL_FIND_ALL = "SELECT * FROM `specialite`";
    private final String SQL_SELECT_SPECIALITE_BY_ID =" SELECT * FROM specialite  WHERE id_specialite =? ";

   

    @Override
    public int update(Specialite ogj) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int delete(int id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Specialite> findAll() {
        List<Specialite> specialites =new ArrayList<Specialite>();
        database.openConnexion();
        try {
            
            database.initPrepareStatement(SQL_FIND_ALL);
            ResultSet rs =database.executeSelect(SQL_FIND_ALL);
            while(rs.next()){
                
                Specialite sp = new Specialite(
                        rs.getInt("id_specialite"),
                        rs.getString("libelle_specialite")
                );
                specialites.add(sp);
               
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(SpecialiteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
       // System.out.println(specialites);
         return specialites;
    }

    @Override
    public Specialite findById(int id) {
       Specialite p = null;
       if(id==0){
       
       
       }else{
       
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_SELECT_SPECIALITE_BY_ID);
            database.getPs().setInt(1, id);
            ResultSet rs = database.executeSelect(SQL_SELECT_SPECIALITE_BY_ID);
            if(rs.next())
            {
                
                p = new Specialite(
                    rs.getInt("id_specialite"),
                    rs.getString("libelle_specialite")
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

    @Override
    public int insert(Specialite ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
