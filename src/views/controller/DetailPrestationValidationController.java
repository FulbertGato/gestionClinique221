/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.controller;

import Service.Service;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import dto.DetailPrestationDto;
import entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import utils.FileGenerator;

/**
 * FXML Controller class
 *
 * @author gatoj
 */
public class DetailPrestationValidationController implements Initializable {

    @FXML
    private Pane content;
    @FXML
    private JFXButton nomPatient;
    @FXML
    private JFXButton antecedant;
    @FXML
    private JFXButton etat;
    @FXML
    private JFXButton enregistrerBtn;
    
    @FXML
    private JFXButton resultatsBtn;
    @FXML
    private JFXTextArea resultatText;

    
     private final User user= ConnexionController.getCtrl().getUser();
     private final DetailPrestationDto prestationDto = PrestationController.getCtrl().getPrestationSelect();
     Service service = new Service();
    @FXML
    private JFXButton terminerBtn;
    @FXML
    private JFXButton annulerPrestation;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nomPatient.setText(prestationDto.getPatient().getNomComplet());
        antecedant.setText(prestationDto.getPatient().getAntecedants());
        etat.setText(prestationDto.getStatus());
        if(prestationDto.getResultats()==null){
        resultatsBtn.setDisable(true);
        
        }else{
         resultatText.setText(prestationDto.getResultats()); 
         enregistrerBtn.setDisable(true);
         terminerBtn.setDisable(true);
         annulerPrestation.setDisable(true);
        
        }
      
    }    

    @FXML
    private void enregistrer(ActionEvent event) {
        if(!resultatText.getText().equals("")){
            prestationDto.setResultats(resultatText.getText());
            service.addDetailPrestationResultat(prestationDto);
            resultatText.setText(prestationDto.getResultats()); 
            enregistrerBtn.setDisable(true);
            terminerBtn.setDisable(true);
            annulerPrestation.setDisable(true);
        }else{
        }
    }

    @FXML
    private void TerminatePrestation(ActionEvent event) {
        prestationDto.setStatus("ENREGISTRER");
        terminerBtn.setDisable(true);
        annulerPrestation.setDisable(false);
    }

    @FXML
    private void ImportResultat(ActionEvent event) {
        if(!resultatText.getText().equals("")){
            
            FileGenerator.generator(prestationDto.getPatient().getCode(),prestationDto.getDate(),resultatText.getText());
        }
        
    }

    @FXML
    private void annulerPrestationAction(ActionEvent event) {
        prestationDto.setStatus("ANNULER");
        terminerBtn.setDisable(false);
        annulerPrestation.setDisable(true);
    }
    
}
