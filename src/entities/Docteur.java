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
public class Docteur extends User {
    private Specialite specialite;

    public Docteur(int idUser, String nomComplet, String login, String password, Role role, Specialite specialite) {
        super(idUser, nomComplet, login, password, role);
        this.setSpecialite(specialite);
    }

    public Docteur( String nomComplet, String login, String password, Role role, Specialite specialite) {
        super(nomComplet, login, password, role);
        this.setSpecialite(specialite);
    }

    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }
    public int getSpecialiteId(){
    
        return this.specialite.getIdSpecialite();
    
    }

    
}
