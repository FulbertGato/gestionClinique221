/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Service.Service;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author junio
 */
public class ConnexionController implements Initializable {

    @FXML
    private AnchorPane login;
    @FXML
    private Text txtError;
    @FXML
    private TextField ttxtEmail;
    @FXML
    private TextField txtpPassword;
    
     private final Service service = new Service();
    
    private static ConnexionController ctrl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         txtError.setVisible(false);
         ctrl = this;

    }    

    @FXML
    private void handleConnexion(ActionEvent event) {
        
        String login = ttxtEmail.getText().trim();
        String password = txtpPassword.getText().trim();
        if(login.isEmpty() || password.isEmpty())
        {
          txtError.setText("login ou le mot de passe Obligatoire");
          txtError.setVisible(true);
        }
        else{
            
            
          User user = service.login(login, password);
          
          if(user == null)
          {
               txtError.setText("login ou le mot de passe Incorrect");
               txtError.setVisible(true);
          }
          else
          {
              txtError.setText("CONNECTER");
              txtError.setVisible(true);

             /*//Cache la fénétre de connexion
              this.txtError.getScene().getWindow().hide();
              AnchorPane root = null;
              
              try {
                  root = FXMLLoader.load(getClass().getResource("/views/v_home.fxml"));
                  Scene scene = new Scene(root);
                  Stage stage = new Stage();
                  stage.setScene(scene);
                  stage.show();
              } catch (IOException ex) {
                  Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
              }*/
          }
        }
    }

    @FXML
    private void handleInscription(ActionEvent event) {
    }
    
}
