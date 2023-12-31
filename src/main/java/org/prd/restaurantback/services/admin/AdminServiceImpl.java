package org.prd.restaurantback.services.admin;

import org.prd.restaurantback.dtos.CategoryDto;
import org.prd.restaurantback.entities.Category;
import org.prd.restaurantback.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    private final CategoryRepository categoryRepository;

    public AdminServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto postCategory(CategoryDto category) throws IOException {
        Category categoryEntity = new Category();
        categoryEntity.setName(category.getName());
        categoryEntity.setDescription(category.getDescription());
        categoryEntity.setImg(category.getImg().getBytes());
        Category cat = categoryRepository.save(categoryEntity);
        if (cat != null) {
            CategoryDto catDto = new CategoryDto();
            catDto.setId(cat.getId());
            catDto.setName(cat.getName());
            catDto.setDescription(cat.getDescription());
            catDto.setReturnedImg(cat.getImg());
            return catDto;
        }
        return null;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream().map(category -> category.getCategoryDto()).toList();
    }
}
