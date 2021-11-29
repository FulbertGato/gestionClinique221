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
import java.util.logging.Level;
import java.util.logging.Logger;

import entities.Docteur;
import entities.Role;
import entities.Specialite;

/**
 *
 * @author junio
 */
public class DocteurDao implements IDao<Docteur>{
    private final DataBase database= new DataBase();
    
    private final String  SQL_FIND_ALL = "SELECT * FROM user WHERE specialite_id > 0";
    private final String SQL_INSERT="INSERT INTO `user` (`nomComplet`, `login`, `password` , `role_id`,`specialite_id`) VALUES ( ?, ?, ?,?,?)";
    private final String SQL_FIND_BY_SP ="SELECT * FROM user u , specialite sp WHERE u.specialite_id = sp.id_specialite AND sp.libelle_specialite LIKE ?";
    @Override
    public int insert(Docteur docteur) {
        int lastInsertId=0;
        database.openConnexion();
        database.initPrepareStatement(SQL_INSERT);
        try {
            database.getPs().setString(1, docteur.getNomComplet());
            database.getPs().setString(2, docteur.getLogin());
            database.getPs().setString(3, docteur.getPassword());
            database.getPs().setInt(4, docteur.getRoleId());
            database.getPs().setInt(5, docteur.getSpecialiteId());
            database.executeUpdate(SQL_INSERT);
            ResultSet rs=database.getPs().getGeneratedKeys();
            if(rs.next()){
            lastInsertId=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DocteurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        database.closeConnexion();
        return lastInsertId;
        
    }

    @Override
    public int update(Docteur ogj) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int delete(int id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Docteur> findAll() {
        List<Docteur> docteurs=new ArrayList<Docteur>();
        
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_FIND_ALL);
            ResultSet rs =database.executeSelect(SQL_FIND_ALL);
            while(rs.next()){
                try {
                    
                Role role = new Role(rs.getInt("role_id"));
                Specialite specialite = new Specialite(rs.getInt("specialite_id"),rs.getString("libelle_specialite"));
                Docteur docteur = new Docteur(
                rs.getInt("id_user"),
                rs.getString("nomComplet"),
                rs.getString("login"),
                rs.getString("password"), 
                role,
                specialite);
                docteurs.add(docteur);
                } catch (SQLException ex) {
                    Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return docteurs;
    }

    @Override
    public Docteur findById(int id) {
        // TODO Auto-generated method stub
        return null;
    }
    
    public List<Docteur> findBySpecialite(String consultOrPrestaType){
         List<Docteur> docteurs=new ArrayList<Docteur>();
        
    
    try {
            database.openConnexion();
            database.initPrepareStatement(SQL_FIND_BY_SP);
            database.getPs().setString(1, consultOrPrestaType );
            ResultSet rs =database.executeSelect(SQL_FIND_BY_SP);
            while(rs.next()){
                try {
                    
                Role role = new Role(rs.getInt("role_id"));
                
                Specialite specialite = new Specialite(rs.getInt("specialite_id"),rs.getString("libelle_specialite"));
                Docteur docteur = new Docteur(
                rs.getInt("id_user"),
                rs.getString("nomComplet"),
                rs.getString("login"),
                rs.getString("password"), 
                role,
                specialite);
                docteurs.add(docteur);
                } catch (SQLException ex) {
                    Logger.getLogger(DocteurDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        System.out.print(docteurs);
        return docteurs;
    
    }
    
}
