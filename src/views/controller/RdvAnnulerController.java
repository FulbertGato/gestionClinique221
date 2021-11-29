/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.controller;

import Service.Service;
import dto.RendezVousDTO;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author junio
 */
public class RdvAnnulerController implements Initializable {

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
    Service service =new Service();
    ObservableList<RendezVousDTO> obvRdv;

    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
         loadTableView();
    }    

    @FXML
    private void choixRendezVous(MouseEvent event) {
    }
     private void loadTableView(){
       
        List<RendezVousDTO> rdvList=service.showAllRendezVous("ANNULER");
        obvRdv=FXCollections.observableArrayList(rdvList);
        //Construction des colonnes
        tblcService.setCellValueFactory(new PropertyValueFactory<>("consultOrPresta"));
        tblcType.setCellValueFactory(new PropertyValueFactory<>("consultOrPrestaType"));
        tblDate.setCellValueFactory(new PropertyValueFactory<>("dateRendezVous"));
        tblEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tblcID.setCellValueFactory(new PropertyValueFactory<>("idRendezVous"));
        
        
        tblvRendezVous.setItems(obvRdv);
    }
    
}
