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
import dto.RendezVousDTO;
import entities.Medicament;
import entities.Ordonnance;
import entities.Patient;
import entities.Prestation;
import entities.Specialite;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import utils.ViewService;

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
    @FXML
    private JFXButton enregistrerBtn;
    @FXML
    private JFXButton planierdvBtn;
    @FXML
    private JFXButton planifierRdvBtn;
    @FXML
    private AnchorPane popupPlanifierRdv;
    
    
    private String choix;
    private List<Specialite> consultations;
    private List<Prestation> prestations;
    private Specialite consulChoix=null;
     private  Prestation prestaChoix=null;
     private int idRdv =0;
    @FXML
    private AnchorPane rdvPlanifierPopup;
    @FXML
    private JFXButton typeRdv;
    @FXML
    private JFXButton etatRDV;
    @FXML
    private JFXButton typeRdv1;
    @FXML
    private JFXButton exitRdv;
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-LLLL-yyyy");
   // LocalDateTime dateN = LocalDateTime.now();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomPatient.setText(consultation.getPatient().getNomComplet());
        antecedant.setText(consultation.getPatient().getAntecedants());
        etatConsult.setText(consultation.getStatus());
        popuMedicament.setVisible(false);
        popupPlanifierRdv.setVisible(false);
        popupPlanifierRdv.setVisible(false);
        ViewService.loadComboBoxService(cboxChoix);
        if(!"TERMINER".equals(consultation.getStatus())){        
            ordonnance=new Ordonnance(consultation); 
            System.out.println(" Ordonnance pas trouver ");
        }else{
            enregistrerBtn.setDisable(true);
            planierdvBtn.setDisable(true);
            addMedicamentBtn.setDisable(true);
            System.out.println(" Ordonnance  trouver ");
            constante.setText(consultation.getConstante());
            ordonnance=service.findOrdonnanceById(consultation.getIdOrdonnance());
            medicaments=ordonnance.getMedicaments();
            
            loadTableView();
        }
    }    

    @FXML
    private void enregistrerOrdonnance(ActionEvent event) {
        enregistrerBtn.setDisable(true);
        int id =service.addOrdonnance(ordonnance);
        if(id!=0){
             System.out.println(id);
             consultation.setConstante(constante.getText());
             consultation.setOrdonnance(service.findOrdonnanceById(id));
          if(idRdv!=0){
              
              consultation.setRdv(service.showRendezVousById(idRdv));
          
          }
          consultation.setStatus("TERMINER");
          service.addOrdonnanceToConsultation(consultation);
        
        }
    }

    @FXML
    private void addMedicament(ActionEvent event) {
        popuMedicament.setVisible(true);
    }

    @FXML
    private void handleChangeChoix(ActionEvent event){
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
    private void handleChangeTypeConsultation(ActionEvent event) {
         cboxTypePrestaion.getSelectionModel().clearSelection();
         consulChoix = cboxTypeConsultation.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void handleChangeTypePrestation(ActionEvent event) {
         cboxTypeConsultation.getSelectionModel().clearSelection();
        prestaChoix = cboxTypePrestaion.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void voirRdvPlanier(ActionEvent event) {
        if(null != consultation.getPrestation()){
        
            RendezVousDTO rdvPlanifier = service.showRendezVousById(consultation.getPrestation().getIdRendezVous());
            System.out.println(rdvPlanifier.getEtat()); 
            popuMedicament.setVisible(false);
            popupPlanifierRdv.setVisible(false);
            typeRdv.setText(rdvPlanifier.getConsultOrPrestaType());
            etatRDV.setText(rdvPlanifier.getEtat());
            typeRdv1.setText("DATE : "+rdvPlanifier.getDateRendezVous().format(formatter));
            popupPlanifierRdv.setVisible(true);
        } else {
            
        }
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

    @FXML
    private void planierdvAction(ActionEvent event) {
        popupPlanifierRdv.setVisible(true);
        planierdvBtn.setDisable(true);
        addMedicamentBtn.setDisable(true);
    }

    @FXML
    private void CancelrdvBtnAction(ActionEvent event) {
        popupPlanifierRdv.setVisible(false);
        planifierRdvBtn.setDisable(false);
        addMedicamentBtn.setDisable(false);
    }

    @FXML
    private void planifierRdvBtnAction(ActionEvent event) {
        
        Prestation  presta =  prestaChoix;  
        Specialite sper = consulChoix;
        Patient patient = consultation.getPatient();
            
            if(presta ==null ){
              //  txtError.setVisible(false);
              //a corriger apres esposé : ne pas enregistrement un rdv sans validation
                LocalDate createDateTime = LocalDate.now();
                
                RendezVousDTO rdv = new RendezVousDTO();
                rdv.setCreateDateTime(createDateTime);
                rdv.setDateRendezVous(createDateTime);
                rdv.setEtat("EN COURS");
                rdv.setPatient(patient);
                rdv.setSpecialite(sper);
                idRdv = service.addRendezVous(rdv);
                //tankView();
                popupPlanifierRdv.setVisible(false);
            }
            
            else if(sper ==null ){
                
                LocalDate createDateTime = LocalDate.now();
                RendezVousDTO rdv = new RendezVousDTO();
                rdv.setCreateDateTime(createDateTime);
                rdv.setDateRendezVous(createDateTime);
                rdv.setEtat("EN COURS");
                rdv.setPatient(patient);
                rdv.setPrestation(presta);
                idRdv = service.addRendezVous(rdv);
               // tankView();
               popupPlanifierRdv.setVisible(false);
            
            }
            addMedicamentBtn.setDisable(false);
            
            
    }

    @FXML
    private void exitRdvAction(ActionEvent event) {
        popupPlanifierRdv.setVisible(false);
    }
    
}
