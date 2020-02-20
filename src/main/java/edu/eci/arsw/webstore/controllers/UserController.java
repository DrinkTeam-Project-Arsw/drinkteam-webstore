/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jmvillatei
 */

@RestController
@RequestMapping( value = "/login")
public class UserController {
    @RequestMapping(method = RequestMethod.GET) 
    public String getOffendingAccounts() {
        String ans = "pagina de inicio en construcción";
        return ans;
    }
}
