/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.eci.arsw.webstore.model.Auction;
import edu.eci.arsw.webstore.model.User;
import edu.eci.arsw.webstore.services.auction.AuctionServices;
import edu.eci.arsw.webstore.services.user.UserServices;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Juan David
 */
@RestController
@RequestMapping(value = "/api/v1")
public class AuctionController {
    
    @Autowired
    AuctionServices aService;
    
    @Autowired
    UserServices uService;
    
    @RequestMapping(method = RequestMethod.GET, path = "auctions")
    public ResponseEntity<?> getAllAuctions() {
        try {
            System.out.println("Consultando Subastas...");
            String data = new Gson().toJson(aService.getAllAuctions());
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(AuctionController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido retornar las Subastas", HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(method = RequestMethod.GET, path = { "auctions/{auctionId}" })
    public ResponseEntity<?> getAuctionById(@PathVariable("auctionId") String auctionId) {
        try {
            System.out.println("Consultando Subasta: " + auctionId);

            String data = new Gson().toJson(aService.getAuctionById(auctionId));

            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);

        } catch (Exception ex) {
            Logger.getLogger(AuctionController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido retornar la subasta: " + auctionId, HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST, path = "auctions")
    public ResponseEntity<?> createNewAuction(@RequestBody String auction ) {
        //Formato de json {"1":{"auctionInitPrice":"5000","auctionDate":"2020-04-19 08:22:23","auctionDateFinal":"2020-06-1 08:22:23","auctionTimeToWait":"60","auctionType":"1","auctinActive":"true","sellerId":"0","productId":"5ea79e12e8cc5500041bc120","auctionStatus":"En curso"}}
        try {
            System.out.println("Creando subasta nuevo...");
            //Pasar el String JSON a un Map
            Type listType = new TypeToken<Map<Integer, Auction>>() {
            }.getType();
            Map<String, Auction> result = new Gson().fromJson(auction, listType);
            
            //Obtener las llaves del Map
            Object[] nameKeys = result.keySet().toArray();
            
            Auction au = result.get(nameKeys[0]);
            
            ObjectId newObjectIdProduct = new ObjectId(new Date());
            au.setAuctionId(newObjectIdProduct.toHexString());
            
            aService.createNewAuction(au);
               
            return new ResponseEntity<>(HttpStatus.CREATED);
            
        } catch (Exception ex) {
            Logger.getLogger(AuctionController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido registrar la subasta", HttpStatus.FORBIDDEN);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST, path = "auctions/{auctionId}/{userNickname}")
    public ResponseEntity<?> addUsersInAuction(@PathVariable("auctionId") String auctionId, @PathVariable("userNickname") String userNickname) {
        try {
            System.out.println("Agregando al usuario: "+ userNickname+" a la subasta: " + auctionId);
            
            User u = uService.getUserByUserNickname(userNickname);
            
            aService.addUsersInAuction(auctionId, u.getIdUser());
               
            return new ResponseEntity<>(HttpStatus.CREATED);
            
        } catch (Exception ex) {
            Logger.getLogger(AuctionController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido registrar la subasta", HttpStatus.FORBIDDEN);
        }
    }
    
    @RequestMapping(method = RequestMethod.DELETE, path = {"auctions/{auctionId}"})
    public ResponseEntity<?> deleteAuctionById(@PathVariable("auctionId") String auctionId) {
        try {
            System.out.println("Eliminando Subasta: "+auctionId);

            aService.deleteAuctionById(auctionId);
            
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(AuctionController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido eliminar la subasta con el id: " + auctionId,
                    HttpStatus.FORBIDDEN);
        }
    }
}
