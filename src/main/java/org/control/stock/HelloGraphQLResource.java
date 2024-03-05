package org.control.stock;

import org.eclipse.microprofile.graphql.DefaultValue;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@GraphQLApi
public class HelloGraphQLResource {

    @Query("getAllProducts")
    @Description("")
    public List<ProductInventory> getProducts() {
        List<ProductInventory> productos = ProductInventory.listAll();
        
        if (productos.isEmpty()) {
        	log.info("la lista retornada de productos se encuentra vacia: {}", productos);
        }
        
        return productos;
    }
    
    @Mutation
    @Description("")
    @Transactional
    public ProductInventory addProduct(String field) {
    	ProductInventory newProduct = new ProductInventory();
    	newProduct.field = field;
    	newProduct.persist();
    	return newProduct;
    }
    
}