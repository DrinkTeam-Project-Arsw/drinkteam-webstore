/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.services.auction;

import edu.eci.arsw.webstore.model.Auction;
import edu.eci.arsw.webstore.persistence.AuctionPersistence;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author estudiante
 */
@Service
public class AuctionServicesStub implements AuctionServices{
    
    @Autowired
    AuctionPersistence aPersistence;
    
    @Override
    public List<Auction> getAllAuctions() {
        return aPersistence.getAllAuctions();
    }

    @Override
    public Auction getAuctionById(String auctionId) {
        return aPersistence.getAuctionById(auctionId);
    }

    @Override
    public void createNewAuction(Auction au) {
        aPersistence.createNewAuction(au);
    }

    @Override
    public void deleteAuctionById(String auctionId) {
        aPersistence.deleteAuctionById(auctionId);
    }

    @Override
    public void addUsersInAuction(String auctionId, String userId) {
        aPersistence.addUsersInAuction(auctionId, userId);
    }

    @Override
    public List<Auction> getAuctionsByUsername(String username) {
        return aPersistence.getAuctionsByUsername(username);
    }
    
}
