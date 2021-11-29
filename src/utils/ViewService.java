/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.time.LocalDate;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.layout.Pane;

/**
 *
 * @author junio
 */
public class ViewService {
    
    
     
       public static void loadComboBoxService(ComboBox<String> service){
        service.getItems().add("Consultation");
        service.getItems().add("Prestation");
      //  service.getSelectionModel().selectFirst();
    } 
       
       public static void datePastDesactive(JFXDatePicker dateChoix){
       
       dateChoix.setDayCellFactory(picker -> new DateCell() {
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();

            setDisable(empty || date.compareTo(today) < 0 );
        }
        });
       }
       
        public  void loadView(String view, Pane content) throws IOException{
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/views/"+view+".fxml"));
        content.getChildren().clear();
        content.getChildren().add(root);
    }
}
