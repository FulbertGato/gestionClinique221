/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.controller;

import Service.Service;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTimePicker;
import dto.RendezVousDTO;
import entities.Patient;
import entities.Prestation;
import entities.RendezVous;
import entities.Specialite;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import utils.ViewService;

/**
 * FXML Controller class
 *
 * @author junio
 */
public class DemandeController implements Initializable {

    @FXML
    private JFXDatePicker dateChoix;
    @FXML
    private JFXTimePicker heureChoix;
    @FXML
    private JFXComboBox<String> cboxChoix;
    @FXML
    private JFXTextArea txtFieldMessage;
    
    @FXML
    private JFXComboBox<Specialite> cboxTypeConsultation;
    @FXML
    private JFXComboBox<Prestation> cboxTypePrestaion;

     private String choix;
     private List<Prestation> prestations;
     private List<Specialite> consultations;
     private Specialite consulChoix=null;
     private  Prestation prestaChoix=null;
     private final User user= ConnexionController.getCtrl().getUser();
    
     private final Service service = new Service();
    @FXML
    private Text txtError;
    @FXML
    private Pane paneDemande;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtError.setVisible(false);
        ViewService.datePastDesactive(dateChoix);
        
        ViewService.loadComboBoxService(cboxChoix);
        
      
    }    

    @FXML
    private void handleChangeChoix(ActionEvent event) {
        choix = cboxChoix.getSelectionModel().getSelectedItem();
        if("Consultation".equals(choix)){
            if(cboxTypePrestaion != null ){
                
                cboxTypePrestaion.setVisible(false);
            }
            
            cboxTypeConsultation.setVisible(true);
            cboxTypeConsultation.getItems().clear();
            
            consultations=service.showAllSpecialite();
            if(consultations!=null){
            
                consultations.forEach((sp) -> {
                    cboxTypeConsultation.getItems().add(sp);
                });
            }
            

        
        }else if ("Prestation".equals(choix)){
              if(cboxTypeConsultation != null ){
                
                cboxTypeConsultation.setVisible(false);
            }
            cboxTypePrestaion.setVisible(true);
            prestations=service.showAllPrestation();
            cboxTypePrestaion.getItems().clear();
            if(prestations!=null){prestations.forEach((p) -> {
                cboxTypePrestaion.getItems().add(p);
                });
}
           
        }
    }


    @FXML
    private void handleDEmande(ActionEvent event) {
        
        LocalDate date = dateChoix.getValue();
        LocalTime heure = heureChoix.getValue();
        String message=txtFieldMessage.getText();
        Prestation  presta =  prestaChoix;  
        Specialite sper = consulChoix;
        
        if(date ==null ||heure ==null ||message.isEmpty() ){
        txtError.setVisible(true);
        }else{
            
            Patient patient = service.searchPatientById(user.getIdUser());
            
            if(presta ==null ){
                txtError.setVisible(false);
                LocalDate createDateTime = LocalDate.now();
                
                RendezVousDTO rdv = new RendezVousDTO();
                rdv.setCreateDateTime(createDateTime);
                rdv.setDateRendezVous(date);
                rdv.setEtat("EN COURS");
                rdv.setPatient(patient);
                rdv.setSpecialite(sper);
                service.addRendezVous(rdv);
                tankView();
            }
            else if(sper ==null ){
                txtError.setVisible(false);
                LocalDate createDateTime = LocalDate.now();
                RendezVousDTO rdv = new RendezVousDTO();
                rdv.setCreateDateTime(createDateTime);
                rdv.setDateRendezVous(date);
                rdv.setEtat("EN COURS");
                rdv.setPatient(patient);
                rdv.setPrestation(presta);
                tankView();
            
            }
            else{
                txtError.setText("ERROR A CORRIGER ");
                txtError.setVisible(true);
            }
            
            
        
        
        
        }
      
    
    }

    @FXML
    private void handleChangeTypeConsultation(ActionEvent event) {
        // cboxTypePrestaion=null;
       ///  cboxChoix.disabledProperty();
         consulChoix = cboxTypeConsultation.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void handleChangeTypePrestation(ActionEvent event) {
       // cboxTypeConsultation=null;
        //cboxChoix
        prestaChoix = cboxTypePrestaion.getSelectionModel().getSelectedItem();
    }
    
    
    private void tankView(){
     try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/views/v_tank_you.fxml"));
            paneDemande.getChildren().removeAll();
            paneDemande.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
    

