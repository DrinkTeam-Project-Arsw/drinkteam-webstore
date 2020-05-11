/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.persistence;

import edu.eci.arsw.webstore.model.Auction;
import java.util.List;

/**
 *
 * @author estudiante
 */
public interface AuctionPersistence {
    
    /**
     * Metodo que permite consultar todas las subastas.
     * @return Retorna una lista con todas las subastas.
     */
    public List<Auction> getAllAuctions();
    
    /**
     * Metodo que permite consultar una subasta por su id.
     * @param auctionId Es el id de la suabasta.
     * @return  Retorna la subasta correspondiente al id.
     */
    public Auction getAuctionById(String auctionId);
    
    /**
     * Metodo que permite crear una nueva subasta
     * @param au Es la nueva subasta que sera registrada en la base de datos.
     */
    public void createNewAuction(Auction au);
    
    /**
     * Metodo que permite eliminar una subasta.
     * @param auctionId Es el id de la subasta que se quiere eliminar.
     */
    public void deleteAuctionById(String auctionId);
    
    /**
     * Metodo que permite agregar un usuario a la subasta.
     * @param auctionId Es el id de la subasta.
     * @param userId Es el id del usuario que se va unir a la subasta.
     */
    public void addUsersInAuction(String auctionId, String userId);
}
