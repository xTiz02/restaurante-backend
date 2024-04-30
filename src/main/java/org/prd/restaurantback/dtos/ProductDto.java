package org.prd.restaurantback.dtos;


import org.prd.restaurantback.entities.Category;
import org.springframework.web.multipart.MultipartFile;

public class ProductDto {

    private Long id;
    private String name;
    private String price;
    private String description;

    private byte[] returnedImg;

    private MultipartFile img;
    private Long categoryId;

    private String categoryName;

    public ProductDto() {
    }

    public ProductDto(Long id, String name, String price, String description, byte[] returnedImg, MultipartFile img, Long categoryId, String categoryName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.returnedImg = returnedImg;
        this.img = img;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getReturnedImg() {
        return returnedImg;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setReturnedImg(byte[] returnedImg) {
        this.returnedImg = returnedImg;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
