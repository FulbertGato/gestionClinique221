/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author junio
 */
public class Role {
    private int idRole;
    private String libelle;

    public Role() {
        
    } 
     public Role(int id) {
         this.setIdRole(id);
    } 
    public Role(String libelle) {
        this.setLibelle(libelle);
    } 
    public Role(int idRole, String libelle) {
        this.setIdRole(idRole);
        this.setLibelle(libelle);
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    @Override
    public String toString() {
        return libelle;
    }
    

    


}
