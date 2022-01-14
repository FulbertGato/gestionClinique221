/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Medicament;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author junio
 */
public class MedicamentDao implements IDao<Medicament> {
    private final DataBase database= new DataBase();
    private final String  SQL_FIND_LIBELLE = "SELECT * FROM medicament WHERE libelle LIKE  ?";
    private final String SQL_INSERT="INSERT INTO `medicament` (`libelle`) VALUES ( ?)";
    private final String SQL_SELECT_MEDICAMENT_BY_ID ="SELECT * FROM `medicament`  WHERE id_medicament  =  ? ";

    @Override
    public int insert(Medicament medicament) {
         int lastInsertId=0;
        try {
           
            database.openConnexion();
            database.initPrepareStatement(SQL_INSERT);
            database.getPs().setString(1, medicament.getLibelle());
            database.executeUpdate(SQL_INSERT);
            ResultSet rs=database.getPs().getGeneratedKeys();
            if(rs.next()){
            lastInsertId=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicamentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastInsertId;
    }

    @Override
    public int update(Medicament ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Medicament> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Medicament findById(int id) {
         Medicament m = null;
        try {
           
            
            database.openConnexion();
            database.initPrepareStatement(SQL_SELECT_MEDICAMENT_BY_ID);
            database.getPs().setInt(1, id);
            ResultSet rs = database.executeSelect(SQL_SELECT_MEDICAMENT_BY_ID);
            if(rs.next())
            {
               
                m = new Medicament(
                        rs.getInt("id_medicament"),
                        rs.getString("libelle")
                );
                 
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MedicamentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }
       
    
    
    public Medicament findByLibelle(String libelle) {
        Medicament medicament = null;
        try {
            
            database.openConnexion();
            database.initPrepareStatement(SQL_FIND_LIBELLE);
            
            database.getPs().setString(1, libelle);
            ResultSet rs = database.executeSelect(SQL_FIND_LIBELLE);
            if(rs.next())
            {
                    
                    medicament = new Medicament(
                    rs.getInt("id_medicament"),
                    rs.getString("libelle")
                    );
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(MedicamentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return medicament;
    }
    
}
