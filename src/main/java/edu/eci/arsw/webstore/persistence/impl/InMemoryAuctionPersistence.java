/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.persistence.impl;

import edu.eci.arsw.webstore.model.Auction;
import edu.eci.arsw.webstore.persistence.AuctionPersistence;
import edu.eci.arsw.webstore.persistence.WebStoreDB;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author estudiante
 */
@Service
public class InMemoryAuctionPersistence implements AuctionPersistence {

    //Atributos
    WebStoreDB wsdb;

    private void newDb() {
        wsdb = new WebStoreDB();
    }

    @Override
    public List<Auction> getAllAuctions() {
        newDb();
        return wsdb.getAllAuctions();
    }

    @Override
    public Auction getAuctionById(String auctionId) {
        newDb();
        return wsdb.getAuctionById(auctionId);
    }

    @Override
    public void createNewAuction(Auction au) {
        newDb();
        wsdb.createNewAuction(au);
    }

    @Override
    public void deleteAuctionById(String auctionId) {
        newDb();
        wsdb.deleteBuyersInAuction(auctionId);
        wsdb.deleteAuctionById(auctionId);
    }

    @Override
    public void addUsersInAuction(String auctionId, String userId) {
        newDb();
        wsdb.addUsersInAuction(auctionId, userId);
    }

}
