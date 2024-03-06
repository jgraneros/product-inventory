package org.control.stock.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Category extends PanacheEntity{

	public String name;
}
