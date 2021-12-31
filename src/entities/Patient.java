/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.UUID;

/**
 *
 * @author junio
 */
public class Patient extends User {

   
    private String code;
    private String antecedants;
    private String telephone;

    public String getTelephone() {
        return telephone;
    }

    private void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Patient(int idUser, String nomComplet, String login, String password, Role role, String code,
            String antecedants,String telephone) {
        super(idUser, nomComplet, login, password, role);
        this.setCode(code);
        this.setAntecedants(antecedants);
        this.setTelephone(telephone);
    }

    public String getAntecedants() {
        return antecedants;
    }

    private void setAntecedants(String antecedants) {
        this.antecedants = antecedants;
    }

    public String getCode() {
        return code;
    }

    private void setCode(String code) {
        this.code = code;
    }

    public Patient(String nomComplet, String login, String password, Role role, String antecedants,String telephone) {
        super(nomComplet, login, password, role);
        this.code = generatedCode();
        this.setAntecedants(antecedants);
        this.setTelephone(telephone);
    }

    public final String generatedCode() {
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        return uuidAsString;
    }

  
    

    
}
