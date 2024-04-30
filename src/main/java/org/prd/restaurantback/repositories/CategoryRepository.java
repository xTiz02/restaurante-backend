package org.prd.restaurantback.repositories;

import org.prd.restaurantback.dtos.CategoryDto;
import org.prd.restaurantback.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByNameContaining(String name);
}
