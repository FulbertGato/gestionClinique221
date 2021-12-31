/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dto.ConsultationDTo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import views.controller.RdvDocteurController;

/**
 * FXML Controller class
 *
 * @author junio
 */
public class V_detailconsultationController implements Initializable {

    @FXML
    private Pane content;
    @FXML
    private JFXButton nomPatient;
    @FXML
    private JFXButton antecedant;
    @FXML
    private JFXButton etatConsult;
    @FXML
    private JFXTextArea constante;
    @FXML
    private TableView<?> tblvMedicament;
    @FXML
    private TableColumn<?, ?> nomMeoc;
    @FXML
    private TableColumn<?, ?> posLogie;
    @FXML
    private JFXButton addMedicamentBtn;
    @FXML
    private JFXComboBox<?> cboxChoix;
    @FXML
    private JFXComboBox<?> cboxTypeConsultation;
    @FXML
    private JFXComboBox<?> cboxTypePrestaion;
    @FXML
    private JFXButton rdvPlanierBtn;
    @FXML
    private AnchorPane popuMedicament;
    @FXML
    private JFXTextField libelleMedicament;
    @FXML
    private JFXTextArea posologieMedicament;
     private ConsultationDTo consultation = RdvDocteurController.getCtrl().getConsultationSelect();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomPatient.setText(consultation.getPatient().getNomComplet());
        antecedant.setText(consultation.getPatient().getAntecedants());
        etatConsult.setText(consultation.getStatus());
        popuMedicament.setVisible(false);
    }    

    @FXML
    private void enregistrerOrdonnance(ActionEvent event) {
    }

    @FXML
    private void addMedicament(ActionEvent event) {
        popuMedicament.setVisible(true);
    }

    @FXML
    private void handleChangeChoix(ActionEvent event) {
    }

    @FXML
    private void handleChangeTypeConsultation(ActionEvent event) {
    }

    @FXML
    private void handleChangeTypePrestation(ActionEvent event) {
    }

    @FXML
    private void voirRdvPlanier(ActionEvent event) {
    }

    @FXML
    private void addMedeBtn(ActionEvent event) {
    }

    @FXML
    private void restMedBtn(ActionEvent event) {
    }
    
}
