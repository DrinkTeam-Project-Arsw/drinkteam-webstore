/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.controllers;

import edu.eci.arsw.webstore.services.transaction.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jmvillatei
 */
@RestController
public class TransactionController {
    @Autowired
    TransactionServices transactionService;
}
