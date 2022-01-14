/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author junio
 */
public class DataBase {
    @SuppressWarnings("FieldMayBeFinal")
    private String url="jdbc:mysql://localhost:3306/java_clinique_appointment";
    @SuppressWarnings("FieldMayBeFinal")
    private String user="root";
    @SuppressWarnings("FieldMayBeFinal")
    private String password="";
    Connection cnx=null;
    //Objet d'execution des requetes preparé
    private PreparedStatement ps=null;
    public void openConnexion(){
        try {
            //1-charger le Driver
            Class.forName("com.mysql.jdbc.Driver");
            //2-Ouverture de la connection
            
            try {
                cnx=DriverManager.getConnection(url,user,password);
                System.out.println("Connexion etablie");
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            }
    
    
    public void closeConnexion(){
        if(cnx!=null){
            try {
                cnx.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void initPrepareStatement(String sql){
        if(sql.toLowerCase().startsWith("insert")){
            try {
                ps=cnx.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                ps=cnx.prepareStatement(sql);
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public int executeUpdate(String sql){
        int nbreLigne=0;
    
        try {
            nbreLigne=ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    return nbreLigne;
    }
    
    public ResultSet executeSelect(String sql){
        ResultSet rs=null;
       
        try {
            rs=ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    return rs;
    }

    public PreparedStatement getPs() {
        return ps;
    }
}
