/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.controller;

import Service.Service;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dto.DetailPrestationDto;
import entities.DetailPrestation;
import entities.Docteur;
import entities.Prestation;
import entities.User;
import java.io.IOException;
import java.net.URL;
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
 * @author gatoj
 */
public class PrestationController implements Initializable {

    @FXML
    private Pane contentDocteur;
    @FXML
    private TableView<DetailPrestationDto> tblvPrestation;
    @FXML
    private TableColumn<DetailPrestationDto, String> tblcType;
    @FXML
    private TableColumn<DetailPrestationDto, String> tblNmPatient;
    @FXML
    private TableColumn<DetailPrestationDto, String> tblcAnte;
    @FXML
    private TableColumn<DetailPrestationDto, String> tblcTel;
    @FXML
    private TableColumn<DetailPrestationDto, String> tblcAge;
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

    ObservableList<DetailPrestationDto> obvCons;
    Service service  = new Service();
    private final User user= ConnexionController.getCtrl().getUser();
    private final Docteur doc = service.docteurById(user.getIdUser());
     private ViewService view = new ViewService();
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
        loadTableView("EN COURS", d);
        statusF.getItems().add("EN COURS");
        statusF.getItems().add("TERMINER");
        statusF.getItems().add("ANNULER");
        filtreId.setDisable(true);
        annulerId.setDisable(true);
    }    

    @FXML
    private void selectConsulatation(MouseEvent event) {
        try {
            view.loadView("v_DetailPrestation", contentDocteur);
        } catch (IOException ex) {
            Logger.getLogger(PrestationController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
       List<DetailPrestationDto> dtoList=new ArrayList<>();
       presList.stream().map((e) -> {
           DetailPrestationDto c = new DetailPrestationDto();
           c.toDto(e);    
            return c;
        }).forEachOrdered((c) -> {
            dtoList.add(c);
        });
         obvCons=FXCollections.observableArrayList(dtoList);
         tblNmPatient.setCellValueFactory(new PropertyValueFactory<>("nomPatient"));
         tblcType.setCellValueFactory(new PropertyValueFactory<>("prestation"));
         tblcAnte.setCellValueFactory(new PropertyValueFactory<>("antPatient"));
         tblcTel.setCellValueFactory(new PropertyValueFactory<>("telPtaient"));
         tblcAge.setCellValueFactory(new PropertyValueFactory<>("status"));
   
            tblvPrestation.setItems(obvCons);
         
    }
    
}
