package org.prd.restaurantback.controllers;

import org.prd.restaurantback.dtos.CategoryDto;
import org.prd.restaurantback.dtos.ProductDto;
import org.prd.restaurantback.services.admin.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
//ModelAttribute se usa para recibir datos de un formulario
    private final AdminService adminService;
    private final Logger logger = Logger.getLogger(AdminController.class.getName());

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    //como sabe que adminsService es de tipo AdminServiceImpl? porque en AdminServiceImpl tiene la anotacion @Service

    @PostMapping("/category")
    public ResponseEntity<CategoryDto> postCategory(@ModelAttribute CategoryDto categoryDto) throws IOException {
        logger.info("El nombre de la categoria es : " + categoryDto.getName());
        CategoryDto createdCatDto = adminService.postCategory(categoryDto);
        if (createdCatDto != null) {
            return ResponseEntity.ok(createdCatDto);
        }
        return ResponseEntity.badRequest().build();
    }
    @PostMapping("/access")
    public ResponseEntity<String> hellCheck(){
        return ResponseEntity.ok("Tienes acceso de admin");
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getCategories(){
        List<CategoryDto> categoryDtoList = adminService.getAllCategories();
        if (categoryDtoList != null) {
            return ResponseEntity.ok(categoryDtoList);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/categories/{title}")
    public ResponseEntity<List<CategoryDto>> getCategoriesByTitle(@PathVariable("title") String title){
        List<CategoryDto> categoryDtoList = adminService.findAllByTitle(title);
        if (categoryDtoList != null) {
            return ResponseEntity.ok(categoryDtoList);
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("{categoryId}/product")
    public ResponseEntity<?> postProduct(@PathVariable("categoryId") Long categoryId, @ModelAttribute ProductDto productDto) throws IOException {
        ProductDto createdProductDto = adminService.postProduct(categoryId, productDto);
        if (createdProductDto != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProductDto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Algo salio mal");
    }

    @GetMapping("{categoryId}/products")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable("categoryId") Long categoryId){
        logger.info("El id de la categoria es : " + categoryId);
        List<ProductDto> productDtoList = adminService.getProductsByCategory(categoryId);
        if (productDtoList != null) {
            return ResponseEntity.ok(productDtoList);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{categoryId}/products/{title}")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryAndTitle(@PathVariable("categoryId") Long categoryId, @PathVariable("title") String title){
        List<ProductDto> productDtoList = adminService.getProductsByCategoryAndTitle(categoryId, title);
        if (productDtoList != null) {
            return ResponseEntity.ok(productDtoList);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long productId){
        adminService.deleteProduct(productId);

        return ResponseEntity.noContent().build();//noContent() devuelve un 204
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("productId") Long productId){
        ProductDto productDto = adminService.getProductById(productId);
        if (productDto != null) {
            return ResponseEntity.ok(productDto);
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/product/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("productId") Long productId, @ModelAttribute ProductDto productDto) throws IOException {
        ProductDto updatedProductDto = adminService.updateProduct(productId, productDto);
        if (updatedProductDto != null) {
            return ResponseEntity.ok(updatedProductDto);
        }
        return ResponseEntity.badRequest().build();
    }

}
