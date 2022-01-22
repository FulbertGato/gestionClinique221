/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.controller;

import Service.Service;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entities.Role;
import entities.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import utils.MailSender;

/**
 * FXML Controller class
 *
 * @author gatoj
 */
public class AddUtilisateurController implements Initializable {

    @FXML
    private Pane paneAddUser;
    @FXML
    private JFXComboBox<Role> cboxChoiRole;
    @FXML
    private Text txtError;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField email;
    ArrayList<Role> roles = new ArrayList<Role>();
    Service service = new Service();
    @FXML
    private JFXButton ajoutBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtError.setVisible(false);
        roles= (ArrayList<Role>) service.showAllRole();
        for(Role r: roles){
            cboxChoiRole.getItems().add(r);
        };
        
    }    

    @FXML
    private void handleAjouter(ActionEvent event) {
        String name = nom.getText();
        String firstname = nom.getText();
        String login = email.getText();
        if(login.isEmpty() || name.isEmpty() || firstname.isEmpty())
        {
          
          txtError.setVisible(true);
        }else{
        txtError.setVisible(false);
        ajoutBtn.setDisable(true);
        User user = new User();
        user.setLogin(login);
        user.setNomComplet(name+" "+firstname);
        user.setRole(cboxChoiRole.getSelectionModel().getSelectedItem());
        user.setPassword("defaultPassword");
        
        int id = service.addUser(user);
        if(id>0){
        
            try {
 
             MailSender.sendMail(user.getLogin(),"Votre compte est cree sur Clinique 221","Bonjour "+user.getNomComplet()+" Votre email: "+user.getLogin()+" <br> votre password : "+user.getPassword()); 
            } catch (Exception ex) {
                 Logger.getLogger(AddUtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
             }
            nom.clear();
            email.clear();
            prenom.clear();
            ajoutBtn.setDisable(false);
        }
        }
    }
    
}
