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

import entities.Role;

/**
 *
 * @author junio
 */
public class RoleDao implements IDao<Role> {
    DataBase database= new DataBase();
    private final String  SQL_FIND_ALL = "SELECT * FROM role";
    private final String SQL_SELECT_BY_ID =" SELECT * FROM role  WHERE id_role =? ";

    @Override
    public int insert(Role ogj) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int update(Role ogj) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int delete(int id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles=new ArrayList<Role>();
        
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_FIND_ALL);
            ResultSet rs =database.executeSelect(SQL_FIND_ALL);
            while(rs.next()){
                try {
                Role role = new Role(
                rs.getInt("id_role"),
                rs.getString("libelle")
                );
                roles.add(role);
                } catch (SQLException ex) {
                    Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return roles;
    }

    @Override
    public Role findById(int id) {
         Role p = null;
       if(id==0){
       
       
       }else{
       
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_SELECT_BY_ID);
            database.getPs().setInt(1, id);
            ResultSet rs = database.executeSelect(SQL_SELECT_BY_ID);
            if(rs.next())
            {
                
                p = new Role(
                    rs.getInt("id_role"),
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
