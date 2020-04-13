/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.persistence;

import edu.eci.arsw.webstore.model.User;
import java.util.List;

/**
 *
 * @author jmvillatei
 */
public interface UserPersistence {
    
    /**
     * Este metodo permite obtener todos los usuarios
     *
     * @return Una lista con todos los usuarios
     */
    public List<User> getAllUsers();
    
    /**
     * Este metodo permite la creacion de un nuevo usuario
     *
     * @param us
     */
    public void createNewUser(User us);

    /**
     * Este metodo permite obtener un usuario por su nickname
     *
     * @param userNickname nickname del usuario a obtener
     * @return El usuario que pertenece a ese nickname
     */
    public User getUserByUserNickname(String userNickname);

    /**
     * Este metodo permite eliminar un usuario por su nickname
     *
     * @param userNickname
     */
    public void deleteUserByUserNickname(String userNickname);
    
    /**
     * Este metodo permite obtener un usuario por su email
     *
     * @param email el email del usuario a obtener
     * @return El usuario que pertenece a ese email
     */
    public void updateUser(User user);

    /**
     * Este metodo permite eliminar un usuario por su email
     *
     * @param email
     */
    public void deleteUserByEmail(String email);
}
