/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.controller;

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
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.ViewService;

/**
 * FXML Controller class
 *
 * @author gatoj
 */
public class AdministratorController implements Initializable {

    @FXML
    private VBox navBar;
    @FXML
    private Label txtNomComplet;
    @FXML
    private Pane contentAdministrateur;

     private User user = ConnexionController.getCtrl().getUser();
     private ViewService view = new ViewService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtNomComplet.setText(user.getNomComplet());
    }    

    @FXML
    private void handleDashboard(ActionEvent event) {
    }

    @FXML
    private void AjouterUtilisateur(ActionEvent event) {
        try {
            view.loadView("addUtilisateur", contentAdministrateur);
        } catch (IOException ex) {
            Logger.getLogger(AdministratorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    private void ListerUtilisateurs(ActionEvent event) {
        try {
            view.loadView("v_AllRendezVous", contentAdministrateur);
        } catch (IOException ex) {
            Logger.getLogger(AdministratorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleSignOut(ActionEvent event) {
        try {
            this.txtNomComplet.getScene().getWindow().hide();
            AnchorPane root = null;
            root = FXMLLoader.load(getClass().getResource("/views/v_connexion.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
