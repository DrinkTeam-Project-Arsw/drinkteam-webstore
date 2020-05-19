/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.persistence.impl;

import edu.eci.arsw.webstore.model.User;
import edu.eci.arsw.webstore.persistence.UserPersistence;
import edu.eci.arsw.webstore.persistence.WebStoreDB;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author jmvillatei
 */
@Service
public class InMemoryUserPersistence implements UserPersistence {

    // Atributos
    WebStoreDB wsdb;

    private void newDb() {
        wsdb = new WebStoreDB();
    }

    @Override
    public List<User> getAllUsers() {
        newDb();
        try {
            return wsdb.getAllUsers();
        } catch (Exception ex) {
            Logger.getLogger(InMemoryUserPersistence.class.getName()).log(Level.SEVERE, null, ex);
            return wsdb.getAllUsers();
        }

    }

    @Override
    public void createNewUser(User us) {
        newDb();
        try {
             wsdb.createNewUser(us);
       } catch (Exception ex) {
            Logger.getLogger(InMemoryUserPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public User getUserByUserNickname(String userNickname) {
        newDb();
        try {
            return wsdb.getUserByUserNickname(userNickname);
        } catch (Exception ex) {
            Logger.getLogger(InMemoryUserPersistence.class.getName()).log(Level.SEVERE, null, ex);
            return wsdb.getUserByUserNickname(userNickname);
        }
        
    }

    @Override
    public void deleteUserByUserNickname(String userNickname) {
        newDb();
        try {
            wsdb.deleteUserByUserNickname(userNickname);
        } catch (Exception ex) {
            Logger.getLogger(InMemoryUserPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void updateUser(User user) {
        newDb();
        try {
            wsdb.updateUser(user);
        } catch (Exception ex) {
            Logger.getLogger(InMemoryUserPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    @Override
    public User getUserById(String id) {
        newDb();
        try {
            return wsdb.getUserById(id);
        } catch (Exception ex) {
            Logger.getLogger(InMemoryUserPersistence.class.getName()).log(Level.SEVERE, null, ex);
            return wsdb.getUserById(id);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        newDb();
        try {
            return wsdb.getUserByEmail(email);
        } catch (Exception ex) {
            Logger.getLogger(InMemoryUserPersistence.class.getName()).log(Level.SEVERE, null, ex);
            return wsdb.getUserByEmail(email);
        }
        
    }
}
