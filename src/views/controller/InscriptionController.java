/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Service.Service;
import Validator.Validation;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entities.Patient;
import entities.Role;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author junio
 */
public class InscriptionController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXTextField txtPrenom;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtAntecedant;
    @FXML
    private JFXTextField txtTelephone;
    @FXML
    private Text txtError;
     private final Service service = new Service();
      private final Validation validation = new Validation();
    
      private static InscriptionController ctrl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       txtError.setVisible(false);
         ctrl = this;
    }    

    @FXML
    private void close_app(MouseEvent event) {
         System.exit(0);
    }

    @FXML
    private void back_to_menu(MouseEvent event) {
      try {
            this.txtError.getScene().getWindow().hide();
            AnchorPane root = null;
            root = FXMLLoader.load(getClass().getResource("/views/v_connexion.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
             stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void handleRegister(ActionEvent event) {
         String login = txtEmail.getText().trim();
        String password = txtPassword.getText().trim();
        String nom = txtNom.getText().trim();
        String prenom = txtPrenom.getText().trim();
        String antecedant = txtAntecedant.getText().trim();
        
       String telephone = txtTelephone.getText().trim();
        if(login.isEmpty() || password.isEmpty() || nom.isEmpty() || prenom.isEmpty() || telephone.isEmpty()|| antecedant.isEmpty() )
        {
          txtError.setText("tous les champs sont obligatoire");
          txtError.setVisible(true);
        }else if(!validation.isValidMail(login)){
                txtError.setText("Veuillez saisir un mail correct");
                txtError.setVisible(true);
            }
        else if(service.getUserByLogin(login)!= null){
            
            txtError.setText("Vous avez déja un compte ");
            txtError.setVisible(true);
        
        }
        else if(!validation.isValidTel(telephone)){
                txtError.setText("Veuillez saisir un numero telephone correct");
                txtError.setVisible(true);
            }
        else{
            String nomComplet = nom+" "+prenom;
            Role role = new Role(1,"ROLE_PATIENT");
            Patient patient = new Patient(nomComplet,login,password,role,antecedant);
            int id = service.addPatient(patient);
          
           if(id>0){
               
            try {
            this.txtError.getScene().getWindow().hide();
            AnchorPane root = null;
            root = FXMLLoader.load(getClass().getResource("/views/v_connexion.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
             stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
          }else{

            txtNom.clear();
            txtPrenom.clear();
            txtEmail.clear();
            txtPassword.clear();
            txtTelephone.clear();
            txtAntecedant.clear();
            txtError.setVisible(false);
           txtError.setText("Une erreur innatendu s'est produite");
           txtError.setVisible(true);
           
           }
        }
    }

    @FXML
    private void handleReset(ActionEvent event) {
        txtNom.clear();
        txtPrenom.clear();
        txtEmail.clear();
        txtPassword.clear();
        txtTelephone.clear();
        txtAntecedant.clear();
        txtError.setVisible(false);
    }
    
}
