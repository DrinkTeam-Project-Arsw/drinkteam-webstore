/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore.persistence.impl;

import edu.eci.arsw.webstore.model.Product;
import edu.eci.arsw.webstore.model.User;
import edu.eci.arsw.webstore.persistence.ProductPersistence;
import edu.eci.arsw.webstore.persistence.UserPersistence;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author jmvillatei
 */
@Service
public class InMemoryProductPersistence implements ProductPersistence{
    
    private List<Product> products = new ArrayList<>();
    UserPersistence uPersistence;
    
    public InMemoryProductPersistence() {
        
        Product prd01 = new Product(1, "Libro Calculo", "Libro de calculo 13, estado bueno", 12.6);
        Product prd02 = new Product(2, "Libro Algebra", "Libro de algebra 13, estado medio", 6.6);
        Product prd03 = new Product(3, "Software Objetos", "Software para empezar en la programacion", 133.4);
        Product prd04 = new Product(4, "Cuaderno", "cuaderno cuadriculado 100 hojas", 5.5);
        
        products.add(prd01);
        products.add(prd02);
        products.add(prd03);
        products.add(prd04);
    }
    
    
    @Override
    public List<Product> getAllProducts() {
        try {
            //Codigo para consultar en la base de datos
            return products;
        } catch (Exception ex) {
            System.out.println("No se han podido obtener los productos");
            return products;
        }
    }

    @Override
    public List<Product> getAllProductsOfUser(String username) {
        List<Product> productListUser = new ArrayList<>();
        try {
            
            System.out.println("Usuario: "+username);
            System.out.println(">> "+uPersistence.getUserByUsername(username));
            User user = uPersistence.getUserByUsername(username);
            
            List<Integer> idProductListUser = new ArrayList<>();
            
            idProductListUser = user.getProducts();
            System.out.println("ids: "+idProductListUser);
            
            for(int idProduct : idProductListUser){
                for (Product prd : products){
                    System.out.println("Comparacion: "+idProduct+"=="+prd.getProductID());
                    if(idProduct==prd.getProductID()){
                        productListUser.add(prd);
                    }
                }
               
            }
            return productListUser;
            
        } catch (Exception ex) {
            System.out.println("No se han podido obtener los productos del usuario");
            return productListUser;
        }
    }

    @Override
    public void createNewProduct(Product pr, String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product getProductByIdOfUser(int id, String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteProductByIdOfUser(int id, String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
