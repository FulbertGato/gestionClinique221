/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
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
    private JFXButton antecedant1;
    @FXML
    private JFXButton resultatsBtn;
    @FXML
    private JFXTextArea resultatText;

    
    FileGenerator gen = new FileGenerator();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        FileGenerator.generator("codepatient","date","resultat test maman");
    }    

    @FXML
    private void enregistrer(ActionEvent event) {
    }

    @FXML
    private void TerminatePrestation(ActionEvent event) {
    }

    @FXML
    private void ImportResultat(ActionEvent event) {
    }
    
}
