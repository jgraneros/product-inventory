package org.control.stock.service;


import org.control.stock.entity.Category;
import org.control.stock.exceptions.CategoryException;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class CategoryService {
	
	public void persistIfNotExistInDB(Category category) throws CategoryException {
		
		if (isNotInDB(category)) {
			category.persist();
			log.info("new category saved.");
		}
		
	}

	private boolean isNotInDB(Category category) throws CategoryException {
		
		if (category == null) {
			throw new CategoryException("Category null");
		}
		return Category.count("name", category.name) == 0 ? true : false;
	}

}
