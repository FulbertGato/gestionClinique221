/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.controller;

import MAIN.Main;

import Service.Service;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author junio
 */
public class ConnexionController implements Initializable {
    private double xOffset=0;
   private double yOffset=0;
   private User user;
    @FXML
    private Pane content_area;
    @FXML
    private JFXTextField ttxtEmail;
    @FXML
    private JFXPasswordField txtpPassword;
    @FXML
    private Text txtError;
    private final Service service = new Service();
    
    private static ConnexionController ctrl;
    

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
    private void handleConnexion(ActionEvent event) {
        String login = ttxtEmail.getText().trim();
        String password = txtpPassword.getText().trim();
        if(login.isEmpty() || password.isEmpty())
        {
          txtError.setText("login ou le mot de passe Obligatoire");
          txtError.setVisible(true);
        }
        else{
            
            
           user = service.login(login, password);
          
          if(user == null)
          {
               txtError.setText("login ou le mot de passe Incorrect");
               txtError.setVisible(true);
          }
          else
          {
              
              
           switch(user.getRole().getLibelle()){
            case "ROLE_PATIENT":
             try {
                    this.txtError.getScene().getWindow().hide();
                    AnchorPane root = null;
                    root = FXMLLoader.load(getClass().getResource("/views/dashboard.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
                }
              break;
            case "ROLE_DOCTEUR":
              try {
                    this.txtError.getScene().getWindow().hide();
                    AnchorPane root = null;
                    root = FXMLLoader.load(getClass().getResource("/views/v_docteurDashboard.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
                }
              break;
            case "ROLE_RESPONSABLE":
              // code block
              break;
            case "ROLE_SECRETAIRE":
              try {
                  System.out.println(user.getRole());
                    this.txtError.getScene().getWindow().hide();
                    AnchorPane root = null;
                   root = FXMLLoader.load(getClass().getResource("/views/v_dashboardSecretaire.fxml"));
                 // root = FXMLLoader.load(getClass().getResource("/views/dashboard.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
                }
              break;
            
            default:
              // code block
            }
              
            
         
          }
        }
    }

    @FXML
    private void handleInscription(MouseEvent event) {
         try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/views/v_inscription.fxml"));
            content_area.getChildren().removeAll();
            content_area.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static ConnexionController getCtrl() {
        return ctrl;
    }

    public User getUser() {
        return user;
    }
}
