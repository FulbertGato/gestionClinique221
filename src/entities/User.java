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
public class User {
    protected int idUser;
    protected String nomComplet;
    protected String login;
    protected String password;
    protected Role role;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    
    public User(int idUser, String nomComplet, String login, String password, Role role) {
        this.idUser = idUser;
        this.nomComplet = nomComplet;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(String nomComplet, String login, String password, Role role) {
        this.nomComplet = nomComplet;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public int getRoleId(){
        return this.role.getIdRole();
    }

    @Override
    public String toString() {
        return nomComplet;
    }
    
    
}
