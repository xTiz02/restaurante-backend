package org.prd.restaurantback.services.admin;

import org.prd.restaurantback.dtos.CategoryDto;
import org.prd.restaurantback.entities.Category;

import java.io.IOException;
import java.util.List;

public interface AdminService {

    CategoryDto postCategory(CategoryDto category) throws IOException;

    List<CategoryDto> getAllCategories();
}
