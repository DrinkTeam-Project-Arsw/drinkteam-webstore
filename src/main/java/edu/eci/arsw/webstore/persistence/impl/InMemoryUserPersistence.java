/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.persistence.impl;

import edu.eci.arsw.webstore.model.User;
import edu.eci.arsw.webstore.persistence.UserPersistence;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author jmvillatei
 */
@Service
public class InMemoryUserPersistence implements UserPersistence{

    private List<User> users = new ArrayList<>();
    
    public InMemoryUserPersistence(){
        User usr01 = new User("navarro@hotmail.com", "123", "navarro");
        User usr02 = new User("ocampo@hotmail.com", "123", "Ocampo");
        User usr03 = new User("villate@hotmail.com", "123", "Villate");
        
        users.add(usr01);
        users.add(usr02);
        users.add(usr03);
    }
    
    
    
    @Override
    public List<User> getAllUsers() {
        
        try {
            return users;
        } catch (Exception ex) {
            System.out.println("No se han podido obtener los usuarios");
            return users;
        }
    }

    @Override
    public void createNewUser(User us) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUserByUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteUserByUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUserByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteUserByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
