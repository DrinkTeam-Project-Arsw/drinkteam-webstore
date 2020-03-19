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
        //Base de datos local (volatil)
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
            //Codigo para consultar en la base de datos
            return users;
        } catch (Exception ex) {
            System.out.println("No se han podido obtener los usuarios");
            return users;
        }
    }

    @Override
    public void createNewUser(User us) {
        
        try{
            
            Boolean exitingUser = false;
            List<User> usersList = new ArrayList<>();
            //en la variable usersList se debe guardar lo que haga en la base de datos de usuarios (actualmente esta en local)
            usersList = getAllUsers();
            for(User u : usersList){
                if(u.getUserEmail().equals(us.getUserEmail())){
                    exitingUser = true;
                    break;
                }
                if(u.getUserNickname().equals(us.getUserNickname())){
                    exitingUser = true;
                    break;
                }
            }
            
            if(!exitingUser){
                users.add(us);
            }else{
                System.out.println("Usuario Existente");
                
            }
            
        } catch (Exception ex){
            System.out.println("Error: No se ha podido crear este usuario");
        }
    }

    @Override
    public User getUserByUsername(String username) {
        User newUser = null;
        try{
            
            Boolean exitingUser = false;
            List<User> usersList = new ArrayList<>();
            //en la variable usersList se debe guardar lo que haga en la base de datos de usuarios (actualmente esta en local)
            usersList = getAllUsers();
            for(User u : usersList){
                if(u.getUserNickname().equals(username)){
                    exitingUser = true;
                    newUser = u;
                }
            }
            
            if(exitingUser){
                return newUser;
            }else{
                System.out.println("Usuario No existente");
                return newUser;
            }
            
        } catch (Exception ex){
            System.out.println("Error: No se ha podido encontrar este usuario");
            return newUser;
        }
    }

    @Override
    public void deleteUserByUsername(String username) {
        User deleteUser = null;
        try{
            
            Boolean exitingUser = false;
            List<User> usersList = new ArrayList<>();
            //en la variable usersList se debe guardar lo que haga en la base de datos de usuarios (actualmente esta en local)
            usersList = getAllUsers();
            for(User u : usersList){
                if(u.getUserNickname().equals(username)){
                    exitingUser = true;
                    deleteUser = u;
                    break;
                }
            }
            
            if(exitingUser){
                users.remove(deleteUser);
            }else{ 
                System.out.println("Usuario No existente");
            }
            
        } catch (Exception ex){
            System.out.println("Error: No se ha podido encontrar este usuario");
        }
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
