/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.controller;

import Service.Service;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dto.ConsultationDTo;
import entities.Consultation;
import entities.Docteur;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import utils.ViewService;

/**
 * FXML Controller class
 *
 * @author junio
 */
public class RdvDocteurController implements Initializable {
    
    @FXML
    private TableView<ConsultationDTo> tblvConsultation;
    @FXML
    private TableColumn<ConsultationDTo, String> tblcType;
    @FXML
    private TableColumn<ConsultationDTo, String> tblNmPatient;
    @FXML
    private TableColumn<ConsultationDTo, String> tblcAnte;
    @FXML
    private TableColumn<ConsultationDTo, String> tblcTel;
    @FXML
    private TableColumn<ConsultationDTo, String> tblcAge;
    @FXML
    private JFXButton nbreRdv;
    @FXML
    private JFXButton nameSrv;
    @FXML
    private JFXButton date;
    @FXML
    private JFXButton etat;
    Service service  = new Service();
    private final User user= ConnexionController.getCtrl().getUser();
    private final Docteur doc = service.docteurById(user.getIdUser());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-LLLL-yyyy");
    LocalDateTime dateN = LocalDateTime.now();
    String d =dateN.format(formatter);
   private ConsultationDTo consultationSelect  ;
   private static RdvDocteurController ctrl;
    ObservableList<ConsultationDTo> obvCons;
    @FXML
    private DatePicker dateF;
    @FXML
    private JFXComboBox<String> statusF;
    @FXML
    private JFXButton filtreId;
    @FXML
    private JFXButton annulerId;
     private ViewService view = new ViewService();
    @FXML
    private Pane contentDocteur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        nbreRdv.setText("Nbre rdv: "+service.nombrerdvDoc(user.getIdUser(),d));
        nameSrv.setText("Service: "+doc.getSpecialite());
        date.setText(d);
        loadTableView("en cours",d);
        ctrl = this;
        statusF.getItems().add("EN COURS");
        statusF.getItems().add("TERMINER");
        statusF.getItems().add("ANNULER");
        filtreId.setDisable(true);
        annulerId.setDisable(true);
    }    

    @FXML
    private void selectConsulatation(MouseEvent event) {
        
        try {
            consultationSelect = tblvConsultation.getSelectionModel().getSelectedItem();
            System.out.println(consultationSelect.getIdConsultation());
            
            view.loadView("v_detailconsultation", contentDocteur);
        } catch (IOException ex) {
            Logger.getLogger(RdvDocteurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    private void loadTableView(String etat, String date){
        List<Consultation> consList=service.showAllConsultation(etat,date,doc.getIdUser());
        List<ConsultationDTo> dtoList=new ArrayList<ConsultationDTo>();
        for (Consultation e : consList) {
            ConsultationDTo c = new ConsultationDTo();
            c.toDto(e);    
            dtoList.add(c);
        }
         obvCons=FXCollections.observableArrayList(dtoList);
         tblNmPatient.setCellValueFactory(new PropertyValueFactory<>("nomPatient"));
         tblcType.setCellValueFactory(new PropertyValueFactory<>("specialite"));
         tblcAnte.setCellValueFactory(new PropertyValueFactory<>("antPatient"));
         tblcTel.setCellValueFactory(new PropertyValueFactory<>("telPtaient"));
         tblcAge.setCellValueFactory(new PropertyValueFactory<>("status"));
   
          tblvConsultation.setItems(obvCons);
         
    }
    
    public ConsultationDTo getConsultationSelect(){
    
    return this.consultationSelect;
    
    }
    public static RdvDocteurController getCtrl() {
        
        return ctrl;
    }

    @FXML
    private void dateClick(KeyEvent event) {
       
    }

    @FXML
    private void dateFchoix(ActionEvent event) {
         filtreId.setDisable(false);
    }

    @FXML
    private void filtrezRdv(ActionEvent event) {
        obvCons = null;
        LocalDate date = dateF.getValue();
        String status = statusF.getSelectionModel().getSelectedItem();
        if(date != null && status != null ){
            
            System.out.println("date et status");
            loadTableView(status,date.format(formatter));
            
            
        } else {
                     if(date != null ){
            
                          System.out.println("date ");
                          String statusN = "";
                          loadTableView(statusN,date.format(formatter));
            
                      }
                     if(status != null ){
            
                         loadTableView(status,"");
                          System.out.println("status");
            
                      }
        
        }
        tblvConsultation.setItems(obvCons);
        annulerId.setDisable(false);
    }

    @FXML
    private void choixStatus(ActionEvent event) {
        filtreId.setDisable(false);
    }

    @FXML
    private void annulerFiltre(ActionEvent event) {
         obvCons = null;
         loadTableView("en cours",d);
         filtreId.setDisable(true);
         annulerId.setDisable(true);
    }
}
