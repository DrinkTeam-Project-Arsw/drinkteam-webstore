/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.persistence.impl;

import edu.eci.arsw.webstore.model.User;
import edu.eci.arsw.webstore.persistence.UserPersistence;
import edu.eci.arsw.webstore.persistence.WebStoreDB;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan David
 */
@Service
public class DbUserPersistence implements UserPersistence {
    
    //Atributos
    WebStoreDB wsdb = new WebStoreDB();
    private List<User> users = new ArrayList<>();
    
    public DbUserPersistence (){
        //Base de datos local (volatil)
        User usr01 = new User("navarro@hotmail.com", "123", "NAVARRO");
        User usr02 = new User("ocampo@hotmail.com", "123", "OCAMPO");
        User usr03 = new User("villate@hotmail.com", "123", "VILLATE");
        
        List<Integer> products1 = new ArrayList<>(); 
        List<Integer> products2 = new ArrayList<>(); 
        List<Integer> products3 = new ArrayList<>(); 
        
        products1.add(1);
        products1.add(3);
        products2.add(2);
        products3.add(4);
        
        usr01.setProducts(products1);
        
        usr02.setProducts(products2);
        
        usr03.setProducts(products3);
        
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
                if(u.getUserNickName().equals(us.getUserNickName())){ 
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
        return wsdb.getUserByUsername(username);
        /**
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
        }**/
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
                if(u.getUserNickName().equals(username)){
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
