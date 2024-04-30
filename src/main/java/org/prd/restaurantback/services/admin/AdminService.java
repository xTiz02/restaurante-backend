package org.prd.restaurantback.services.admin;

import org.prd.restaurantback.dtos.CategoryDto;
import org.prd.restaurantback.dtos.ProductDto;
import org.prd.restaurantback.entities.Category;

import java.io.IOException;
import java.util.List;

public interface AdminService {

    CategoryDto postCategory(CategoryDto category) throws IOException;

    List<CategoryDto> getAllCategories();

    List<CategoryDto> findAllByTitle(String name);

    ProductDto postProduct(Long categoryId, ProductDto productDto) throws IOException;

    List<ProductDto> getProductsByCategory(Long categoryId);

    List<ProductDto> getProductsByCategoryAndTitle(Long categoryId, String title);

    void deleteProduct(Long productId);

    ProductDto getProductById(Long productId);

    ProductDto updateProduct(Long productId, ProductDto productDto) throws IOException;
}
