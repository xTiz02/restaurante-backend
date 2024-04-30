package org.prd.restaurantback.services.customer;

import org.prd.restaurantback.dtos.CategoryDto;
import org.prd.restaurantback.dtos.ProductDto;
import org.prd.restaurantback.dtos.ReservationDto;

import java.util.List;

public interface CustomerService {
    List<CategoryDto> getAllCategories();

    List<CategoryDto> findAllByTitle(String title);

    List<ProductDto> getProductsByCategory(Long categoryId);

    List<ProductDto> getProductsByCategoryAndTitle(Long categoryId, String title);

    ReservationDto postReservation(ReservationDto reservationDto);
}
