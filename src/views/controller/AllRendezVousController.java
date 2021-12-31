/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.controller;

import Service.Service;
import dto.RendezVousDTO;
import entities.Consultation;
import entities.DetailPrestation;
import entities.Docteur;
import entities.Patient;
import entities.Prestation;
import entities.Specialite;
import entities.User;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author junio
 */
public class AllRendezVousController implements Initializable, IController {

    @FXML
    private TableView<RendezVousDTO> tblvRendezVous;
    @FXML
    private TableColumn<RendezVousDTO, String> tblcService;
    @FXML
    private TableColumn<RendezVousDTO, String> tblcType;
    @FXML
    private TableColumn<RendezVousDTO, String> tblDate;
    @FXML
    private TableColumn<RendezVousDTO, String> tblEtat;
    @FXML
    private TableColumn<RendezVousDTO, String> tblcID;
    @FXML
    private TextField txtfService;
    @FXML
    private TextField txtfIdentifiant;
    
    @FXML
    private TextField txtfType;
   
    @FXML
    private Button btnAnnuler;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnVider;
    @FXML
    private ComboBox<Docteur> cboRealisatorConsultation;
    @FXML
    private ComboBox<Docteur> cboRealisatorPrestation;
      Service service =new Service();
    ObservableList<RendezVousDTO> obvRdv;
    RendezVousDTO rdv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTableView();
        disableFields(true);
    }    

    @FXML
    private void handleSeachRendezVousById(MouseEvent event) {
        rdv = service.showRendezVousById(parseInt(txtfIdentifiant.getText()));
        if(rdv==null){
        clearFields();
        
        }else if(!"EN COURS".equals(rdv.getEtat())){
            clearFields();
    }else{
            
                txtfService.setText(rdv.getConsultOrPresta());
                txtfType.setText(rdv.getConsultOrPrestaType());

                    if("PRESTATION".equals(rdv.getConsultOrPresta())){

                        List<Docteur> responsables =service.searchDoctorBySpecialiter(rdv.getConsultOrPresta());
                        cboRealisatorConsultation.setVisible(false);
                        cboRealisatorPrestation.setVisible(true);
                        if(responsables!=null){
                            
                        responsables.forEach((doc) -> {
                        cboRealisatorPrestation.getItems().add(doc);
                        }); 
                        
                        cboRealisatorPrestation.setDisable(false);

                        
                        btnAnnuler.setDisable(false);
                        btnValider.setDisable(false);
                        btnVider.setDisable(false);
                        txtfIdentifiant.setDisable(true);

                    }

                    }
                    else if("CONSULTATION".equals(rdv.getConsultOrPresta())){
                        cboRealisatorPrestation.setVisible(false);
                        List<Docteur> docteurs = service.searchDoctorBySpecialiter(rdv.getConsultOrPrestaType());    
                        cboRealisatorConsultation.setVisible(true);
                        
                        if(docteurs!=null){docteurs.forEach((doc) -> {cboRealisatorConsultation.getItems().add(doc);});
                        cboRealisatorConsultation.setDisable(false);
                        btnAnnuler.setDisable(false);
                        btnValider.setDisable(false);
                        btnVider.setDisable(false);
                        txtfIdentifiant.setDisable(true);
                      }

                 } 
                    loadTableView();
             } 
    
    }

    @FXML
    private void handleCancelRdv(ActionEvent event) {
        int id = service.etatRendezVousSet(parseInt(txtfIdentifiant.getText()), "annuler");
        loadTableView();
    }

    @FXML
    @SuppressWarnings("empty-statement")
    private void handleValideRdv(ActionEvent event) {
        rdv = service.showRendezVousById(parseInt(txtfIdentifiant.getText()));
        if(rdv==null){
        clearFields();
        
        }else if(!"EN COURS".equals(rdv.getEtat())){
            clearFields();
        }else{
           
           Patient patient = rdv.getPatient();
           System.out.println(patient.getCode());
            
            
            if("PRESTATION".equals(rdv.getConsultOrPresta())){
                Prestation prestation = rdv.getPrestation();
                Docteur responsable = cboRealisatorPrestation.getSelectionModel().getSelectedItem(); 
                
                DetailPrestation dtPresta = new DetailPrestation(prestation,patient,responsable,"en cours");
                System.out.print(dtPresta.getPatient().getCode());
               
               if(service.addDetailPrestation(dtPresta) !=0){
                   
                service.etatRendezVousSet(parseInt(txtfIdentifiant.getText()), "VALIDER");
               }; 
              
            }else if("CONSULTATION".equals(rdv.getConsultOrPresta())){
            
                Specialite specialite = rdv.getSpecialite();
                Docteur docteur = cboRealisatorConsultation.getSelectionModel().getSelectedItem();
               // System.out.println(docteur.getIdUser());
               // System.out.println(docteur.getSpecialite());
               Consultation consultation = new Consultation(specialite,patient,docteur,rdv.getDateRendezVous(),rdv,"en cours");
               
               System.out.println(consultation.getDocteur().getIdUser());
               if(service.addConsultation(consultation) !=0){
                   
                service.etatRendezVousSet(parseInt(txtfIdentifiant.getText()), "VALIDER");
               };
                
            
                
            
            }
            
            
        
        
        
        
        }
       loadTableView();
        
    }

    @FXML
    private void handleReset(ActionEvent event) {
        clearFields();
        txtfIdentifiant.clear();
        disableFields(true);
         txtfIdentifiant.setDisable(false);
        
        
    }
    private void loadTableView(){
       
        List<RendezVousDTO> rdvList=service.showAllRendezVous("EN cours");
        obvRdv=FXCollections.observableArrayList(rdvList);
        //Construction des colonnes
        tblcService.setCellValueFactory(new PropertyValueFactory<>("consultOrPresta"));
        tblcType.setCellValueFactory(new PropertyValueFactory<>("consultOrPrestaType"));
        tblDate.setCellValueFactory(new PropertyValueFactory<>("dateRendezVous"));
        tblEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tblcID.setCellValueFactory(new PropertyValueFactory<>("idRendezVous"));
        
        
        tblvRendezVous.setItems(obvRdv);
    }
    @Override
    public void disableFields(boolean param) {
        txtfService.setDisable(param);
        cboRealisatorConsultation.setDisable(param);
        cboRealisatorPrestation.setDisable(param);
        txtfType.setDisable(param);
        btnAnnuler.setDisable(param);
        btnValider.setDisable(param);
        btnVider.setDisable(param);
        
    }

    @Override
    public void clearFields() {
        txtfService.clear();
        txtfType.clear();
        //cboRealisator.clear();
    }

    @FXML
    private void choixRendezVous(MouseEvent event) {
        RendezVousDTO rdvSet = tblvRendezVous.getSelectionModel().getSelectedItem();
        System.out.print(rdvSet);
        
    }
    
}
