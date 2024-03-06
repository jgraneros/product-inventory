package org.control.stock.service;

import org.control.stock.entity.ProductInventory;
import org.control.stock.exceptions.CategoryException;
import org.control.stock.exceptions.ProductInventoryException;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class ProductService {
	
	@Inject
	CategoryService categoryService;
	
	@Transactional
	public ProductInventory addOrUpdate(ProductInventory product) throws ProductInventoryException, CategoryException {
		
			categoryService.persistIfNotExistInDB(product.category);
		
		if (isIdNull(product)) {
			
			product.persist();
			
		} else {
			
			var existingProduct = ProductInventory.<ProductInventory>findById(product.id);
			
			if (isNotInDB(existingProduct)) {
				throw new ProductInventoryException("Product is not in DB");
			} else {
				existingProduct.sku = product.sku;
				existingProduct.availability = product.availability;
				existingProduct.category = product.category;
			}
			
		}
		
		return product;

	}


	private boolean isNotInDB(ProductInventory existingProduct) throws ProductInventoryException {
		
		if (existingProduct == null) {
			log.error("Null product");
			throw new ProductInventoryException("Null product");
		}
		
		return existingProduct == null ? true : false;
	}


	private boolean isIdNull(ProductInventory product) throws ProductInventoryException {
		
		if (product == null) {
			log.error("Null product");
			throw new ProductInventoryException("Null product");
		}
		
		return product.id == null ? true : false;
	}
	
	

}
