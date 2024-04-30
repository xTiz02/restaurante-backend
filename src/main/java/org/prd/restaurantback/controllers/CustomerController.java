package org.prd.restaurantback.controllers;

import org.prd.restaurantback.dtos.CategoryDto;
import org.prd.restaurantback.dtos.ProductDto;
import org.prd.restaurantback.dtos.ReservationDto;
import org.prd.restaurantback.services.customer.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final Logger logger = Logger.getLogger(AdminController.class.getName());

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getCategories(){
        List<CategoryDto> categoryDtoList = customerService.getAllCategories();
        if (categoryDtoList != null) {
            return ResponseEntity.ok(categoryDtoList);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/categories/{title}")
    public ResponseEntity<List<CategoryDto>> getCategoriesByTitle(@PathVariable("title") String title){
        List<CategoryDto> categoryDtoList = customerService.findAllByTitle(title);
        if (categoryDtoList != null) {
            return ResponseEntity.ok(categoryDtoList);
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("{categoryId}/products")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable("categoryId") Long categoryId){
        logger.info("El id de la categoria es : " + categoryId);
        List<ProductDto> productDtoList = customerService.getProductsByCategory(categoryId);
        if (productDtoList != null) {
            return ResponseEntity.ok(productDtoList);
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/{categoryId}/products/{title}")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryAndTitle(@PathVariable("categoryId") Long categoryId, @PathVariable("title") String title){
        List<ProductDto> productDtoList = customerService.getProductsByCategoryAndTitle(categoryId, title);
        if (productDtoList != null) {
            return ResponseEntity.ok(productDtoList);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping(value = "/reservation", produces = "application/json") //produces = "application/json" para que devuelva un json
//para que lea el json que le llega del front hay que poner @RequestBody
    public ResponseEntity<ReservationDto> postingReservation(@RequestBody ReservationDto reservationDto) throws IOException {
        logger.info("El reservationDto es: " + reservationDto.toString());

        ReservationDto createdReservationDto = customerService.postReservation(reservationDto);
        if (createdReservationDto != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdReservationDto);
        }
        return ResponseEntity.badRequest().build();
    }
}
