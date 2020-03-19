/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.services.user;

import edu.eci.arsw.webstore.model.User;
import edu.eci.arsw.webstore.persistence.UserPersistence;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jmvillatei
 */
@Service
public class UserServicesStub implements UserServices{

    @Autowired
    UserPersistence uPersistence;
    
    @Override
    public List<User> getAllUsers() {
        return uPersistence.getAllUsers();
    }

    @Override
    public void createNewUser(User us) {
        uPersistence.createNewUser(us);
    }

    @Override
    public User getUserByUsername(String username) {
        return uPersistence.getUserByUsername(username);
    }

    @Override
    public void deleteUserByUsername(String username) {
        uPersistence.deleteUserByUsername(username);
    }

    @Override
    public User getUserByEmail(String email) {
        return uPersistence.getUserByEmail(email);
    }

    @Override
    public void deleteUserByEmail(String email) {
        uPersistence.deleteUserByEmail(email);
    }

        
}
