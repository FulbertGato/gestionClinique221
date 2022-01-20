/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.controller;

import Service.Service;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import entities.DetailPrestation;
import entities.Docteur;
import entities.Prestation;
import entities.User;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
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

/**
 * FXML Controller class
 *
 * @author gatoj
 */
public class PrestationController implements Initializable {

    @FXML
    private Pane contentDocteur;
    @FXML
    private TableView<DetailPrestation> tblvPrestation;
    @FXML
    private TableColumn<DetailPrestation, String> tblcType;
    @FXML
    private TableColumn<DetailPrestation, String> tblNmPatient;
    @FXML
    private TableColumn<DetailPrestation, String> tblcAnte;
    @FXML
    private TableColumn<DetailPrestation, String> tblcTel;
    @FXML
    private TableColumn<DetailPrestation, String> tblcAge;
    @FXML
    private JFXButton nbreRdv;
    @FXML
    private JFXButton nameSrv;
    @FXML
    private JFXButton date;
    @FXML
    private JFXButton etat;
    @FXML
    private DatePicker dateF;
    @FXML
    private JFXButton filtreId;
    @FXML
    private JFXComboBox<String> statusF;
    @FXML
    private JFXButton annulerId;

    ObservableList<DetailPrestation> obvCons;
    Service service  = new Service();
    private final User user= ConnexionController.getCtrl().getUser();
    private final Docteur doc = service.docteurById(user.getIdUser());
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-LLLL-yyyy");
    LocalDateTime dateN = LocalDateTime.now();
    String d =dateN.format(formatter);
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nbreRdv.setText("Nbre rdv: "+service.nombrerdvDoc(user.getIdUser(),d));
        nameSrv.setText("Service: "+doc.getSpecialite());
        date.setText(d);
        
        statusF.getItems().add("EN COURS");
        statusF.getItems().add("TERMINER");
        statusF.getItems().add("ANNULER");
        filtreId.setDisable(true);
        annulerId.setDisable(true);
    }    

    @FXML
    private void selectConsulatation(MouseEvent event) {
    }

    @FXML
    private void dateClick(KeyEvent event) {
    }

    @FXML
    private void dateFchoix(ActionEvent event) {
    }

    @FXML
    private void filtrezRdv(ActionEvent event) {
    }

    @FXML
    private void choixStatus(ActionEvent event) {
    }

    @FXML
    private void annulerFiltre(ActionEvent event) {
    }
    
    
     private void loadTableView(String etat, String date){
        List<DetailPrestation> presList=service.showAllPrestation(etat, date,doc.getIdUser());
       /* List<ConsultationDTo> dtoList=new ArrayList<ConsultationDTo>();
        for (Consultation e : consList) {
            ConsultationDTo c = new ConsultationDTo();
            c.toDto(e);    
            dtoList.add(c);
        }*/
         obvCons=FXCollections.observableArrayList(presList);
         tblNmPatient.setCellValueFactory(new PropertyValueFactory<>("nomPatient"));
         tblcType.setCellValueFactory(new PropertyValueFactory<>("specialite"));
         tblcAnte.setCellValueFactory(new PropertyValueFactory<>("antPatient"));
         tblcTel.setCellValueFactory(new PropertyValueFactory<>("telPtaient"));
         tblcAge.setCellValueFactory(new PropertyValueFactory<>("status"));
   
            tblvPrestation.setItems(obvCons);
         
    }
    
}
