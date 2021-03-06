/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.controller;


import Service.Service;
import entities.Docteur;
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
import javafx.scene.control.Button;
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
 * @author junio
 */
public class DocteurDashboardController implements Initializable {

    @FXML
    private VBox navBar;
    @FXML
    private Label txtNomComplet;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnCustomers;
    @FXML
    private Pane contentDocteur;
     private User user = ConnexionController.getCtrl().getUser();
     Service service  = new Service();
     private final Docteur doc = service.docteurById(user.getIdUser());
     private ViewService view = new ViewService();
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.print(user);
    }    

    @FXML
    private void handleDashboard(ActionEvent event) {
    }

    @FXML
    private void handleDemandeEnCours(ActionEvent event) {
        
        if(doc.getSpecialite().getLibelle().equals("Prestation")){
        
            try {
            view.loadView("v_prestation", contentDocteur);
            } catch (IOException ex) {
                Logger.getLogger(DocteurDashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
        
            try {
            view.loadView("v_rdvDocteur", contentDocteur);
        } catch (IOException ex) {
            Logger.getLogger(DocteurDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
     
        
        
        
    }

    @FXML
    private void HandleRendezVousEnValidez(ActionEvent event) {
    }

    @FXML
    private void HandleRendezVousAnnule(ActionEvent event) {
    }

    @FXML
    private void HandleDocteursPrestateurs(ActionEvent event) {
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
