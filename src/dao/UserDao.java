/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.Role;
import entities.User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author junio
 */
public class UserDao implements IDao<User> {
    private final DataBase database= new DataBase();
    private final RoleDao roleDao=new RoleDao();
    private final String  SQL_LOGIN = "SELECT * FROM user u WHERE u.login LIKE  ? AND u.password LIKE ? ";
    private final String  SQL_EMAIL = "SELECT * FROM user WHERE login LIKE  ?";
    private final String  SQL_FIND_ALL = "SELECT * FROM user";
    private final String SQL_INSERT="INSERT INTO `user` (`nomComplet`, `login`, `password` , `role_id`) VALUES ( ?, ?, ?,?)";
    

    @Override
    public int insert(User user) {
        int lastInsertId=0;
        database.openConnexion();
        database.initPrepareStatement(SQL_INSERT);
        try {
            database.getPs().setString(1, user.getNomComplet());
            database.getPs().setString(2, user.getLogin());
            database.getPs().setString(3, user.getPassword());
            database.getPs().setInt(4, user.getRoleId());
            database.executeUpdate(SQL_INSERT);
            ResultSet rs=database.getPs().getGeneratedKeys();
            if(rs.next()){
            lastInsertId=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        database.closeConnexion();
        return lastInsertId;
    }

    @Override
    public int update(User user) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int delete(int id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List findAll() {
        List<User> users=new ArrayList<User>();
        
        database.openConnexion();
        database.initPrepareStatement(SQL_FIND_ALL);
       
            ResultSet rs =database.executeSelect(SQL_FIND_ALL);
        
        try {
            while(rs.next()){
                try {
                    
                    Role role = roleDao.findById(rs.getInt("role_id"));
                    User user = new User(
                    rs.getInt("id_user"),
                    rs.getString("nomComplet"),
                    rs.getString("login"),
                    rs.getString("password"), 
                    role
                    );

                    users.add(user);
                } catch (SQLException ex) {
                    Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return users;
    }

    @Override
    public User  findById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    
    public User findUserByLoginPassword(String login, String password) {

         User user = null;
        database.openConnexion();
        database.initPrepareStatement(SQL_LOGIN);
        try {
            database.getPs().setString(1, login);
            database.getPs().setString(2, password);
            ResultSet rs = database.executeSelect(SQL_LOGIN);
        
            if(rs.next())
            {
                    Role role = roleDao.findById(rs.getInt("role_id"));
                    user = new User(
                    rs.getInt("id_user"),
                    rs.getString("nomComplet"),
                    rs.getString("login"),
                    rs.getString("password"), 
                    role
                    );
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    public User getUserByLogin(String login){

        User user = null;
        database.openConnexion();
        database.initPrepareStatement(SQL_EMAIL);
        try {
            database.getPs().setString(1, login);
            ResultSet rs = database.executeSelect(SQL_EMAIL);
            if(rs.next())
            {
                    Role role = roleDao.findById(rs.getInt("role_id"));
                    user = new User(
                    rs.getInt("id_user"),
                    rs.getString("nomComplet"),
                    rs.getString("login"),
                    rs.getString("password"), 
                    role
                    );
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }


        return user;
    }
    
}
