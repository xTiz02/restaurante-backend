package org.prd.restaurantback.repositories;

import org.prd.restaurantback.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>{


    List<Product> findAllByCategory_Id(Long categoryId);
    List<Product> findAllByCategory_IdAndNameContaining(Long categoryId, String name);
}
