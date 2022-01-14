/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ConsultationDTo;
import dto.MedicamentDTO;
import entities.Medicament;
import entities.Ordonnance;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author junio
 */
public class OrdonnanceDao implements IDao<Ordonnance> {
    
    private final String SQL_INSERT="INSERT INTO `ordonnance` (`consultation_id`) VALUES ( ?)";
    private final String SQL_INSERT_MEDICAMENT_POSOLOGIE="INSERT INTO `ordonnance_medicament` (`ordonnance_id`,`medicament_id`,`posologie`) VALUES (?,?,?)";
    private final String SQL_SELECT_BY_ID ="SELECT * FROM `ordonnance` o ,`ordonnance_medicament` om    WHERE o.id_ordonnance=? AND om.ordonnance_id = ?  ";
    private final DataBase database= new DataBase();
    private final ConsultationDao daoConsultation = new ConsultationDao();
    private final MedicamentDao daoMedicament = new MedicamentDao();

    @Override
    public int insert(Ordonnance ordonnance) {
        int lastInsertIdOrd=0;
        try {
           
            database.openConnexion();
            database.initPrepareStatement(SQL_INSERT);
            database.getPs().setInt(1,ordonnance.getConsultation().getIdConsultation() );
            database.executeUpdate(SQL_INSERT);
            ResultSet rs=database.getPs().getGeneratedKeys();
            if(rs.next()){
            lastInsertIdOrd=rs.getInt(1);
            int i = 1;
            for (MedicamentDTO m : ordonnance.getMedicamentsDto()) {
                
            database.openConnexion();
            database.initPrepareStatement(SQL_INSERT_MEDICAMENT_POSOLOGIE); 
            database.getPs().setInt(1,lastInsertIdOrd );
            database.getPs().setInt(2,m.getMedicament().getIdMedicament() );
            database.getPs().setString(3,m.getPosologie() );          
            database.executeUpdate(SQL_INSERT_MEDICAMENT_POSOLOGIE);
            System.out.println("success insert "+i);
            i=i+1;
            }
            
            
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdonnanceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lastInsertIdOrd;
    }

    @Override
    public int update(Ordonnance ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ordonnance> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ordonnance findById(int id) {
        Ordonnance m = null;
        try {
           
            
            database.openConnexion();
            database.initPrepareStatement(SQL_SELECT_BY_ID);
            database.getPs().setInt(1, id);
            ResultSet rs = database.executeSelect(SQL_SELECT_BY_ID);
            
            
            if(rs.next())
            {
               ConsultationDTo  consultation = new ConsultationDTo();
                consultation.toDto(daoConsultation.findById( rs.getInt("consultation_id")));
                m = new Ordonnance(rs.getInt("id_ordonnance"),consultation);
                HashMap<Medicament, String> medicaments = null;
               while(rs.next()){
                
                   medicaments.put(daoMedicament.findById(rs.getInt("medicament_id")), rs.getString("id_ordonnance"));
                   
                }
               m.setMedicaments(medicaments);
   
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MedicamentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    
}}
