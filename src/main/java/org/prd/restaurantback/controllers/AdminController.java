package org.prd.restaurantback.controllers;

import org.prd.restaurantback.dtos.CategoryDto;
import org.prd.restaurantback.services.admin.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
//ModelAttribute se usa para recibir datos de un formulario
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    //como sabe que adminsService es de tipo AdminServiceImpl? porque en AdminServiceImpl tiene la anotacion @Service

    @PostMapping("/category")
    public ResponseEntity<CategoryDto> postCategory(@ModelAttribute CategoryDto categoryDto) throws IOException {
        CategoryDto createdCatDto = adminService.postCategory(categoryDto);
        if (createdCatDto != null) {
            return ResponseEntity.ok(createdCatDto);
        }
        return ResponseEntity.badRequest().build();
    }
}
