package org.control.stock;

import org.control.stock.entity.Category;
import org.control.stock.entity.ProductInventory;
import org.control.stock.exceptions.CategoryException;
import org.control.stock.exceptions.ProductInventoryException;
import org.control.stock.service.ProductService;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@GraphQLApi
public class ProductGraphQLResource {
	
	@Inject
	ProductService productService;

    @Query("getAllProducts")
    @Description("")
    public List<ProductInventory> getProducts() {
        List<ProductInventory> productos = ProductInventory.listAll();
        
        if (productos.isEmpty()) {
        	log.debug("Empty list: {}", productos);
        }
        
        return productos;
    }
    
    @Mutation
    @Description("")
    public ProductInventory addOrUpdate(ProductInventory product) throws ProductInventoryException, CategoryException {
    	return productService.addOrUpdate(product);
    }
    	
    	
    
}