/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.controller;

import Service.Service;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dto.ConsultationDTo;
import dto.MedicamentDTO;
import entities.Medicament;
import entities.Ordonnance;
import entities.Prestation;
import entities.Specialite;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author junio
 */
public class DetailconsultationController implements Initializable {

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
    private TableView<MedicamentDTO> tblvMedicament;
    @FXML
    private TableColumn<MedicamentDTO, String> nomMeoc;
    @FXML
    private TableColumn<MedicamentDTO, String> posLogie;
    @FXML
    private JFXButton addMedicamentBtn;
    @FXML
    private JFXComboBox<String> cboxChoix;
    @FXML
    private JFXComboBox<Specialite> cboxTypeConsultation;
    @FXML
    private JFXComboBox<Prestation> cboxTypePrestaion;
    @FXML
    private JFXButton rdvPlanierBtn;
    @FXML
    private AnchorPane popuMedicament;
    @FXML
    private JFXTextField libelleMedicament;
    @FXML
    private JFXTextArea posologieMedicament;
     private final ConsultationDTo consultation = RdvDocteurController.getCtrl().getConsultationSelect();
     
     private final Service service = new Service();
     Ordonnance ordonnance = new Ordonnance();
     private HashMap<Medicament, String> medicaments = new HashMap<Medicament, String>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomPatient.setText(consultation.getPatient().getNomComplet());
        antecedant.setText(consultation.getPatient().getAntecedants());
        etatConsult.setText(consultation.getStatus());
        popuMedicament.setVisible(false);
        if(consultation.getOrdonnance()==null){        
            ordonnance=new Ordonnance(consultation);           
        }else{
        
            ordonnance=service.ordonnanceById(consultation.getOrdonnance().getIdOrdonnance());
            medicaments=ordonnance.getMedicaments();
            loadTableView();
        }
    }    

    @FXML
    private void enregistrerOrdonnance(ActionEvent event) {
        
        int id =service.addOrdonnance(ordonnance);
        if(id!=0){
            
            consultation.setOrdonnance(service.findOrdonnanceById(id));
           int re= service.addOrdonnanceToConsultation(consultation);
        
        }
    }

    @FXML
    private void addMedicament(ActionEvent event) {
        popuMedicament.setVisible(true);
    }

    @FXML
    private void handleChangeChoix(ActionEvent event){
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
        String libelle = libelleMedicament.getText().trim();
        String posologie = posologieMedicament.getText().trim();
        if(libelle.isEmpty() || posologie.isEmpty())
        {
          
            System.out.println("posologie ou libelle Obligatoire");
         
        }else{
            
            Medicament medicament = new Medicament(libelle);
            medicament = service.addMedicament(medicament);
            medicaments.put(medicament, posologie);
            ordonnance.setMedicaments(medicaments);
            loadTableView();
        }
    }

    @FXML
    private void restMedBtn(ActionEvent event) {
        popuMedicament.setVisible(false);
    }
    
    private void loadTableView(){
         ordonnance.setMedicamentsDto();
         List<MedicamentDTO> mediamentsDTO=ordonnance.getMedicamentsDto();
        ObservableList<MedicamentDTO> obvMedicaments = FXCollections.observableArrayList(mediamentsDTO);
        //Construction des colonnes
         nomMeoc.setCellValueFactory(new PropertyValueFactory<>("medicament"));
         posLogie.setCellValueFactory(new PropertyValueFactory<>("posologie"));
        tblvMedicament.setItems(obvMedicaments);
    }
    
}
