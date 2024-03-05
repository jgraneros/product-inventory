package org.control.stock;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;


@Entity
public class ProductInventory extends PanacheEntity {
    public String field;
}
