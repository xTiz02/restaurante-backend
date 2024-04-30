package org.prd.restaurantback.services.customer;

import org.prd.restaurantback.dtos.CategoryDto;
import org.prd.restaurantback.dtos.ProductDto;
import org.prd.restaurantback.dtos.ReservationDto;
import org.prd.restaurantback.entities.Category;
import org.prd.restaurantback.entities.Reservation;
import org.prd.restaurantback.entities.User;
import org.prd.restaurantback.enums.ReservationStatus;
import org.prd.restaurantback.repositories.CategoryRepository;
import org.prd.restaurantback.repositories.ProductRepository;
import org.prd.restaurantback.repositories.ReservationRepository;
import org.prd.restaurantback.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final ReservationRepository reservationRepository;

    private final Logger logger = Logger.getLogger(CustomerServiceImpl.class.getName());

    public CustomerServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository, UserRepository userRepository, ReservationRepository reservationRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream().map(category -> category.getCategoryDto()).toList();
    }

    @Override
    public List<CategoryDto> findAllByTitle(String title) {
        return categoryRepository.findAllByNameContaining(title).stream().map(category -> category.getCategoryDto()).toList();
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
    public ReservationDto postReservation(ReservationDto reservationDto) {
        logger.info("El customer es: " + reservationDto.getCustomerUsername());
        Optional<User> user =  userRepository.findFirstByEmail(reservationDto.getCustomerUsername());
        logger.info("El user es: " + user.get().getEmail());
        if (user.isPresent()) {
            Reservation reservation = new Reservation();
            BeanUtils.copyProperties(reservationDto, reservation);
            reservation.setUser(user.get());
            reservation.setReservationStatus(ReservationStatus.PEDDING);
            Reservation savedReservation = reservationRepository.save(reservation);
            if (savedReservation.getId()!= null) {
                return savedReservation.getReservationDto();
            }
        }
        return null;
    }
}
