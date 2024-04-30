package org.prd.restaurantback.services.admin;

import org.prd.restaurantback.dtos.CategoryDto;
import org.prd.restaurantback.dtos.ProductDto;
import org.prd.restaurantback.entities.Category;
import org.prd.restaurantback.entities.Product;
import org.prd.restaurantback.repositories.CategoryRepository;
import org.prd.restaurantback.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    public AdminServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
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

    @Override
    public List<CategoryDto> findAllByTitle(String name) {
        return categoryRepository.findAllByNameContaining(name).stream().map(category -> category.getCategoryDto()).toList();
    }

    @Override
    public ProductDto postProduct(Long categoryId, ProductDto productDto) throws IOException {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category != null) {
            Product product = new Product();
            BeanUtils.copyProperties(productDto,product); //copiar propiedades de un objeto a otro (productDto a product) y lo guarda en product
            product.setCategory(category);
            product.setImg(productDto.getImg().getBytes());
            //como copia las propiedades? con BeanUtils y el metodo copyProperties que recibe dos parametros, el primero es el objeto que queremos copiar y el segundo es el objeto donde queremos copiar las propiedades del primero (productDto a product)
            Product createdproduct = productRepository.save(product); 
            return createdproduct.getProductDto();
        }
        return null;
    }

    @Override
    public List<ProductDto> getProductsByCategory(Long categoryId) {

        return productRepository.findAllByCategory_Id(categoryId).stream().map(product -> product.getProductDto()).toList();
    }

    @Override
    public List<ProductDto> getProductsByCategoryAndTitle(Long categoryId, String title) {
        return productRepository.findAllByCategory_IdAndNameContaining(categoryId, title).stream().map(product -> product.getProductDto()).toList();
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            productRepository.delete(product);
        }
        //throw new IllegalArgumentException("No se pudo eliminar el producto de id: "+productId);
    }

    @Override
    public ProductDto getProductById(Long productId) {
         Product product = productRepository.findById(productId).orElse(null);
        if (product != null){
            return product.getProductDto();
        }
        return null;
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDto) throws IOException {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            BeanUtils.copyProperties(productDto, product);
            product.setId(productId);
            Product updatedProduct;
            if(productDto.getImg() != null){
                product.setImg(productDto.getImg().getBytes());
            }
            updatedProduct = productRepository.save(product);
            return updatedProduct.getProductDto();
        }
        return null;
    }
}
