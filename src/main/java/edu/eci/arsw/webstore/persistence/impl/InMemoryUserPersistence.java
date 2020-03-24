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
public class InMemoryUserPersistence implements UserPersistence{

    //Atributos
    WebStoreDB wsdb;
    
    private void newDb(){
        wsdb = new WebStoreDB();
    }
    
    @Override
    public List<User> getAllUsers() {
        newDb();
        return wsdb.getAllUsers();
    }

    @Override
    public void createNewUser(User us) {
        newDb();
        if(getUserByUserNickname(us.getUserNickname()) == null){
            wsdb.createNewUser(us);
        }else{
            try {
                throw new ExcepcionWebStore("Nickname ya registrados en la plataforma.");
            } catch (ExcepcionWebStore ex) {
                Logger.getLogger(InMemoryUserPersistence.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public User getUserByUserNickname(String userNickname) {
        newDb();
        return wsdb.getUserByUserNickname(userNickname); 
    }

    @Override
    public void deleteUserByUserNickname(String userNickname) {
        newDb();
        wsdb.deleteUserByUserNickname(userNickname);
    }

    @Override
    public User getUserByEmail(String email) {
        newDb();
        wsdb.getUserByEmail(email);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteUserByEmail(String email) {
        newDb();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
